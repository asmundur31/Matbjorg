package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.BuyerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.OrderDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.OrderItemDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private BuyerService buyerService;
    private AdvertisementService adService;

    public OrderRestController(OrderService orderService, OrderItemService orderItemService, BuyerService buyerService, AdvertisementService advertisementService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.buyerService = buyerService;
        this.adService = advertisementService;
    }

    /**
     * Fall sem sækir Order fyrir Buyer með auðkenni id
     * @param token auðkenningar token fyrir buyer
     * @return active order fyrir viðkomandi buyer
     */
    @GetMapping("/active")
    OrderDTO activeOrder(@RequestParam String token) {
        // Athugum hvort það sé til buyer fyrir gefið token
        Buyer b = buyerService.findByToken(token);
        if (b == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        // Ef við komumst hingað þá er buyer loggaður inn
        List<Order> orderList = orderService.findByBuyerAndActive(b, true);
        if (orderList.isEmpty()) {
            Order o = new Order(b);
            o.setActive(true);
            orderList.add(o);
        }
        Order o = orderList.get(0);
        // Vörpum order yfir í DTO hlut
        OrderDTO orderDTO = new OrderDTO(o);
        orderDTO.setTotalPrice(orderService.totalPrice(o));
        return orderDTO;
    }

    @PostMapping("/confirm")
    OrderDTO confirmOrder(@RequestParam String token) {
        // Athugum hvort það sé til buyer fyrir gefið token
        Buyer b = buyerService.findByToken(token);
        if (b == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        Order o = orderService.findByBuyerAndActive(b, true).get(0);
        o.setTimeOfPurchase(LocalDateTime.now());
        o.setTotalPrice(orderService.totalPrice(o));
        o = orderService.confirmOrder(o);
        if (o == null) {
            // Eitthver hluttur í körfunni er active=false eða currentAmount < amount
            return null;
        }
        // Vörpum order yfir í DTO hlut
        OrderDTO orderDTO = new OrderDTO(o);
        orderDTO.setTotalPrice(orderService.totalPrice(o));
        return orderDTO;
    }

    /**
     * Fall sem breytir amount hjá orderItem og eyðir því ef amount <= 0. Þetta fall er notað
     * til að eyða orderItems líka.
     * @param orderItemId auðkenni fyrir orderItem
     * @param amount nýja magnið sem orderItem á að fá
     * @param token auðkenni fyrir innskráðan notanda
     * @return skilar uppfærðu orderi (þ.e. búið er að fjarlæga eða setja nýtt amount á eitthvað
     * orderItem)
     */
    @PostMapping("/changeItem/{orderItemId}")
    OrderDTO changeOrderItem(@PathVariable long orderItemId, @RequestParam double amount, @RequestParam String token) {
        // Athugum hvort það sé til buyer fyrir gefið token
        Buyer b = buyerService.findByToken(token);
        if (b == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        Optional<OrderItem> orderItem = orderItemService.findById(orderItemId);
        if(orderItem.isEmpty()) {
            return null;
        }
        if (amount <= 0) {
            orderItemService.delete(orderItem.get());
        } else {
            orderItem.get().setAmount(amount);
            // Vistum breytta item-ið í gagnagrunninn
            orderItemService.save(orderItem.get());
        }
        // Sækjum order aftur núna með breytt item
        List<Order> orderList = orderService.findByBuyerAndActive(b, true);
        Order o = orderList.get(0);
        OrderDTO orderDTO = new OrderDTO(o);
        // Reiknum út nýtt total price
        orderDTO.setTotalPrice(orderService.totalPrice(o));
        return orderDTO;
    }

    @GetMapping("")
    List<OrderDTO> all() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            ordersDTO.add(new OrderDTO(order));
        }
        return ordersDTO;
    }

    @GetMapping("/buyer")
    List<OrderDTO> getBuyerOrders(@RequestParam long buyerId) {

        Optional<Buyer> buyerCheck = buyerService.findById(buyerId);
        if (buyerCheck.isEmpty()) {
            return null;
        }

        Buyer buyer = buyerCheck.get();
        BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail());

        List<Order> orders = orderService.findByBuyerAndActive(buyer, false);
        List<OrderDTO> ordersDTO = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            ordersDTO.add(new OrderDTO(order));
        }
        return ordersDTO;
    }

    @PostMapping("/addToCart/{advertisementId}")
    OrderItemDTO addToCart(@PathVariable long advertisementId, @RequestParam double amount, @RequestParam String token) {
        // Athugum hvort það sé til buyer fyrir gefið token
        Buyer b = buyerService.findByToken(token);
        if (b == null) {
            System.out.println("Notandi hefur ekki rétt token");
            return null;
        }
        // Byrjum að tékka hvort til er Order sem er virkt fyrir Buyer b???
        List<Order> exists = orderService.findByBuyerAndActive(b, true);
        if (exists.isEmpty()) { // Ekkert til virkt order fyrir buyer
            Order newOrder = new Order(b);
            orderService.save(newOrder);
        }
        //Náum í order hjá þessum kaupanda til að bæta orderItem við það order
        Order o = orderService.findByBuyerAndActive(b, true).get(0);
        Optional<Advertisement> adCheck = adService.findById(advertisementId);
        if(adCheck.isEmpty()) {
            return null;
        }
        Advertisement advertisement = adCheck.get();
        OrderItem orderItem = new OrderItem(advertisement, amount, o);
        orderItemService.save(orderItem);
        OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem);
        return orderItemDTO;
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.RestController;


import is.hi.hbv501g.matbjorg.matbjorg.DTO.BuyerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.OrderDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.OrderItemDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/orders")
public class OrderRestController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private BuyerService buyerService;

    public OrderRestController(OrderService orderService, OrderItemService orderItemService, BuyerService buyerService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.buyerService = buyerService;
    }

    @GetMapping("")
    List<OrderDTO> all() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            List<OrderItem> orderItems = order.getItems();
            List<OrderItemDTO> orderItemsDTO = new ArrayList<>();

            Buyer buyer = order.getBuyer();
            BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail());

            for (int j = 0; j < orderItems.size(); j++) {
                OrderItem orderItem = orderItems.get(j);
                orderItemsDTO.add(new OrderItemDTO(orderItem.getId(), orderItem.getAdvertisement().getId(), orderItem.getAmount()));
            }
            ordersDTO.add(new OrderDTO(order.getId(), orderItemsDTO, buyerDTO, order.isActive(), order.getTimeOfPurchase(), order.getTotalPrice()));
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

        List<Order> orders = orderService.findByBuyer(buyer);
        List<OrderDTO> ordersDTO = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            List<OrderItem> orderItems = order.getItems();
            List<OrderItemDTO> orderItemsDTO = new ArrayList<>();

            for (int j = 0; j < orderItems.size(); j++) {
                OrderItem orderItem = orderItems.get(j);
                orderItemsDTO.add(new OrderItemDTO(orderItem.getId(), orderItem.getAdvertisement().getId(), orderItem.getAmount()));
            }
            ordersDTO.add(new OrderDTO(order.getId(), orderItemsDTO, buyerDTO, order.isActive(), order.getTimeOfPurchase(), order.getTotalPrice()));
        }
        return ordersDTO;
    }

    @GetMapping("/buyer/active")
    OrderDTO getActiveOrder(@RequestParam long buyerId) {

        Optional<Buyer> buyerCheck = buyerService.findById(buyerId);
        if (buyerCheck.isEmpty()) {
            return null;
        }

        Buyer buyer = buyerCheck.get();
        BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail());

        List<Order> orders = orderService.findByBuyerAndActive(buyer, true);
        Order activeOrder = orders.get(0);
        OrderDTO activeOrderDTO;


        List<OrderItem> orderItems = activeOrder.getItems();
        List<OrderItemDTO> orderItemsDTO = new ArrayList<>();

        for (int j = 0; j < orderItems.size(); j++) {
            OrderItem orderItem = orderItems.get(j);
            orderItemsDTO.add(new OrderItemDTO(orderItem.getId(), orderItem.getAdvertisement().getId(), orderItem.getAmount()));
        }
        activeOrderDTO = new OrderDTO(activeOrder.getId(), orderItemsDTO, buyerDTO, activeOrder.isActive(), activeOrder.getTimeOfPurchase(), activeOrder.getTotalPrice());

        return activeOrderDTO;
    }


}

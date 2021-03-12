package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.OrderDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private BuyerService buyerService;

    public OrderRestController(OrderService orderService, OrderItemService orderItemService, BuyerService buyerService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.buyerService = buyerService;
    }

    /**
     * Fall sem sækir Order fyrir Buyer með auðkenni id
     * @param token auðkenningar token fyrir buyer
     * @return active order fyrir viðkomandi buyer
     */
    @GetMapping("")
    OrderDTO one(@RequestParam String token) {
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
}

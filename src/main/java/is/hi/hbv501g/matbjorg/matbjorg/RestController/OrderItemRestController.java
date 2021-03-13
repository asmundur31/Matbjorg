package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/orderItem")
public class OrderItemRestController {
    private AdvertisementService advertisementService;
    private OrderItemService orderItemService;
    private OrderService orderService;

    public OrderItemRestController(AdvertisementService advertisementService, OrderItemService orderItemService, OrderService orderService) {
        this.advertisementService = advertisementService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

}

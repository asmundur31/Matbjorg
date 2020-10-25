package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.*;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderItemController {
    private OrderItemService orderItemService;
    private AdvertisementService advertisementService;
    private OrderService orderService;

    public OrderItemController(OrderItemService orderItemService, AdvertisementService advertisementService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.advertisementService = advertisementService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orderitem/{id}", method = RequestMethod.GET)
    public String addToOrderGET(@PathVariable long id, Model model) {
        Optional<Advertisement> ad = advertisementService.findById(id);
        if(ad.isEmpty()) {
            model.addAttribute("advertisements", advertisementService.findAll());
            return "redirect:/advertisements";
        }
        model.addAttribute("advertisement", ad.get());
        model.addAttribute("orderItem", new OrderItem());
        return "orderItemForm";
    }

    @RequestMapping(value = "/orderitem/{id}", method = RequestMethod.POST)
    public String addToOrderPOST(@PathVariable long id, @Valid OrderItem orderItem, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/orderitem/"+id;
        }
        // Sækjum buyer sem ætlar að kaupa
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        // Byrjum að tékka hvor buyer er búinn að búa til Order
        List<Order> exists = orderService.findByBuyer(b);
        if(exists.size() == 0) {
            // Hér ef þetta er fyrsta item sem buyer kaupir
        } else {
            // Hér ef hann hefur keypt áður
        }
        orderItemService.save(orderItem, b);
        model.addAttribute("advertisements", advertisementService.findAll());
        return "redirect:/advertisements";
    }
}

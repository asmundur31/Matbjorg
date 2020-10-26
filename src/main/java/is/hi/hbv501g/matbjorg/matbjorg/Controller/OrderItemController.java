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

    @RequestMapping(value = "/orderitem/{advertisementId}", method = RequestMethod.GET)
    public String addToOrderGET(@PathVariable long advertisementId, Model model) {
        Optional<Advertisement> ad = advertisementService.findById(advertisementId);
        if(ad.isEmpty()) {
            model.addAttribute("advertisements", advertisementService.findAll());
            return "redirect:/advertisements";
        }
        model.addAttribute("advertisement", ad.get());
        model.addAttribute("orderItem", new OrderItem());
        return "orderItemForm";
    }

    @RequestMapping(value = "/orderitem/{advertisementId}", method = RequestMethod.POST)
    public String addToOrderPOST(@Valid OrderItem orderItem, @PathVariable long advertisementId, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/orderitem/"+advertisementId;
        }
        // Sækjum buyer sem ætlar að kaupa
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if(b == null) {
            return "redirect:/login";
        }
        // Byrjum að tékka hvort til er Order sem er virkt fyrir Buyer b
        Order exists = orderService.findByBuyerAndActive(b, true);
        if(exists == null) { // Ekkert til virkt order fyrir buyer
            exists = new Order(b);
            orderService.save(exists);
        }

        // Sækjum advertisement sem er verið að kaupa
        Optional<Advertisement> ad = advertisementService.findById(advertisementId);
        orderItem.setAdvertisement(ad.get());
        orderItem.setOrder(exists);

        // Vista OrderItem í gangnagrunn
        orderItemService.save(orderItem);

        // Förum í körfuna
        model.addAttribute("order", exists);
        return "redirect:/order";
    }
}

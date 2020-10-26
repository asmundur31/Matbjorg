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
        // Sækjum advertisement sem er verið að kaupa
        Optional<Advertisement> ad = advertisementService.findById(id);
        orderItem.setAdvertisement(ad.get());

        // Vista OrderItem í gangnagrunn
        orderItemService.save(orderItem);

        // Byrjum að tékka hvor buyer á til Order sem er virkt
        Order exists = orderService.findByBuyerAndActive(b, true);
        if(exists == null) { // Ekkert til virkt order fyrir buyer
            exists = new Order(b);
        }

        // Bætum item-inu við listann
        orderService.insertItem(exists, orderItem);
        orderService.save(exists);

        // Förum í körfuna
        model.addAttribute("order", exists);
        return "redirect:/order";
    }
}

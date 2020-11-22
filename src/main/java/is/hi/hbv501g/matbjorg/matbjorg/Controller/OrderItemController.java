package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.*;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller sem grípur allar fyrirspurnir sem koma að OrderItem
 */
@Controller
public class OrderItemController {
    /**
     * orderItemService er þjónusta fyrir OrderItem
     * advertisementService er þjónusta fyrir Advertisement
     * orderService er þjónusta fyrir Order
     */
    private OrderItemService orderItemService;
    private AdvertisementService advertisementService;
    private OrderService orderService;

    /**
     * Smiður fyrir OrderItemController
     *
     * @param orderItemService     þjónusta fyrir OrderItem
     * @param advertisementService þjónusta fyrir Advertisement
     * @param orderService         þjónusta fyrir Order
     */
    @Autowired
    public OrderItemController(OrderItemService orderItemService, AdvertisementService advertisementService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.advertisementService = advertisementService;
        this.orderService = orderService;
    }

    /**
     * Grípur fyrirspurn þegar notanda langar að skoða ákveðna auglýsingu betur.
     * @param advertisementId aukenni auglýsingar sem notanda langar að skoða
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return endursendir notandann á auglýsingasíðunna ef auglýsing finnst ekki annars streng sem er nafnið á html
     *         skránni sem birtir auglýsinguna sem var valin
     */
    @RequestMapping(value = "/orderitem/{advertisementId}", method = RequestMethod.GET)
    public String addToOrderGET(@PathVariable long advertisementId, Model model, HttpSession session) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if(buyer == null) {
            return "redirect:/";
        }
        model.addAttribute("loggedInUser", buyer);
        model.addAttribute("userType", "buyer");
        
        Optional<Advertisement> ad = advertisementService.findById(advertisementId);
        if(ad.isEmpty()) {
            model.addAttribute("advertisements", advertisementService.findAll());
            return "redirect:/advertisements";
        }
        model.addAttribute("advertisement", ad.get());
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setAmount(0.25);
        model.addAttribute("orderItem", newOrderItem);
        return "orderItemForm";
    }

    /**
     * Grípur fyrirspurn þegar notanda langar að bæta við orderItem í körfuna sína.
     * @param orderItem hlutur af taginu OrderItem sem á að setja í körfuna
     * @param advertisementId hlutur af taginu long sem auðkennir auglýsinguna sem var verið að skoða
     * @param result hlutur af taginu BindingResult sem geymir upplýsingar um villur í OrderItem hlutnum
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef orderItem hefur villur þá erum notandinn sendur aftur á auglýsinguna sem hann var að skoða
     *         ef enginn er loggaður inn þá er notandinn sendur í login
     *         annars er sendum við notandann í körfuna sína
     */
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
        List<Order> exists = orderService.findByBuyerAndActive(b, true);
        if (exists.isEmpty()) { // Ekkert til virkt order fyrir buyer
            Order newOrder = new Order(b);
            orderService.save(newOrder);
        }

        Order order = orderService.findByBuyerAndActive(b, true).get(0);
        // Sækjum advertisement sem er verið að kaupa
        Optional<Advertisement> ad = advertisementService.findById(advertisementId);
        orderItem.setAdvertisement(ad.get());
        orderItem.setOrder(order);

        // Vista OrderItem í gangnagrunn
        orderItemService.save(orderItem);

        // Förum í körfuna
        model.addAttribute("order", order);
        return "redirect:/order";
    }
}

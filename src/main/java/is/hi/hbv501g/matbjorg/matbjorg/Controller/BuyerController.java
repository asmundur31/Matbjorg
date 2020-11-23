package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * BuyerController er controller klasi sem sér um birtingu á heimasvæði kaupanda
 */

@Controller
public class BuyerController {
    /**
     * buyerService er þjónusta fyrir Buyer
     * orderService er þjónusta fyrir Order
     */
    private BuyerService buyerService;
    private OrderService orderService;

    /**
     * Smiður fyrir BuyerController
     *
     * @param buyerService þjónusta fyrir Buyer
     * @param orderService þjónusta fyrir Order
     */
    @Autowired
    public BuyerController(BuyerService buyerService, OrderService orderService) {
        this.buyerService = buyerService;
        this.orderService = orderService;
    }

    /**
     * Grípur fyrirspurn um að fara á heimasvæði kaupanda
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return skilar okkur á forsíðuna ef notandi er ekki Buyer. Annars förum við á heimasvæði kaupanda
     */
    @RequestMapping(value = "/profile/buyer")
    public String buyerProfile(Model model, HttpSession session) {
        Buyer buyer = (Buyer) session.getAttribute("loggedInUser");
        if(buyer == null) {
            return "redirect:/";
        }
        model.addAttribute("buyer", buyer);
        model.addAttribute("previousOrders", orderService.findByBuyerAndActive(buyer, false));
        return "buyerProfile";
    }
}

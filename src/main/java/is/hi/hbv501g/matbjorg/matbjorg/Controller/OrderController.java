package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder(Model model, HttpSession session) {
        // Athugum að hér kemur villa ef loggedInUser er seller
        // Þurfum að laga þetta
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if(b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true);
        model.addAttribute("order", o);
        model.addAttribute("buyer", b);
        model.addAttribute("totalPrice", orderService.totalPrice(o));
        return "order";
    }

    @RequestMapping(value = "/receipt")
    public String confirmOrder(Model model, HttpSession session) {
        // Athugum að hér kemur villa ef loggedInUser er seller
        // Þurfum að laga þetta
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if(b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true);
        o = orderService.confirmOrder(o);
        if(o == null) {
            // Eitthver villuskilaboð um að eitthver hluttur í körfunni er active=false eða currentAmount < amount
            return "redirect:/order";
        }
        model.addAttribute("order", o);
        model.addAttribute("time", LocalDateTime.now());
        model.addAttribute("totalPrice", orderService.totalPrice(o));
        return "kvittun";
    }
}

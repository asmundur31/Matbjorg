package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder(Model model, HttpSession session) {
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if(b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true);
        model.addAttribute("order", o);
        model.addAttribute("buyer", b);
        return "order";
    }
}

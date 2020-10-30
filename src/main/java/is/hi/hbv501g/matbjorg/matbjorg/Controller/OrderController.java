package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;

    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder(Model model, HttpSession session) {
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true);
        model.addAttribute("order", o);
        model.addAttribute("buyer", b);
        return "order";
    }

    @RequestMapping(value = "/removeFromOrder/{id}")
    public String removeOrderItem(@PathVariable("id") long id, HttpSession session) {

        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }

        // Finnum orderItem sem á að eyða, villuskilaboð ef ólöglegt OrderItem id
        OrderItem orderItem = orderItemService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid OrderItem ID"));

        // Eyðum OrderItem-inu
        orderItemService.delete(orderItem);

        // Finna active order fyrir Buyer b
        Order o = orderService.findByBuyerAndActive(b, true);
        // Ef Order inniheldur engin OrderItem þá eyðum við Order-inu
        if (o.getItems().isEmpty()) {
            orderService.delete(o);
        }

        return "redirect:/order";
    }

    @RequestMapping(value = "changeAmount/{orderItemId}")
    public String changeOrderItemAmount(@PathVariable long orderItemId, @RequestParam double amount, HttpSession session) {

        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }

        OrderItem orderItem = orderItemService.findById(orderItemId).orElseThrow(() -> new IllegalArgumentException("Invalid OrderItem ID"));
        orderItem.setAmount(orderItem.getAmount() + amount);
        if (orderItem.getAmount() <= 0) {
            orderItemService.delete(orderItem);
        } else {
            orderItemService.save(orderItem);
        }

        Order o = orderService.findByBuyerAndActive(b, true);

        if (o.getItems().isEmpty()) {
            orderService.delete(o);
        }

        return "redirect:/order";
    }

}

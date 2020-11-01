package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;

    
  @Autowired
  public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder(Model model, HttpSession session) {
        // Athugum að hér kemur villa ef loggedInUser er seller
        // Þurfum að laga þetta
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true);
        model.addAttribute("order", o);
        model.addAttribute("buyer", b);
        model.addAttribute("totalPrice", orderService.totalPrice(o));
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

        return "redirect:/order";
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

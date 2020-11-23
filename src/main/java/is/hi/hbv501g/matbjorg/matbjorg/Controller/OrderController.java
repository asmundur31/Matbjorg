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
import java.util.List;

/**
 * OrderController er Controller klasi sem grípur allar fyrirspurnir sem tengjast körfu kaupanda
 */
@Controller
public class OrderController {

    /**
     * orderService er sú þjónusta sem er boðið uppá fyrir Order
     * orderItemService er sú þjónusta sem er boðið uppá fyrir OrderItem
     * Þurfum þessar þjónustur þegar við erum að vinna með körfu notanda
     */
    private OrderService orderService;
    private OrderItemService orderItemService;

    /**
     * Smiður fyrir OrderController
     *
     * @param orderService     þjónusta fyrir Order
     * @param orderItemService þjónusta fyrir OrderItem
     */
    @Autowired
    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    /**
     * Grípur fyrirspurn þegar notandi vill skoða körfuna sína og birtir síðu fyrir hana - GET fall
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki loggaður inn sem  Buyer þá er hann endursendur á login síðu,
     * annars skilum við streng sem er nafn html síðunnar sem verður birt
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder(Model model, HttpSession session) {
        // Athugum að hér kemur villa ef loggedInUser er seller
        // Þurfum að laga þetta
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        List<Order> orderList = orderService.findByBuyerAndActive(b, true);
        if (orderList.isEmpty()) {
            Order o = new Order(b);
            o.setActive(true);
            orderList.add(o);
        }
        Order o = orderList.get(0);
        model.addAttribute("order", o);
        model.addAttribute("buyer", b);
        model.addAttribute("totalPrice", orderService.totalPrice(o));
        return "order";
    }

    /**
     * Grípur fyrirspurn þegar notandi vill fjarlægja hlut úr körfunni sinni
     *
     * @param id      long tala sem er id fyrir OrderItem sem á að fjarlægja
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki loggaður inn sem Buyer er hann endursendur á login síðu,
     * annars er hann endursendur á körfu síðu þar hann sér uppfærða körfu
     */
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

    /**
     * Grípur fyrirspurn þegar notandi vill breyta magni af einhverjum hlut í körfunni sinni
     *
     * @param orderItemId long tala sem er id fyrir OrderItem-ið sem á að breyta magninu af
     * @param amount      double tala sem segir til um hversu mikið á að breyta magninu
     * @param session     hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki loggaður inn sem Buyer er hann endursendur á login síðu,
     * annars er hann endursendur á körfu síðu þar hann sér uppfærða körfu
     */
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

    /**
     * Grípur fyrirspurn þegar notandi vill kaupa það sem er í körfunni sinni
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki loggaður inn sem Buyer er hann endursendur á login síðu,
     * ef það er OrderItem í körfunni sem tilheyrir annað hvort auglýsingu sem er útrunnin
     * eða er uppselt í því magni sem er valið þá er notandi endursendur á körfu síðu,
     * annars er streng skilað sem er nafnið á html síðu sem birtir kvittun
     */
    @RequestMapping(value = "/receipt")
    public String confirmOrder(Model model, HttpSession session) {
        // Athugum að hér kemur villa ef loggedInUser er seller
        // Þurfum að laga þetta
        Buyer b = (Buyer) session.getAttribute("loggedInUser");
        if (b == null) {
            // Það þarf að vera loggaður inn buyer
            return "redirect:/login";
        }
        Order o = orderService.findByBuyerAndActive(b, true).get(0);
        o.setTimeOfPurchase(LocalDateTime.now());
        o.setTotalPrice(orderService.totalPrice(o));
        o = orderService.confirmOrder(o);

        if (o == null) {
            // Eitthver villuskilaboð um að eitthver hluttur í körfunni er active=false eða currentAmount < amount
            return "redirect:/order";
        }

        model.addAttribute("order", o);
        model.addAttribute("time", LocalDateTime.now());
        model.addAttribute("totalPrice", orderService.totalPrice(o));
        return "kvittun";
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController er controller klasi sem grípur fyrirspurnina þegar við ræsum síðuna
 */

@Controller
public class HomeController {

    /**
     * Smiður fyrir HomeController
     */
    @Autowired
    public HomeController() {
    }

    /**
     * Grípur fyrirspurnina þegar við förum á aðalsíðuna
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return skilar okkur á html-síðuna Velkominn
     */
    @RequestMapping("/")
    public String Home(Model model, HttpSession session) {
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        model.addAttribute("userType", session.getAttribute("userType"));
        return "Velkominn";
    }
}

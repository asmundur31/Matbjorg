package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
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
    private AdvertisementService advertisementService;

    /**
     * Smiður fyrir HomeController
     * @param advertisementService
     */
    @Autowired
    public HomeController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
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
        String userType = (String) session.getAttribute("userType");
        if(userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }
        model.addAttribute("adToday", advertisementService.createdToday());
        return "Velkominn";
    }
}

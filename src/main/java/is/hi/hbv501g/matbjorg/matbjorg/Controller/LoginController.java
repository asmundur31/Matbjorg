package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.User;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * LoginController er Controller klasi sem grípur allar fyrirspurnir sem tengjast login og signup
 */
@Controller
public class LoginController {
    /**
     * sellerService er sú þjónusta sem er boðinn uppá fyrir Seller
     * buyerService er sú þjónusta sem er boðinn uppá fyrir Buyer
     *
     * Þegar við erum að logga inn eða signa upp notendur þá þurfum við á þessum þjónustum að halda
     */
    private SellerService sellerService;
    private BuyerService buyerService;

    /**
     * Smiður fyrir LoginController
     * @param sellerService þjónusta fyrir Seller
     * @param buyerService þjónusta fyrir Buyer
     */
    @Autowired
    public LoginController(SellerService sellerService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    /**
     * Grípur fyrirspurn þegar notandi vill logga sig inn og birtir login síðu - GET fall
     * @param user hlutur af taginu User sem mun geyma email og password í innskráningu
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPageGET(User user) {
        return "loginPage";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á Skrá inn - POST fall
     * @param user hlutur af taginu User sem geymir email og lykilorð sem var slegið inn
     * @param result hlutur af taginu BindingResult sem geymir upplýsingar um villur í User hlutnum
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef result hefur engar villur þá endursendum við notandann á heimasvæðið annars leyfum við honum að
     *         reyna að logga sig inn aftur
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPagePOST(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "loginPage";
        }
        Seller exists1 = sellerService.login(sellerService.findByEmail(user.getEmail()));
        Buyer exists2 = buyerService.login(buyerService.findByEmail(user.getEmail()));
        if (exists1 == null && exists2 == null) {
            return "loginPage";
        } else if(exists2 == null) {
            session.setAttribute("loggedInUser", exists1);
            session.setAttribute("userType", "seller");
        } else {
            session.setAttribute("loggedInUser", exists2);
            session.setAttribute("userType", "buyer");
        }
        return "redirect:/";
    }

    /**
     * Grípur fyrirspurn þegar notandinn loggar sig út
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return endursendum notandann aftur á heimasvæðið
     */
    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("loggedInUser");
        session.removeAttribute("userType");
        return "redirect:/";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ákveður að signa sig upp
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping(value = "/signup")
    public String signup(Model model) {
        return "signup";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ákveður að búa til notanda sem er Seller - GET fall
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping(value = "/signup/newSeller", method = RequestMethod.GET)
    public String signupSellerGET(Model model) {
        model.addAttribute("userType", "Seller");
        model.addAttribute("user", new Seller());
        return "signupUser";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ákveður að búa til notanda sem er Buyer - GET fall
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping(value = "/signup/newBuyer", method = RequestMethod.GET)
    public String signupBuyerGET(Model model) {
        model.addAttribute("userType", "Buyer");
        model.addAttribute("user", new Buyer());
        return "signupUser";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á Staðfesta í nýskráningu - POST fall
     * @param user hlutur af taginu Seller sem geymir upplýsingar sem notandinn sló inn
     * @param result hlutur af taginu BindingResult sem geymir upplýsingar um villur í Seller hlutnum
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return Ef enginn Seller eða Buyer er til með innslegið netfang þá endursendum við notandann á loginn síðuna
     *         annars leyfum við notanda að reyna aftur
     */
    @RequestMapping(value = "/signup/newSeller", method = RequestMethod.POST)
    public String signupSellerPOST(@Valid Seller user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/signup/newSeller";
        }
        Seller exists1 = sellerService.findByEmail(user.getEmail());
        Buyer exists2 = buyerService.findByEmail(user.getEmail());
        if(exists1 == null && exists2 == null) {
            sellerService.save(user);
        } else {
            return "redirect:/signup/newSeller";
        }
        return "redirect:/login";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á Staðfesta í nýskráningu - POST fall
     * @param user hlutur af taginu Buyer sem geymir upplýsingar sem notandinn sló inn
     * @param result hlutur af taginu BindingResult sem geymir upplýsingar um villur í Buyer hlutnum
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return Ef enginn Seller eða Buyer er til með innslegið netfang þá endursendum við notandann á loginn síðuna
     *         annars leyfum við notanda að reyna aftur
     */
    @RequestMapping(value = "/signup/newBuyer", method = RequestMethod.POST)
    public String signupBuyerPOST(@Valid Buyer user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/signup/newBuyer";
        }
        Buyer exists1 = buyerService.findByEmail(user.getEmail());
        Seller exists2 = sellerService.findByEmail(user.getEmail());
        if(exists1 == null && exists2 == null) {
            buyerService.save(user);
        } else {
            return "redirect:/signup/newBuyer";
        }
        return "redirect:/login";
    }

    /**
     * Þurfum í raun ekki þetta fall
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model) {
        String usertype = (String) session.getAttribute("userType");
        if(usertype == null) {
            return "redirect:/";
        }
        if(usertype.equals("Seller")) {
            Seller sessionUser = (Seller) session.getAttribute("loggedInUser");
            if(sessionUser  != null){
                model.addAttribute("loggedInUser", sessionUser);
                model.addAttribute("userType", usertype);
                return "loggedInUser";
            }
        } else {
            Buyer sessionUser = (Buyer) session.getAttribute("loggedInUser");
            if (sessionUser != null) {
                model.addAttribute("loggedInUser", sessionUser);
                model.addAttribute("userType", usertype);
                return "loggedInUser";
            }
        }
        return "redirect:/";
    }
}

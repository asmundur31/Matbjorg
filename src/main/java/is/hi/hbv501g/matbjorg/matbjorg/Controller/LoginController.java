package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
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

@Controller
public class LoginController {
    private SellerService sellerService;
    private BuyerService buyerService;

    @Autowired
    public LoginController(SellerService sellerService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(User user) {
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "loginPage";
        }
        Seller exists1 = sellerService.login(new Seller(user.getEmail(), user.getPassword()));
        Buyer exists2 = buyerService.login(new Buyer(user.getEmail(), user.getPassword()));
        if (exists1 == null && exists2 == null) {
            System.out.println("Notandi með " + user.getEmail() + " er ekki til eða rangt lykilorð");
            return "loginPage";
        } else if(exists2 == null) {
            System.out.println("Notandi með " + user.getEmail() + " er Seller");
            session.setAttribute("loggedInUser", exists1);
            session.setAttribute("usertype", "seller");
        } else {
            System.out.println("Notandi með " + user.getEmail() + " er Buyer");
            session.setAttribute("loggedInUser", exists2);
            session.setAttribute("usertype", "buyer");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "redirect:/";
    }

    @RequestMapping(value = "/signup")
    public String signup(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signup/newseller", method = RequestMethod.GET)
    public String signupSeller(Model model) {
        model.addAttribute("usertype", "newseller");
        model.addAttribute("user", new Seller());
        return "signupUser";
    }

    @RequestMapping(value = "/signup/newbuyer", method = RequestMethod.GET)
    public String signupBuyer(Buyer user,  Model model) {
        model.addAttribute("usertype", "newbuyer");
        model.addAttribute("user", new Buyer());
        return "signupUser";
    }

    @RequestMapping(value = "/signup/newseller", method = RequestMethod.POST)
    public String signupSeller(@Valid Seller user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signupUser";
        }
        Seller exists1 = sellerService.findByEmail(user.getEmail());
        Buyer exists2 = buyerService.findByEmail(user.getEmail());
        if(exists1 == null && exists2 == null) {
            sellerService.save(user);
        } else {
            System.out.println("Notandi með þetta netfang er til");
            return "signupUser";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/signup/newbuyer", method = RequestMethod.POST)
    public String signupBuyer(@Valid Buyer user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signupUser";
        }
        Buyer exists1 = buyerService.findByEmail(user.getEmail());
        Seller exists2 = sellerService.findByEmail(user.getEmail());
        if(exists1 == null && exists2 == null) {
            buyerService.save(user);
        } else {
            System.out.println("Notandi með þetta netfang er til");
            return "signupUser";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedin(HttpSession session, Model model) {
        String usertype = (String) session.getAttribute("usertype");
        if(usertype == null) {
            return "redirect:/";
        }
        if(usertype.equals("seller")) {
            Seller sessionUser = (Seller) session.getAttribute("loggedInUser");
            if(sessionUser  != null){
                model.addAttribute("loggedinuser", sessionUser);
                model.addAttribute("loggedintype", usertype);
                return "loggedInUser";
            }
        } else {
            Buyer sessionUser = (Buyer) session.getAttribute("loggedInUser");
            if (sessionUser != null) {
                model.addAttribute("loggedinuser", sessionUser);
                model.addAttribute("loggedintype", usertype);
                return "loggedInUser";
            }
        }
        return "redirect:/";
    }
}

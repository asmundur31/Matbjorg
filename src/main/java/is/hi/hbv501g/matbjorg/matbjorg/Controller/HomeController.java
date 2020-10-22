package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private AdvertisementService advertisementService;

    @Autowired
    public HomeController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping("/")
    public String Home(Model model, HttpSession session) {
        model.addAttribute("advertisements", advertisementService.findAll());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        model.addAttribute("userType", session.getAttribute("userType"));
        return "Velkominn";
    }
}

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
        return "Velkominn";
    }

    @RequestMapping(value = "/addadvertisement", method = RequestMethod.POST)
    public String addadvertisement(@Valid Advertisement advertisement, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-advertisement";
        }
        advertisementService.save(advertisement);
        model.addAttribute("advertisements", advertisementService.findAll());
        return "Velkominn";
    }

    @RequestMapping(value = "/addadvertisement", method = RequestMethod.GET)
    public String addadvertisement(Advertisement advertisement) {
        return "add-advertisement";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String addadvertisement(@PathVariable("id") long id, Model model) {
        Advertisement advertisement = advertisementService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid advertisement ID"));
        advertisementService.delete(advertisement);
        model.addAttribute("advertisements", advertisementService.findAll());
        return "Velkominn";
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdvertisementController {
    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @RequestMapping(value = "/advertisements")
    public String advertisements(Model model, HttpSession session) {
        model.addAttribute("advertisements", advertisementService.findAll());
        String userType = (String) session.getAttribute("userType");
        if(userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }
        return "advertisements";
    }

    @RequestMapping(value = "/addadvertisement", method = RequestMethod.GET)
    public String addAdvertisementGET(Model model, HttpSession session) {
        Seller seller = (Seller) session.getAttribute("loggedInUser");
        if(seller == null) {
            return "redirect:/";
        }
        model.addAttribute("advertisement", new Advertisement());
        model.addAttribute("tags", Tag.values());
        return "addAdvertisement";
    }

    @RequestMapping(value = "/addadvertisement", method = RequestMethod.POST)
    public String addAdvertisementPOST(@Valid Advertisement advertisement, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "addAdvertisement";
        }
        advertisementService.save(advertisement, (Seller) session.getAttribute("loggedInUser"));
        model.addAttribute("advertisements", advertisementService.findAll());
        return "redirect:/profile/seller";
    }

    @RequestMapping(value = "/advertisements/delete/{id}", method = RequestMethod.GET)
    public String deleteAdvertisementGET(@PathVariable("id") long id, Model model, HttpSession session) {
        Seller seller = (Seller) session.getAttribute("loggedInUser");
        if(seller == null) {
            return "redirect:/";
        }
        Advertisement advertisement = advertisementService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid advertisement ID"));
        advertisementService.delete(advertisement);
        model.addAttribute("advertisements", advertisementService.findAll());
        return "redirect:/profile/seller";
    }
}

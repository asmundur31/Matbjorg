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

/**
 * AdvertisementController er controller klasi sem sér um breytingar og birtingar á auglýsingum (klasanum Advertisement)
 */

@Controller
public class AdvertisementController {
    /**
     * advertisementService er þjónusta fyrir Advertisement
     */
    private AdvertisementService advertisementService;

    /**
     * Smiður fyrir AdvertisementController
     * @param advertisementService þjónusta fyrir Advertisement
     */
    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     * Grípur fyrirspurn þegar notandi vill skoða auglýsingar
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return sendum notanda á html-síðuna advertisement sem inniheldur allar auglýsingar
     */
    @RequestMapping(value = "/advertisements")
    public String advertisements(Model model, HttpSession session) {
        advertisementService.updateActive();
        model.addAttribute("advertisements", advertisementService.findByActive(true));
        String userType = (String) session.getAttribute("userType");
        if(userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }
        return "advertisements";
    }

    /**
     * Grípur fyrirspurn þegar seller vill bæta við auglýsingu
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki seller förum við á forsíðuna annars förum við á html-síðuna addAdvertisement
     * til að bæta við auglýsingu
     */
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

    /**
     * Grípur fyrirspurn til að bæta við auglýsingu
     * @param advertisement auglýsing sem á að bæta við
     * @param result hlutur af taginu BindingResult sem geymir upplýsingar um villur í Advertisement hlutnum
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef upp kemur villa, þ.e. auglýsing sem átti að bæta við var ekki á réttu formi, þá erum við aftur
     * send á addAdvertisement síðuna. Ef engin villa kemur þá erum við send á heimasvæði hjá seller
     */
    @RequestMapping(value = "/addadvertisement", method = RequestMethod.POST)
    public String addAdvertisementPOST(@Valid Advertisement advertisement, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "addAdvertisement";
        }
        advertisementService.save(advertisement, (Seller) session.getAttribute("loggedInUser"));
        model.addAttribute("advertisements", advertisementService.findByActive(true));
        return "redirect:/profile/seller";
    }

    /**
     * Grípur fyrirspurn til að eyða auglýsingu
     * @param id auðkenning á auglýsingu sem á að eyða
     * @param model hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return skilar okkur á forsíðuna ef við erum ekki seller. Annars förum við á heimasvæði hjá seller
     */
    @RequestMapping(value = "/advertisements/delete/{id}", method = RequestMethod.GET)
    public String deleteAdvertisementGET(@PathVariable("id") long id, Model model, HttpSession session) {
        Seller seller = (Seller) session.getAttribute("loggedInUser");
        if(seller == null) {
            return "redirect:/";
        }
        Advertisement advertisement = advertisementService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid advertisement ID"));
        advertisementService.delete(advertisement);
        model.addAttribute("advertisements", advertisementService.findByActive(true));
        return "redirect:/profile/seller";
    }
}

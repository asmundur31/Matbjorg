package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchController er Controller klasi sem grípur allar fyrirspurnir sem tengjast leit að auglýsingum
 */
@Controller
public class SearchController {
    /**
     * advertisementService er sú þjónusta sem er boðin uppá fyrir Advertisement
     * sellerService er sú þjónusta sem er boðinn uppá fyrir Seller
     * Þegar leitað er að auglýsingum þarf að hafa þessar þjónustur
     */
    private AdvertisementService advertisementService;
    private SellerService sellerService;

    /**
     * Smiður fyrir SearchController
     *
     * @param advertisementService þjónusta fyrir Advertisement
     * @param sellerService        þjónusta fyrir Seller
     */
    @Autowired
    public SearchController(AdvertisementService advertisementService, SellerService sellerService) {
        this.advertisementService = advertisementService;
        this.sellerService = sellerService;
    }

    /**
     * Grípur fyrirspurn þegar notandi vill  leita að auglýsingu og birtir search síðu
     *
     * @param session hlutur af taginu User sem mun geyma email og password í innskráningu
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping("/search")
    public String Search(Model model, HttpSession session) {
        model.addAttribute("advertisements", advertisementService.findByActive(true));
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());

        String userType = (String) session.getAttribute("userType");
        if (userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }

        return "search";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á "Sýna allar auglýsingar" og birtir search síðu með leitarniðurstöðum
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping("/showAllAdvertisements")
    public String showAll(Model model, HttpSession session) {
        model.addAttribute("advertisements", advertisementService.findByActive(true));
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());

        String userType = (String) session.getAttribute("userType");
        if (userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }

        return "search";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á "Leita" eftir að hafa slegið inn leitarstreng og birtir search síðu
     * með leitarniðurstöðum
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @param search  strengur sem verður notaður í leit
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping("/searchByKeyword")
    public String SearchByKeyword(@Param("search") String search, Model model, HttpSession session) {
        List<Advertisement> advertisementList = advertisementService.findByKeyWord(search);
        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());

        String userType = (String) session.getAttribute("userType");
        if (userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }

        return "search";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á "Leita" eftir að hafa hakað við checkbox og birtir search síðu
     * með leitarniðurstöðum
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @param sellers listi af strengjum, inniheldur þá strengi sem hakað var við áður en ýtt var á "Leita"
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping("/filterBySellers")
    public String filterBySellers(@RequestParam List<String> sellers, Model model, HttpSession session) {

        List<Seller> sellerList = new ArrayList<Seller>();

        for (String seller : sellers) {
            sellerList.add(sellerService.findByName(seller));
        }

        List<Advertisement> advertisementList = advertisementService.filterBySellers(sellerList);

        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("sellers", sellerService.findAll());
        model.addAttribute("tags", Tag.values());

        String userType = (String) session.getAttribute("userType");
        if (userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }

        return "search";
    }

    /**
     * Grípur fyrirspurn þegar notandinn ýtir á "Leita" eftir að hafa hakað við checkbox og birtir search síðu
     * með leitarniðurstöðum
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @param tags    listi af strengjum, inniheldur þá strengi sem hakað var við áður en ýtt var á "Leita"
     * @return strengur sem er nafnið á html síðunni sem verður birt
     */
    @RequestMapping("/filterByTags")
    public String filterByTags(@RequestParam List<String> tags, Model model, HttpSession session) {

        List<Advertisement> advertisementList = advertisementService.filterByTags(tags);

        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("sellers", sellerService.findAll());
        model.addAttribute("tags", Tag.values());

        String userType = (String) session.getAttribute("userType");
        if (userType == null) {
            model.addAttribute("userType", "noUser");
        } else {
            model.addAttribute("userType", userType);
        }

        return "search";
    }

}

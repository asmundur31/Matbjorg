package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * SellerController er controller klasi sem grípur allar fyrirspurnir tengdar Seller
 */
@Controller
public class SellerController {
    /**
     * sellerService er þjónusta fyrir Seller
     * advertisementService er þjónusta fyrir Advertisement
     */
    private SellerService sellerService;
    private AdvertisementService advertisementService;

    /**
     * Smiður fyrir SellerController
     *
     * @param sellerService        þjónusta fyrir Seller
     * @param advertisementService þjónusta fyrir Advertisement
     */
    @Autowired
    public SellerController(SellerService sellerService, AdvertisementService advertisementService) {
        this.sellerService = sellerService;
        this.advertisementService = advertisementService;
    }

    /**
     * Grípur fyrirsögn þegar söluaðili vill fara inn á heimasvæði sitt
     *
     * @param model   hlutur af taginu Model sem geymir key-value pör sem hægt er að nota í html template-unum
     * @param session hlutur af taginu HttpSession sem geymir key-value pör
     * @return ef notandi er ekki af taginu Seller þá er hann sendur til baka á upphafs síðu,
     * annars á heimasvæði þar sem birtar eru upplýsingar um hann
     */
    @RequestMapping(value = "/profile/seller")
    public String sellerProfile(Model model, HttpSession session) {
        advertisementService.updateActive();
        Seller seller = (Seller) session.getAttribute("loggedInUser");
        if (seller == null) {
            return "redirect:/";
        }
        model.addAttribute("seller", seller);
        model.addAttribute("advertisementsActive", advertisementService.findByOwnerAndActive(seller, true));
        model.addAttribute("advertisementsInactive", advertisementService.findByOwnerAndActive(seller, false));
        return "sellerProfile";
    }
}

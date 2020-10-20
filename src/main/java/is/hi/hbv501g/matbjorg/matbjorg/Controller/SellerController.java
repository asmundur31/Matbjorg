package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class SellerController {
    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping(value = "/profile/seller")
    public String sellerProfile(Model model, HttpSession session) {
        Seller seller = (Seller) session.getAttribute("loggedInUser");
        if(seller == null) {
            return "redirect:/";
        }
        model.addAttribute("seller", seller);
        return "sellerProfile";
    }
}

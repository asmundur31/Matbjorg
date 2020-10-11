package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class SellerController {
    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping("loginseller")
    public String LoginPage(Model model) {
        model.addAttribute("sellers", sellerService.findAll());
        return "Sellers";
    }

    @RequestMapping(value = "/newseller", method = RequestMethod.POST)
    public String newseller(@Valid Seller seller, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "LoginPageSeller";
        }
        ArrayList<Seller> s = (ArrayList<Seller>) sellerService.findByEmail(seller.getEmail());
        /*if(s.size()==0) {
            sellerService.save(seller);
        } else {
            // Eitthver villu skilaboð um að seller með þetta netfang er til
            // Ég hélt að gagnagrunnurinn myndi sjá um þetta?
            // Það sem gerist er að gagnagrunnurinn yfirskrifar seller með þessum nýja seller
        }*/
        sellerService.save(seller);
        model.addAttribute("sellers", sellerService.findAll());
        return "Sellers";
    }

    @RequestMapping(value = "/newseller", method = RequestMethod.GET)
    public String newseller(Model model) {
        model.addAttribute("seller", new Seller());
        return "LoginPageSeller";
    }
}

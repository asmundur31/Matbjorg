package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.User;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/signup")
public class SignupRestController {
    private SellerService sellerService;
    private BuyerService buyerService;

    public SignupRestController(SellerService sellerService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    /**
     * Fall sem að býr til nýjan notanda með netfangi og lykilorði
     * @param email Strengur með netfangi
     * @param password Strengur með lykilorði
     * @param name Strengur með nafni notanda
     * @return Skilum notanda sem að búið var til annars engu
     */
    @PostMapping("")
    public User signup(@RequestParam String type, @RequestParam String name, @RequestParam String email, @RequestParam String password) {

        if(type.equals("Buyer")) {
            Buyer user_check = buyerService.findByEmail(email);
            if (user_check == null) {
                Buyer buyer = new Buyer(name, email, password);
                buyerService.save(buyer);
                User user = new User(email, password);
                user.setId(buyer.getId());
                user.setType("Buyer");
                buyerService.login(user);
                return user;
            }
            return null;
        } else if(type.equals("Seller")) {
            Seller user_check = sellerService.findByEmail(email);
            if (user_check == null) {
                Seller seller = new Seller(name, email, password);
                sellerService.save(seller);
                User user = new User(email, password);
                user.setId(seller.getId());
                user.setType("Seller");
                sellerService.login(user);
                return user;
            }
            return null;
        }

        return null;


    }
}

package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.User;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/login")
public class LoginRestController {
    private SellerService sellerService;
    private BuyerService buyerService;

    public LoginRestController(SellerService sellerService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    /**
     * Fall sem að athugar hvort að notandi með netfangi sé til og hvort lykilorði passi
     * við það email.
     * @param email Strengur með netfangi
     * @param password Strengur með lykilorði
     * @return Skilum notanda sem skráði sig inn annars engu
     */
    @PostMapping("")
    public User login(@RequestParam String email, @RequestParam String password) {
        User user = new User(email, password);
        Seller seller = sellerService.login(user);
        Buyer buyer = buyerService.login(user);
        if (seller == null && buyer == null) {
            return null;
        } else if (buyer == null) {
            user.setId(seller.getId());
            user.setType("seller");
            user.setToken(seller.getToken());
            return user;
        } else {
            user.setId(buyer.getId());
            user.setType("buyer");
            user.setToken(buyer.getToken());
            return user;
        }
    }
}

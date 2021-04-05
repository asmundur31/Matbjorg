package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.User;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * @param body map sem inniheldur allar upplýsingar sem þarf til að skrá sig
     * @param type strengur með týpu notanda
     * @param email Strengur með netfangi
     * @param password Strengur með lykilorði
     * @param name Strengur með nafni notanda
     * @return Skilum notanda sem að búið var til annars engu
     */
    @PostMapping("")
    public User signup(@RequestParam String type, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestBody Map<String,List<Location>> body) {
        // Pössum uppá að notandi með email sé ekki til
        Seller seller_check = sellerService.findByEmail(email);
        Buyer buyer_check = buyerService.findByEmail(email);
        if (seller_check != null || buyer_check != null) {
            return null;
        }
        // Notandi með email er ekki til ef við komumst hér
        if(type.equals("Buyer")) {
            Buyer buyer = new Buyer(name, email, password);
            buyerService.save(buyer);
            User user = new User(email, password);
            user.setId(buyer.getId());
            user.setType("Buyer");
            Buyer b = buyerService.login(user);
            user.setToken(b.getToken());
            return user;
        } else if(type.equals("Seller")) {
            List<Location> locations = body.get("locations");
            Seller seller = new Seller(name, email, password, locations);
            sellerService.save(seller);
            User user = new User(email, password);
            user.setId(seller.getId());
            user.setType("Seller");
            Seller s = sellerService.login(user);
            user.setToken(s.getToken());
            return user;
        }
        return null;
    }
}

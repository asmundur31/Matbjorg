package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import com.google.gson.Gson;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/buyers")
public class BuyerControllerRest {
    private BuyerService buyerService;
    private OrderService orderService;

    public BuyerControllerRest(BuyerService buyerService, OrderService orderService) {
        this.buyerService = buyerService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    String all() {
        List<Buyer> buyers = buyerService.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(buyers);
        return json;
    }

    @GetMapping("/{id}")
    String one(@PathVariable Long id) {
        Optional<Buyer> buyer = buyerService.findById(id);
        if(buyer.isEmpty()) {
            String notFound = "{ \"Not found\": \"Fann ekki buyer " + id + "\"}";
            return notFound;
        }
        Gson gson = new Gson();
        String json = gson.toJson(buyer);
        return json;
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import com.google.gson.Gson;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.BuyerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.SellerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/buyers")
public class BuyerRestController {
    private BuyerService buyerService;
    private OrderService orderService;

    public BuyerRestController(BuyerService buyerService, OrderService orderService) {
        this.buyerService = buyerService;
        this.orderService = orderService;
    }

    @GetMapping("")
    List<BuyerDTO> all() {
        List<Buyer> buyers = buyerService.findAll();
        List<BuyerDTO> buyersDTO = new ArrayList<>();
        for(int i=0; i<buyers.size(); i++) {
            Buyer buyer = buyers.get(i);
            buyersDTO.add(new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail()));
        }
        return buyersDTO;
    }

    @GetMapping("/{id}")
    BuyerDTO one(@PathVariable Long id) {
        Optional<Buyer> b = buyerService.findById(id);
        if(!b.isEmpty()) {
            Buyer buyer = b.get();
            BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail());
            return buyerDTO;
        }
        return null;
    }
}

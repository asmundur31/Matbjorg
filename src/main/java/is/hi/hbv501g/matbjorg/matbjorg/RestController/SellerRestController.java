package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/sellers")
public class SellerRestController {
    private SellerService sellerService;
    private OrderService orderService;

    public SellerRestController(SellerService sellerService, OrderService orderService) {
        this.sellerService = sellerService;
        this.orderService = orderService;
    }

    @GetMapping("")
    List<Seller> all() {
        List<Seller> sellers = sellerService.findAll();
        return sellers;
    }

    @GetMapping("/{id}")
    Optional<Seller> one(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.findById(id);
        return seller;
    }
}
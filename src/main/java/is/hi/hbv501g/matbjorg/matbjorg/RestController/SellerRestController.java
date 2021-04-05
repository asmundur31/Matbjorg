package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.*;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.*;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    List<SellerDTO> all() {
        List<Seller> sellers = sellerService.findAll();
        List<SellerDTO> sellersDTO = new ArrayList<>();
        for(int i=0; i<sellers.size(); i++) {
            Seller seller = sellers.get(i);
            sellersDTO.add(new SellerDTO(seller));
        }
        return sellersDTO;
    }

    @GetMapping("/{id}")
    SellerDTO one(@PathVariable Long id) {
        Optional<Seller> s = sellerService.findById(id);
        if(!s.isEmpty()) {
            Seller seller = s.get();
            SellerDTO sellerDTO = new SellerDTO(seller);
            return sellerDTO;
        }
        return null;
    }
}
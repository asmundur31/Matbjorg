package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.AdvertisementDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.SellerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/advertisements")
public class AdvertisementRestController {
    private AdvertisementService advertisementService;
    private SellerService sellerService;

    public AdvertisementRestController(AdvertisementService advertisementService, SellerService sellerService) {
        this.advertisementService = advertisementService;
        this.sellerService = sellerService;


    public AdvertisementRestController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;

    }

    @GetMapping("")
    List<AdvertisementDTO> all() {
        List<Advertisement> ads = advertisementService.findAll();
        List<AdvertisementDTO> adsDTO = new ArrayList<>();
        for(int i=0; i<ads.size(); i++) {
            Advertisement ad = ads.get(i);
            adsDTO.add(new AdvertisementDTO(ad));
        }
        return adsDTO;
    }

    @PostMapping("/add")
    AdvertisementDTO addAdvertisement(@RequestParam long sellerId, @RequestParam String name,
                                      @RequestParam String description, @RequestParam double originalAmount,
                                      @RequestParam double price, @RequestParam
                                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime expireDate) {
        Optional<Seller> seller = sellerService.findById(sellerId);
        if (seller.isEmpty()) {
            return null;
        }

        //  engin tögs send með - athuga þetta seinna
        Advertisement ad = new Advertisement(name, seller.get(), description, originalAmount, price, expireDate, null);
        advertisementService.save(ad, seller.get(), null);

        AdvertisementDTO adDTO = new AdvertisementDTO(ad);
        return adDTO;
    }
        
}

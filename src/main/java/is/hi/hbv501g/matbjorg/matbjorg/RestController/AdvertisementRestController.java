package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.AdvertisementDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.LocationService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/rest/advertisements")
public class AdvertisementRestController {
    private AdvertisementService advertisementService;
    private SellerService sellerService;
    private LocationService locationService;

    public AdvertisementRestController(AdvertisementService advertisementService, SellerService sellerService, LocationService locationService) {
        this.advertisementService = advertisementService;
        this.sellerService = sellerService;
        this.locationService = locationService;
    }

    @GetMapping("")
    List<AdvertisementDTO> all() {
        List<Advertisement> ads = advertisementService.findAll();
        List<AdvertisementDTO> adsDTO = new ArrayList<>();
        for (int i = 0; i < ads.size(); i++) {
            Advertisement ad = ads.get(i);
            adsDTO.add(new AdvertisementDTO(ad));
        }
        return adsDTO;
    }

    @GetMapping("/seller")
    List<AdvertisementDTO> getSellerAds(@RequestParam long sellerId) {
        Optional<Seller> seller = sellerService.findById(sellerId);
        List<AdvertisementDTO> sellerAdsDTO = new ArrayList<>();
        if (seller.isEmpty()) {
            return sellerAdsDTO;
        }
        List<Advertisement> ads = advertisementService.findByOwner(seller.get());
        for (Advertisement ad : ads) {
            sellerAdsDTO.add(new AdvertisementDTO(ad));
        }
        return sellerAdsDTO;
    }

    @PostMapping("/add")
    AdvertisementDTO addAdvertisement(@RequestParam long sellerId, @RequestParam String name,
                                      @RequestParam String description, @RequestParam double originalAmount,
                                      @RequestParam double price, @RequestParam
                                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime expireDate,
                                      @RequestParam long locationId, @RequestBody Map<String, List<String>> body) {
        Optional<Seller> seller = sellerService.findById(sellerId);
        if (seller.isEmpty()) {
            return null;
        }
        List<String> t = body.get("tags");
        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < t.size(); i++) {
            tags.add(Tag.valueOf(t.get(i)));
        }
        Location location = locationService.findById(locationId);
        Advertisement ad = new Advertisement(name, seller.get(), description, originalAmount, price, expireDate, tags, location);
        advertisementService.save(ad, seller.get(), null);

        AdvertisementDTO adDTO = new AdvertisementDTO(ad);
        return adDTO;
    }

}

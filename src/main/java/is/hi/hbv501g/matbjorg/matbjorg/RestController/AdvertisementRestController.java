package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.AdvertisementDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.*;
import is.hi.hbv501g.matbjorg.matbjorg.Helpers.BASE64DecodedMultipartFile;

import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.LocationService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.SellerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        advertisementService.updateActive();
        List<Advertisement> ads = advertisementService.findByActive(true);
        List<AdvertisementDTO> adsDTO = new ArrayList<>();
        for (int i = 0; i < ads.size(); i++) {
            Advertisement ad = ads.get(i);
            adsDTO.add(new AdvertisementDTO(ad));
        }
        return adsDTO;
    }

    @GetMapping("/seller")
    List<AdvertisementDTO> getSellerAds(@RequestParam long sellerId) {
        advertisementService.updateActive();
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
    AdvertisementDTO addAdvertisement(@RequestParam String token, @RequestParam String name,
                                      @RequestParam String description, @RequestParam double originalAmount,
                                      @RequestParam double price, @RequestParam
                                      @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime expireDate,
                                      @RequestParam long locationId, @RequestBody Map<String, List<String>> body) {
        Seller seller = sellerService.findByToken(token);
        if (seller == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        List<String> t = body.get("tags");
        String base64encodedPicture = body.get("pic").get(0);
        BASE64DecodedMultipartFile picture = new BASE64DecodedMultipartFile(base64encodedPicture);
        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < t.size(); i++) {
            tags.add(Tag.valueOf(t.get(i)));
        }
        Location location = locationService.findById(locationId);
      
        Advertisement ad = new Advertisement(name, seller, description, originalAmount, price, expireDate, tags, location);
        advertisementService.save(ad, seller, picture);


        AdvertisementDTO adDTO = new AdvertisementDTO(ad);
        return adDTO;
    }

    @GetMapping("/image/{pictureName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String pictureName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = advertisementService.getImage(pictureName);
        System.out.println(resource.getFilename());
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PatchMapping("/change")
    AdvertisementDTO changeAdvertisement(@RequestParam long advertisementId, @RequestParam String token, @RequestParam String name,
                                         @RequestParam String description, @RequestParam double originalAmount,
                                         @RequestParam double price, @RequestParam
                                         @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime expireDate,
                                         @RequestParam long locationId, @RequestBody Map<String, List<String>> body) {
        Seller seller = sellerService.findByToken(token);
        if (seller == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        Optional<Advertisement> advertisement = advertisementService.findById(advertisementId);
        if (advertisement.isEmpty()) {
            return null;
        }
        advertisement.get().setName(name);
        advertisement.get().setDescription(description);
        advertisement.get().setOriginalAmount(originalAmount);
        advertisement.get().setPrice(price);
        advertisement.get().setExpireDate(expireDate);
        Location location = locationService.findById(locationId);
        advertisement.get().setLocation(location);
        String base64encodedPicture = body.get("pic").get(0);
        BASE64DecodedMultipartFile picture = new BASE64DecodedMultipartFile(base64encodedPicture);
        List<String> t = body.get("tags");
        Set<Tag> tags = new HashSet<>();
        for (int i = 0; i < t.size(); i++) {
            tags.add(Tag.valueOf(t.get(i)));
        }
        advertisement.get().setTags(tags);
        Advertisement changedAdvertisement = advertisementService.save(advertisement.get(), seller, picture);

        AdvertisementDTO adDTO = new AdvertisementDTO(changedAdvertisement);

        return adDTO;
    }
}

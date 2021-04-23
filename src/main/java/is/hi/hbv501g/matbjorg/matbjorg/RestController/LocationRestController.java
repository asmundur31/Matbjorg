package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.LocationDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.LocationService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/locations")
public class LocationRestController {
    private LocationService locationService;
    private SellerService sellerService;

    public LocationRestController(LocationService locationService, SellerService sellerService) {
        this.locationService = locationService;
        this.sellerService = sellerService;
    }

    @GetMapping("")
    List<LocationDTO> all() {
        List<Location> locations = locationService.findAll();
        List<LocationDTO> locationsDTO = new ArrayList<>();
        for(int i=0; i<locations.size(); i++) {
            Location location = locations.get(i);
            locationsDTO.add(new LocationDTO(location));
        }
        return locationsDTO;
    }

    @GetMapping("/{sellerId}")
    List<LocationDTO> one(@PathVariable Long sellerId) {
        Optional<Seller> s = sellerService.findById(sellerId);
        if(!s.isEmpty()) {
            Seller seller = s.get();
            List<Location> locations = locationService.findBySeller(seller);
            List<LocationDTO> locationsDTO = new ArrayList<>();
            for(int i=0; i<locations.size(); i++) {
                Location location = locations.get(i);
                locationsDTO.add(new LocationDTO(location));
            }
            return locationsDTO;
        }
        return null;
    }
}
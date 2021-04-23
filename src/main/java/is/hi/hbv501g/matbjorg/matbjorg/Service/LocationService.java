package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    List<Location> findBySeller(Seller seller);
    Location findById(long id);
}

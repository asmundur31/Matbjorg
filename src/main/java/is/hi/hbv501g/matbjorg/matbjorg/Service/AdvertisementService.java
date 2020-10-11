package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    Advertisement save(Advertisement advertisement);
    void delete(Advertisement advertisement);
    List<Advertisement> findAll();
    List<Advertisement> findByName(String name);
    Optional<Advertisement> findById(long id);
}

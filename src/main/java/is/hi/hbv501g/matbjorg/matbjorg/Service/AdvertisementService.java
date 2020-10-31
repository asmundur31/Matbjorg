package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    Advertisement save(Advertisement advertisement, Seller seller);
    void delete(Advertisement advertisement);
    void updateActive();
    List<Advertisement> findAll();
    List<Advertisement> findByActive(boolean active);
    List<Advertisement> findByName(String name);
    Optional<Advertisement> findById(long id);
    List<Advertisement> findByOwner(Seller seller);
    List<Advertisement> findByOwnerAndActive(Seller seller, boolean active);
    List<Advertisement> findByKeyWord(String search);
    List<Advertisement> filterBySellers(List<Seller> sellerList);
    List<Advertisement> filterByTags(List<String> tags);
}

package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.AdvertisementRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImplementation implements AdvertisementService {
    AdvertisementRepository repository;

    @Autowired
    public AdvertisementServiceImplementation(AdvertisementRepository advertisementRepository) {
        this.repository = advertisementRepository;
    }

    @Override
    public List<Advertisement> findByActive(boolean active) {
        return repository.findByActive(active);
    }

    @Override
    public Advertisement save(Advertisement advertisement, Seller seller) {
        advertisement.setOwner(seller);
        advertisement.setCurrentAmount(advertisement.getOriginalAmount());
        return repository.save(advertisement);
    }

    @Override
    public void delete(Advertisement advertisement) {
        repository.delete(advertisement);
    }

   @Override
    public void updateActive() {
        LocalDateTime lt = LocalDateTime.now();
        for(Advertisement ad: repository.findAll()){
            if(ad.getCurrentAmount()<=0 || lt.compareTo(ad.getExpireDate())>0) {
                repository.updateActive(ad.getId(),false);
            }
        }
    }

    @Override
    public List<Advertisement> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Advertisement> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Advertisement> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Advertisement> findByOwner(Seller seller) {
        return repository.findByOwner(seller);
    }

    @Override
    public List<Advertisement> findByOwnerAndActive(Seller seller, boolean active) {
        return repository.findByOwnerAndActive(seller, active);
    }

    @Override
    public List<Advertisement> findByKeyWord(String search) {
        if (search != null) {
            return repository.findByKeyWord(search);
        }
        return repository.findAll();
    }

    @Override
    public List<Advertisement> filterBySellers(List<Seller> sellerList) {
        if (!sellerList.isEmpty()) {
            List<Advertisement> advertisementList = new ArrayList<Advertisement>();
            for (Seller seller : sellerList) {
                List<Advertisement> advertisementBySeller = repository.findByOwner(seller);
                for (Advertisement advertisement : advertisementBySeller) {
                    advertisementList.add(advertisement);
                }
            }
            return advertisementList;
        }
        return repository.findAll();
    }

    @Override
    public List<Advertisement> filterByTags(List<String> tags) {

        if (!tags.isEmpty()) {

            List<Tag> listOfTags = new ArrayList<Tag>();
            for (String tag : tags) {
                listOfTags.add(Tag.valueOf(tag));
            }

            List<Advertisement> advertisements = repository.findAll();
            List<Advertisement> filterResults = new ArrayList<>();

            for (Tag tag : listOfTags) {
                for (Advertisement advertisement : advertisements) {
                    if (advertisement.getTags().contains(tag)) {
                        if (!filterResults.contains(advertisement)) {
                            filterResults.add(advertisement);
                        }
                    }
                }
            }
            return filterResults;
        }
        return repository.findAll();
    }
}

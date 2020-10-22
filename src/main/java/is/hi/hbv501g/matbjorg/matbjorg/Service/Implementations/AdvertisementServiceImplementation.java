package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.AdvertisementRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
}

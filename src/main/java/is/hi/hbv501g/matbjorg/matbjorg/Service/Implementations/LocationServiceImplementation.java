package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.LocationRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImplementation implements LocationService {
    private LocationRepository repository;

    public LocationServiceImplementation(LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Location> findBySeller(Seller seller) {
        return repository.findBySeller(seller);
    }

    @Override
    public Location findById(long id) {
        return repository.findById(id);
    }
}

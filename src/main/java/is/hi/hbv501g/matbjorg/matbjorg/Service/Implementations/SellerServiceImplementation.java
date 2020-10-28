package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.SellerRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImplementation implements SellerService {
    private SellerRepository repository;

    @Autowired
    public SellerServiceImplementation(SellerRepository sellerRepository) {
        this.repository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        return repository.save(seller);
    }

    @Override
    public void delete(Seller seller) {
        repository.delete(seller);
    }

    @Override
    public List<Seller> findAll() {
        return repository.findAll();
    }

    @Override
    public Seller findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Seller findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Seller> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Seller login(Seller seller) {
        if(seller == null) {
            return null;
        }
        Seller exists = findByEmail(seller.getEmail());
        if(exists != null) {
            if(exists.getPassword().equals(seller.getPassword())) {
                return seller;
            }
        }
        return null;
    }
}
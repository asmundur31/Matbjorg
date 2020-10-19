package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.BuyerRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImplementation implements BuyerService {
    private BuyerRepository repository;

    @Autowired
    public BuyerServiceImplementation(BuyerRepository buyerRepository) {
        this.repository = buyerRepository;
    }

    @Override
    public Buyer save(Buyer buyer) {
        return repository.save(buyer);
    }

    @Override
    public void delete(Buyer buyer) {
        repository.delete(buyer);
    }

    @Override
    public List<Buyer> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Buyer> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Buyer findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Buyer> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Buyer login(Buyer buyer) {
        Buyer exists = findByEmail(buyer.getEmail());
        if(exists != null) {
            if(exists.getPassword().equals(buyer.getPassword())) {
                return buyer;
            }
        }
        return null;
    }
}

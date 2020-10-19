package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;

import java.util.List;
import java.util.Optional;

public interface BuyerService {
    Buyer save(Buyer buyer);
    void delete(Buyer buyer);
    List<Buyer> findAll();
    List<Buyer> findByName(String name);
    Buyer findByEmail(String email);
    Optional<Buyer> findById(long id);
    Buyer login(Buyer buyer);
}

package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Seller save(Seller seller);
    void delete(Seller seller);
    List<Seller> findAll();
    Seller findByName(String name);
    Seller findByEmail(String email);
    Optional<Seller> findById(long id);
    Seller login(Seller seller);
}

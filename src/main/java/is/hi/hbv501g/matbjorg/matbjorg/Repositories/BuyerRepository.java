package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer save(Buyer buyer);
    void delete(Buyer buyer);
    List<Buyer> findAll();
    List<Buyer> findByName(String name);
    Buyer findByEmail(String email);
    Optional<Buyer> findById(long id);
}

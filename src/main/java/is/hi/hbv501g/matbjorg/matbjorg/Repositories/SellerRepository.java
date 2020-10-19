package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller save(Seller seller);
    void delete(Seller seller);
    List<Seller> findAll();
    List<Seller> findByName(String name);
    Seller findByEmail(String email);
    Optional<Seller> findById(long id);
}
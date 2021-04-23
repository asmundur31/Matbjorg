package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Review;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);
    List<Review> findAll();
    List<Review> findBySeller(Seller seller);
    List<Review> findByBuyer(Buyer buyer);
    Review findById(long id);
    void delete(Review review);
}

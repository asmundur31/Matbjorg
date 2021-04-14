package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Review;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    List<Review> findAll();
    List<Review> findBySeller(Seller seller);
    List<Review> findByBuyer(Buyer buyer);
    Review findById(long id);
    void delete(Review review);
}

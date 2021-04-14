package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Review;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.ReviewRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewSerivceImplementation implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewSerivceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findBySeller(Seller seller) {
        return reviewRepository.findBySeller(seller);
    }

    @Override
    public List<Review> findByBuyer(Buyer buyer) {
        return reviewRepository.findByBuyer(buyer);
    }

    @Override
    public Review findById(long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}

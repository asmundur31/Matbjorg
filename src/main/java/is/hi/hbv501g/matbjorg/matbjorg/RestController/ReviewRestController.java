package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.BuyerDTO;
import is.hi.hbv501g.matbjorg.matbjorg.DTO.ReviewDTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Review;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Service.BuyerService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.ReviewService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/reviews")
public class ReviewRestController {
    private ReviewService reviewService;
    private SellerService sellerService;
    private BuyerService buyerService;

    public ReviewRestController(ReviewService reviewService, SellerService sellerService, BuyerService buyerService) {
        this.reviewService = reviewService;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    @PostMapping("/add")
    ReviewDTO addReview(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String review = body.get("review");
        long sellerId = Long.parseLong(body.get("sellerId"));
        double rating = Double.parseDouble(body.get("rating"));

        Buyer b = buyerService.findByToken(token);
        if (b == null) {
            System.out.println("notandi hefur ekki rétt token");
            return null;
        }
        Seller s = sellerService.findById(sellerId).get();
        Review r = new Review(review, rating, s, b);
        Review re = reviewService.save(r);
        ReviewDTO reDTO = new ReviewDTO(re);
        return reDTO;
    }

    @GetMapping("/{sellerId}")
    List<ReviewDTO> getReviewsBySeller(@PathVariable Long sellerId) {
        Optional<Seller> s = sellerService.findById(sellerId);
        if (s.isEmpty()) {
            return null;
        }
        List<Review> reviews = reviewService.findBySeller(s.get());
        List<ReviewDTO> reviewsDTO = new ArrayList<>();
        for(int i=0; i<reviews.size(); i++) {
            Review r = reviews.get(i);
            reviewsDTO.add(new ReviewDTO(r));
        }
        return reviewsDTO;
    }
}

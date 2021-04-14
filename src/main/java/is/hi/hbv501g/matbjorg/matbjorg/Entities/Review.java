package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String review;

    @DecimalMin(value = "0")
    @DecimalMax(value = "5")
    private double rating;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Buyer buyer;

    public Review() {
    }

    public Review(@NotEmpty String review, @DecimalMin(value = "0") @DecimalMax(value = "5") double rating, Seller seller, Buyer buyer) {
        this.review = review;
        this.rating = rating;
        this.seller = seller;
        this.buyer = buyer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}

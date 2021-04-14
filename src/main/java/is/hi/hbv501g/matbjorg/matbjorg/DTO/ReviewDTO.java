package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Review;

public class ReviewDTO {
    private long id;
    private String review;
    private double rating;
    private SellerDTO seller;
    private BuyerDTO buyer;

    public ReviewDTO(long id, String review, double rating, SellerDTO seller, BuyerDTO buyer) {
        this.id = id;
        this.review = review;
        this.rating = rating;
        this.seller = seller;
        this.buyer = buyer;
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.review = review.getReview();
        this.rating = review.getRating();
        this.seller = new SellerDTO(review.getSeller());
        this.buyer = new BuyerDTO(review.getBuyer());
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

    public SellerDTO getSeller() {
        return seller;
    }

    public void setSeller(SellerDTO seller) {
        this.seller = seller;
    }

    public BuyerDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDTO buyer) {
        this.buyer = buyer;
    }
}

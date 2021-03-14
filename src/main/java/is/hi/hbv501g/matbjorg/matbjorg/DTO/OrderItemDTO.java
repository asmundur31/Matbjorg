package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

public class OrderItemDTO {
    private long id;
    private AdvertisementDTO advertisement;
    private double amount;

    public OrderItemDTO() {
    }

    public OrderItemDTO(long id, AdvertisementDTO advertisement, double amount) {
        this.id = id;
        this.advertisement = advertisement;
        this.amount = amount;
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.advertisement = new AdvertisementDTO(orderItem.getAdvertisement());
        this.amount = orderItem.getAmount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AdvertisementDTO getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementDTO advertisement) {
        this.advertisement = advertisement;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

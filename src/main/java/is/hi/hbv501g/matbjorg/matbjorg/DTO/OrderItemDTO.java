package is.hi.hbv501g.matbjorg.matbjorg.DTO;

public class OrderItemDTO {
    private long id;
    private long advertisementId;
    private double amount;

    public OrderItemDTO() {
    }

    public OrderItemDTO(long id, long advertisementId, double amount) {
        this.id = id;
        this.advertisementId = advertisementId;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private long id;
    private List<OrderItemDTO> items = new ArrayList<>();
    private BuyerDTO buyer;
    private boolean active = true;
    private LocalDateTime timeOfPurchase;
    private double totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(long id, List<OrderItemDTO> items, BuyerDTO buyer, boolean active, LocalDateTime timeOfPurchase, double totalPrice) {
        this.id = id;
        this.items = items;
        this.buyer = buyer;
        this.active = active;
        this.timeOfPurchase = timeOfPurchase;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public BuyerDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerDTO buyer) {
        this.buyer = buyer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

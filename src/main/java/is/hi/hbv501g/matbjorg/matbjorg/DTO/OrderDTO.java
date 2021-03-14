package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private long id;
    private List<OrderItemDTO> items = new ArrayList<>();
    private BuyerDTO buyer;
    private boolean active = true;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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

    public OrderDTO(Order order) {
        this.id = order.getId();
        for (int i=0; i<order.getItems().size(); i++) {
            this.items.add(new OrderItemDTO(order.getItems().get(i)));
        }
        this.buyer = new BuyerDTO(order.getBuyer());
        this.active = order.isActive();
        this.timeOfPurchase = order.getTimeOfPurchase();
        this.totalPrice = order.getTotalPrice();
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

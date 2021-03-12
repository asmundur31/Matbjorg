package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private long id;
    private List<OrderItemDTO> items = new ArrayList<>();
    private BuyerDTO buyer;
    private boolean active = true;
    private String timeOfPurchase;
    private double totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(long id, List<OrderItemDTO> items, BuyerDTO buyer, boolean active, String timeOfPurchase, double totalPrice) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");
        if (order.getTimeOfPurchase() != null) {
            this.timeOfPurchase = order.getTimeOfPurchase().format(formatter);
        }
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

    public String getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(String timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

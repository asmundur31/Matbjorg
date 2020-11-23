package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity klasi fyrir Order
 */
@Entity
@Table(name = "order_order")
public class Order {
    /**
     * id er long tala sem er id fyrir Order-ið
     * items er listi af þeim OrderItem sem tilheyra Order-inu
     * buyer er sá Buyer sem býr til Order-ið
     * active segir til um hvort Order-ið sé virkt
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    private Buyer buyer;

    private boolean active = true;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeOfPurchase;

    private double totalPrice;

    /**
     * tómur smiður fyrir Order
     */
    public Order() {
    }

    /**
     * Smiður fyrir Order
     *
     * @param buyer hlutur af taginu Buyer
     */
    public Order(Buyer buyer) {
        this.buyer = buyer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
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

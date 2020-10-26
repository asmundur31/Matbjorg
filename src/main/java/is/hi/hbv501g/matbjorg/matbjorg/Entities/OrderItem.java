package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Advertisement advertisement;
    private double amount;

    @ManyToOne
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Advertisement advertisement, double amount, Order order) {
        this.advertisement = advertisement;
        this.amount = amount;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

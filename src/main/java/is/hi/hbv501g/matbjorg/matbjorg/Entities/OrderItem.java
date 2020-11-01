package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;

/**
 * Entity klasi fyrir OrderItem
 */
@Entity
public class OrderItem {
    /**
     * id er long tala sem er id fyrir OrderItem-ið
     * advertisement er það Advertisement sem OrderItem-ið byggir á
     * amount er double tala sem segir til um hversu mikið magn OrderItemið inniheldur
     * order er það Order sem OrdemItem tilheyrir
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Advertisement advertisement;
    private double amount;

    @ManyToOne
    private Order order;

    /**
     * tómur smiður fyrir OrderItem
     */
    public OrderItem() {
    }

    /**
     * Smiður fyrir OrderItem
     *
     * @param advertisement hlutur af taginu Advertisement
     * @param amount        double tala
     * @param order         hlutur af taginu Order
     */
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

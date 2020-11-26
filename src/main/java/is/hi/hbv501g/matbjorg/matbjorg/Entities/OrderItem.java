package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

/**
 * OrderItem er klasi sem býr til og skilgreinir alla eiginleika OrderItem
 */
@Entity
public class OrderItem {
    /**
     * id til að auðkenna hvert OrderItem
     * advertisement er auglýsingin sem OrderItem vísar á
     * amount er magnið sem notandi langar að fá af vörunni í auglýsingunni
     * order er pöntunin sem OrderItem er hluti af, þ.e. order er karfan fyrir alla hlutina sem notandinn pantar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Advertisement advertisement;

    @DecimalMin(value = "1")
    @Column(nullable = false)
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

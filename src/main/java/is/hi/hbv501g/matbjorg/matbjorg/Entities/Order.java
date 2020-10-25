package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<OrderItem> items;

    @ManyToOne
    private Buyer buyer;

    @Column(columnDefinition = "boolean default true")
    private boolean active;

    public Order() {
    }

    public Order(List<OrderItem> items, Buyer buyer) {
        this.items = items;
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
}

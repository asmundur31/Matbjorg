package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "Seller", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Advertisement> activeAdvertisements = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Advertisement> pastAdvertisements = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Order> activeOrders = new ArrayList<>();

    public Seller() {
    }

    public Seller(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Advertisement> getActiveAdvertisements() {
        return activeAdvertisements;
    }

    public void setActiveAdvertisements(List<Advertisement> activeAdvertisements) {
        this.activeAdvertisements = activeAdvertisements;
    }

    public List<Advertisement> getPastAdvertisements() {
        return pastAdvertisements;
    }

    public void setPastAdvertisements(List<Advertisement> pastAdvertisements) {
        this.pastAdvertisements = pastAdvertisements;
    }

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(List<Order> activeOrders) {
        this.activeOrders = activeOrders;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

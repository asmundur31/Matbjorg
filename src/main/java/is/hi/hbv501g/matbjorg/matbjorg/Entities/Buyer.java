package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private String email;

    @OneToMany(mappedBy = "buyer")
    private List<Order> pendingOrders = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<Order> finishedOrders = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<Order> outstandingOrders = new ArrayList<>();

    public Buyer() {
    }

    public Buyer(String name, String password, String email) {
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

    public List<Order> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(List<Order> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public List<Order> getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(List<Order> finishedOrders) {
        this.finishedOrders = finishedOrders;
    }

    public List<Order> getOutstandingOrders() {
        return outstandingOrders;
    }

    public void setOutstandingOrders(List<Order> outstandingOrders) {
        this.outstandingOrders = outstandingOrders;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

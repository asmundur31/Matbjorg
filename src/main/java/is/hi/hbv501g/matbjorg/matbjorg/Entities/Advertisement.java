package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private Seller owner;
    private String description;
    private boolean active = true;
    private double originalAmount;
    private double currentAmount;
    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expireDate;

    @OneToMany(mappedBy = "advertisement")
    private List<OrderItem> items = new ArrayList<>();

    @ElementCollection(targetClass = Tag.class)
    @Column(name = "tag", nullable = false)
    @CollectionTable(name = "advertisement_tags", joinColumns = {@JoinColumn(name = "advertisement_id")})
    private Set<Tag> tags;

    public Advertisement() {
    }

    public Advertisement(String name, Seller owner, String description, double originalAmount, double price, LocalDateTime expireDate, Set<Tag> tags) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.originalAmount = originalAmount;
        this.price = price;
        this.expireDate = expireDate;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Seller getOwner() {
        return owner;
    }

    public void setOwner(Seller owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    private Seller owner;
    private String description;
    private boolean active;
    private double originalAmount;
    private double currentAmount;

    @OneToMany(mappedBy = "advertisement")
    private List<OrderItem> items = new ArrayList<>();

    @ElementCollection(targetClass = Tag.class)
    @Column(name = "tag", nullable = false)
    @CollectionTable(name = "advertisement_tags", joinColumns = {@JoinColumn(name = "advertisement_id")})
    private Set<Tag> tags;

    public Advertisement() {
    }

    public Advertisement(String name, String description, double originalAmount, HashSet<Tag> tags) {
        this.name = name;
        this.description = description;
        this.originalAmount = originalAmount;
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

    @Override
    public String toString() {
        return this.name;
    }
}

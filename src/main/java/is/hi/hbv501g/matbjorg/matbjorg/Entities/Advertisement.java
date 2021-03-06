package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Advertisement er klasi sem tilgreinir auglýsingu
 */

@Entity
public class Advertisement {
    /**
     * id til að auðkenna hverja auglýsingu
     * name er nafn auglýsingar
     * owner er eigandi auglýsingar
     * description er lýsing á auglýsingu og því vörunni sem hún er að auglýsa
     * active segir til um hvort að auglýsing sé virk eða ekki
     * originalAmount segr tli um upprunalegt magn á vöru sem verið er að auglýsa
     * currentAmount er núverandi magn á vöru, þ.e. það sem hægt er að kaupa
     * price er verð vöru
     * expireDate er hvenær auglýsing verður óvirk
     * items er listi af OrderItems sem auglýsing auglýsir
     * tag er flokkun á tegund vörunnar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Seller owner;

    @NotEmpty
    @Column(nullable = false)
    private String description;
    private boolean active = true;

    @DecimalMin(value = "1")
    @Column(nullable = false)
    private double originalAmount;

    @Min(0)
    @Column(nullable = false)
    private double currentAmount;

    @Min(0)
    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expireDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "advertisement")
    private List<OrderItem> items = new ArrayList<>();

    @ElementCollection(targetClass = Tag.class)
    @Column(name = "tag", nullable = false)
    @CollectionTable(name = "advertisement_tags", joinColumns = {@JoinColumn(name = "advertisement_id")})
    private Set<Tag> tags;
    private String pictureName;

    @OneToOne
    private Location location;

    public Advertisement() {
    }

    public Advertisement(String name, Seller owner, String description, double originalAmount, double price, LocalDateTime expireDate, Set<Tag> tags, Location location) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.originalAmount = originalAmount;
        this.price = price;
        this.expireDate = expireDate;
        this.createdAt = LocalDateTime.now();
        this.tags = tags;
        this.location = location;
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

    public String getTagString() {
        String tagString = "";
        for(Tag tag: tags) {
            tagString += tag + " ";
        }
        return tagString;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

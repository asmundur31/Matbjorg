package is.hi.hbv501g.matbjorg.matbjorg.DTO;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import com.fasterxml.jackson.annotation.JsonFormat;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class AdvertisementDTO {
    private long id;
    private String name;
    private String sellerName;
    private String description;
    private boolean active = true;
    private double originalAmount;
    private double currentAmount;
    private double price;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expireDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;
    private Set<Tag> tags;
    private String pictureName;

    public AdvertisementDTO() {
    }

    public AdvertisementDTO(long id, String name, String sellerName, String description, boolean active, double originalAmount, double currentAmount, double price, LocalDateTime expireDate, LocalDateTime createdAt, Set<Tag> tags, String pictureName) {
        this.id = id;
        this.name = name;
        this.sellerName = sellerName;
        this.description = description;
        this.active = active;
        this.originalAmount = originalAmount;
        this.currentAmount = currentAmount;
        this.price = price;
        this.expireDate = expireDate;
        this.createdAt = createdAt;
        this.tags = tags;
        this.pictureName = pictureName;
    }

    public AdvertisementDTO(Advertisement advertisement) {
        this.id = advertisement.getId();
        this.name = advertisement.getName();
        this.sellerName = advertisement.getOwner().getName();
        this.description = advertisement.getDescription();
        this.active = advertisement.isActive();
        this.originalAmount = advertisement.getOriginalAmount();
        this.currentAmount = advertisement.getCurrentAmount();
        this.price = advertisement.getPrice();
        this.expireDate = advertisement.getExpireDate();
        this.createdAt = advertisement.getCreatedAt();
        this.tags = advertisement.getTags();
        this.pictureName = advertisement.getPictureName();
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}

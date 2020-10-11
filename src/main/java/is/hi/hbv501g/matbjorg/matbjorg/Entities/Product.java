package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;

public class Product {

    private long id;

    private Advertisement advertisement;
    private String name;
    private String type;

    public Product() {
    }

    public Product(Advertisement advertisement, String name, String type) {
        this.advertisement = advertisement;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

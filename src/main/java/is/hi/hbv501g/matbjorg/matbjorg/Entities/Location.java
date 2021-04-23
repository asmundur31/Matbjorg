package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    public Location() {
    }

    public Location(long id, double longitude, double latitude, @NotEmpty String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

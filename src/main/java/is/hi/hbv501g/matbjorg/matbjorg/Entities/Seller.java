package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity klasi sem býr til og skilgreinir alla eiginleika söluaðila (Seller)
 */
@Entity
public class Seller {
    /**
     * id er auðkennisnúmer fyrir söluaðilann
     * name er notendanafn söluaðilans
     * password er lykilorð söluaðilans
     * email er netfang söluaðilans
     * activeAdvertisements er listi af öllum virkum auglýsingum söluaðilans
     * pastAdvertisements er listi af öllum óvirku auglýsingum söluaðilans
     * activeOrders er listi af öllum virku pöntunum söluaðilans
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Advertisement> activeAdvertisements = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Advertisement> pastAdvertisements = new ArrayList<>();

    @OneToMany
    private List<Order> activeOrders = new ArrayList<>();

    /**
     * tómur smiður fyrir Seller
     */
    public Seller() {
    }

    /**
     * Smiður fyrir Seller
     * @param name strengur
     * @param email    strengur
     * @param password strengur
     */
    public Seller(String name, String email, String password) {
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

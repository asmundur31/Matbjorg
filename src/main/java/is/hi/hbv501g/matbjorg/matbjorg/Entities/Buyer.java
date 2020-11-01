package is.hi.hbv501g.matbjorg.matbjorg.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Buyer er klasi sem tilgreinir kaupanda
 */
@Entity
public class Buyer {
    /**
     * id sem auðkennir kaupanda
     * name sem tilgreinir nafn kaupanda
     * password sem er lykilorð kaupanda
     * email sem er netfang kaupanda
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;

    @Column(unique = true)
    private String email;

    public Buyer() {
    }

    /**
     * Smiður fyrir klasann Buyer
     * @param email netfang nýs kaupanda
     * @param password lykilorð nýs kaupanda
     */

    public Buyer(String email, String password) {
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

    @Override
    public String toString() {
        return this.name;
    }
}

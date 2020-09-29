package is.hi.hbv501g.matbjorg.matbjorg.persistence.entities;

import java.util.ArrayList;

public class Advertisement {
    private int advertisementID;
    private String type;
    private String name;
    private Seller owner;
    private boolean active;
    private double originalAmount;
    private double currentAmount;
    private ArrayList<Product> products;

    public Advertisement(Seller owner, String type, String name, double originalAmount) {
        // Setja ID fyrir nýja auglýsingu
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.originalAmount = originalAmount;
        this.currentAmount = originalAmount;
    }
}

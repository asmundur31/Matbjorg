package is.hi.hbv501g.matbjorg.matbjorg.persistence.entities;

import java.util.ArrayList;

public class Seller {
    private int sellerID;
    private String name;
    private String password;
    private String email;
    private ArrayList<Advertisement> activeAdvertisements;
    private ArrayList<Advertisement> pastAdvertisements;
    private ArrayList<Order> activeOrders;

    public Seller(String name, String password, String email) {
        // Setja ID fyrir nýjan seller
        this.name = name;
        this.password = password;
        this.email = email;
    }
}

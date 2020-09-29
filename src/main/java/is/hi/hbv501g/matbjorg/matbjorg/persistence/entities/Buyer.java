package is.hi.hbv501g.matbjorg.matbjorg.persistence.entities;

import java.util.ArrayList;

public class Buyer {
    private int buyerID;
    private String name;
    private String password;
    private String email;
    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> finishedOrders;
    private ArrayList<Order> outstandingOrders;

    public Buyer(String name, String password, String email) {
        // Setja ID fyrir nýjan buyer
        this.name = name;
        this.password = password;
        this.email = email;
    }
}

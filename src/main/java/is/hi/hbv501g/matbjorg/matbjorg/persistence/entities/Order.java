package is.hi.hbv501g.matbjorg.matbjorg.persistence.entities;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private ArrayList<OrderItem> items;
    private Buyer buyer;
    private Seller seller;

    public Order(Buyer buyer, Seller seller) {
        // Setja ID fyrir nýja pöntun
        this.buyer = buyer;
        this.seller = seller;
    }
}

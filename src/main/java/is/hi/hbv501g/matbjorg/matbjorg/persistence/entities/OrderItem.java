package is.hi.hbv501g.matbjorg.matbjorg.persistence.entities;

public class OrderItem {
    private Product product;
    private double amount;

    public OrderItem(Product product, double amount) {
        this.product = product;
        this.amount = amount;
    }
}

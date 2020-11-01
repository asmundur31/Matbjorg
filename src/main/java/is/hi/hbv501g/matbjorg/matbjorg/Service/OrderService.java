package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * Interface fyrir Order
 */
public interface OrderService {
    /**
     * @param order hlutur af taginu Order
     * @return hlutur af taginu Order
     */
    Order save(Order order);

    /**
     * @param order hlutur af taginu Order
     */
    void delete(Order order);

    /**
     * @return Listi af Order hlutum
     */
    List<Order> findAll();

    /**
     * @param id long tala
     * @return Optional Order hlutur sem hefur id sem Id
     */
    Optional<Order> findById(long id);

    /**
     * @param buyer hlutur af taginu Buyer
     * @return Listi af Order hlutum sem hafa buyer sem Buyer
     */
    List<Order> findByBuyer(Buyer buyer);

    /**
     * @param buyer  hlutur af taginu Buyer
     * @param active boolean gildi
     * @return Hlutur af taginu Order sem hefur buyer sem Buyer og Active=active
     */
    Order findByBuyerAndActive(Buyer buyer, boolean active);

    /**
     * @param order hlutur af taginu Order
     * @return hlutur af taginu Order
     */
    Order confirmOrder(Order order);

    /**
     * @param order hlutur af taginu Order
     * @return double tala
     */
    double totalPrice(Order order);
}

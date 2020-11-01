package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * Þjónusta fyrir Order
 */
public interface OrderService {
    /**
     * Vistar order
     *
     * @param order hlutur af taginu Order
     * @return hlutur af taginu Order
     */
    Order save(Order order);

    /**
     * eyðir order
     *
     * @param order hlutur af taginu Order
     */
    void delete(Order order);

    /**
     * finnur alla Order hluti
     *
     * @return Listi af Order hlutum
     */
    List<Order> findAll();

    /**
     * finnur Order með ákveðið Id
     *
     * @param id long tala
     * @return Optional Order hlutur sem hefur id sem Id
     */
    Optional<Order> findById(long id);

    /**
     * Finnur öll Order sem hafa buyer sen Buyer
     *
     * @param buyer hlutur af taginu Buyer
     * @return Listi af Order hlutum sem hafa buyer sem Buyer
     */
    List<Order> findByBuyer(Buyer buyer);

    /**
     * Finnur Order sem hefur buyer sem Buyer og Active=active
     *
     * @param buyer  hlutur af taginu Buyer
     * @param active boolean gildi
     * @return Hlutur af taginu Order sem hefur buyer sem Buyer og Active=active
     */
    Order findByBuyerAndActive(Buyer buyer, boolean active);

    /**
     * Staðfestir order og setur Active=false ef gengur upp
     *
     * @param order hlutur af taginu Order
     * @return hlutur af taginu Order
     */
    Order confirmOrder(Order order);

    /**
     * Skilar heildarverði OrderItem-a í order
     *
     * @param order hlutur af taginu Order
     * @return double tala
     */
    double totalPrice(Order order);
}

package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository fyrir Order, hefur samskipti við töfluna Order í gagnagrunninum
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Vistar order í gagnagrunn
     *
     * @param order hlutur af taginu Order sem á að vista
     * @return hlutur af taginu Order sem var verið að vista
     */
    Order save(Order order);

    /**
     * Eyðir order úr gagnagrunni
     *
     * @param order hlutur af taginu Order sem á að eyða
     */
    void delete(Order order);

    /**
     * Finnur öll Order í gagnagrunninum
     *
     * @return listi af hlutum af taginu Order
     */
    List<Order> findAll();

    /**
     * Finnur Order með long töluna id sem Id
     *
     * @param id long tala sem er id fyrir Order-ið sem á að finna
     * @return Optional hlutur af taginu Order
     */
    Optional<Order> findById(long id);

    /**
     * Finnur öll Order sem hafa buyer sem Buyer
     *
     * @param buyer hlutur af taginu Buyer
     * @return listi af hlutum af taginu Order
     */
    List<Order> findByBuyer(Buyer buyer);

    /**
     * Finnur Order sem hefur buyer sem Buyer og Active=active
     *
     * @param buyer  hlutur af taginu Buyer
     * @param active boolean gildi
     * @return Order hlutur sem hefur buyer sem Buyer og Active=active
     */
    Order findByBuyerAndActive(Buyer buyer, boolean active);
}

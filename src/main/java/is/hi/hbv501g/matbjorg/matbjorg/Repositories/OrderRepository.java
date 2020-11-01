package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository fyrir hluti af taginu Order
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Vistar order í gagnagrunn
     *
     * @param order hlutur af taginu Order
     * @return hlutur af taginu Order
     */
    Order save(Order order);

    /**
     * Eyðir order úr gagnagrunni
     *
     * @param order hlutur af taginu Order
     */
    void delete(Order order);

    /**
     * Finnur öll Order
     *
     * @return listi af hlutum af taginu Order
     */
    List<Order> findAll();

    /**
     * Finnur Order með long töluna id sem Id
     *
     * @param id long tala, id fyrir hlut af taginu Order
     * @return Optional hlutur af taginu Order
     */
    Optional<Order> findById(long id);

    /**
     * Finnur öll Order með buyer sem Buyer
     *
     * @param buyer hlutur af taginu Buyer
     * @return listi af hlutum af taginu Order
     */
    List<Order> findByBuyer(Buyer buyer);

    /**
     * Finnur Order sem hefur buyer sem Buyer og Active=active
     *
     * @param buyer
     * @param active
     * @return
     */
    Order findByBuyerAndActive(Buyer buyer, boolean active);
}

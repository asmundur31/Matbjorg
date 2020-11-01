package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * OrderItemRepository hefur samskipti við töfluna order_item í gagnagrunninum
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * Vistar orderItem í gagnagrunninn
     * @param orderItem orderItem sem á að vista í gagnagrunninn
     * @return orderItem sem var verið að vista í gagnagrunninn
     */
    OrderItem save(OrderItem orderItem);

    /**
     * Eyðir orderItem úr gagnagrunninum
     * @param orderItem orderItem sem á að eyða úr gagnagrunninum
     */
    void delete(OrderItem orderItem);

    /**
     * Sækir öll orderItem sem eru í gagnagrunninum
     * @return listi með öllum orderItem sem eru í gagnagrunninum
     */
    List<OrderItem> findAll();

    /**
     * Sækir orderItem með ákveðið auðkenni í gagnagrunninn
     * @param id auðkennið sem notað er til að finna orderItem
     * @return ílát með orderItem ef til er orderItem með auðkenni id annars tómt ílát
     */
    Optional<OrderItem> findById(long id);

    /**
     * Sækir orderItem með ákveðna auglýsingu og ákveðna pöntun í gagnagrunninum. Hver pöntun getur bara verið með eitt
     * orderItem sem vísar á ákveðna auglýsingu.
     * @param advertisement auglýsingin sem orderItem á að hafa
     * @param order pöntunin sem orderItem á að hafa
     * @return ef orderItem með advertisement og order finnst þá er skilað því orderItem annars null
     */
    OrderItem findByAdvertisementAndOrder(Advertisement advertisement, Order order);
}

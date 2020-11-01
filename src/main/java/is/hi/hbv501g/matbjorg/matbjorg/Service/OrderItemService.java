package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * OrderItemService er interface fyrir þjónustu fyrir orderItem
 */
public interface OrderItemService {
    /**
     * Vistar orderItem
     * @param orderItem nýtt orderItem
     * @return orderItem sem var verið að vista
     */
    OrderItem save(OrderItem orderItem);

    /**
     * Eyðir orderItem
     * @param orderItem orderItem sem á að eyða
     */
    void delete(OrderItem orderItem);

    /**
     * Finnur öll orderItem sem hafa verið búinn til
     * @return listi með öllum orderItem sem allir notendur hafa búið til
     */
    List<OrderItem> findAll();

    /**
     * Finnur orderItem með ákveðið id
     * @param id auðkenning á orderItem
     * @return ílát með orderItem með auðkenningu id annars tómu íláti
     */
    Optional<OrderItem> findById(long id);

    /**
     * Finnur orderItem með ákveðna auglýsingu og ákveðna pöntun. Hver pöntun getur bara verið með eitt orderItem sem
     * vísar á ákveðna auglýsingu.
     * @param advertisement auglýsingin sem orderItem á að hafa
     * @param order pöntunin sem orderItem á að hafa
     * @return ef orderItem með advertisement og order finnst þá er skilað því orderItem annars null
     */
    OrderItem findByAdvertisementAndOrder(Advertisement advertisement, Order order);
}

package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.AdvertisementRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.OrderItemRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.OrderRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Klasi sem implementar OrderService
 */
@Service
public class OrderServiceImplementation implements OrderService {
    /**
     * orderRepository er Repository fyrir Order
     * advertisementRepository er Repository fyrir Advertisement
     * orderItemRepository er Repository fyrir orderItem
     */
    private OrderRepository orderRepository;
    private AdvertisementRepository advertisementRepository;
    private OrderItemRepository orderItemRepository;

    /**
     * Smiður fyrir OrderServiceImplementation
     *
     * @param orderRepository         Repository fyrir Order
     * @param advertisementRepository Repository fyrir Advertisement
     * @param orderItemRepository     Repository fyrir OrderItem
     */
    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository, AdvertisementRepository advertisementRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.advertisementRepository = advertisementRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * @param order hlutur af taginu Order
     * @return kall á fallið save úr orderRepository með order
     */
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    /**
     * @param order hlutur af taginu Order
     */
    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    /**
     * @return kall á fallið findAll úr orderRepository
     */
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    /**
     * @param id long tala
     * @return kall á fallið findById úr orderRepository með id
     */
    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    /**
     * @param buyer hlutur af taginu Buyer
     * @return kall á fallið findByBuyer úr orderRepository með buyer
     */
    @Override
    public List<Order> findByBuyer(Buyer buyer) {
        return orderRepository.findByBuyer(buyer);
    }

    /**
     * @param buyer  hlutur af taginu Buyer
     * @param active boolean gildi
     * @return kall á fallið findByBuyerAndActive úr orderRepository með buyer og active
     */
    @Override
    public List<Order> findByBuyerAndActive(Buyer buyer, boolean active) {
        return orderRepository.findByBuyerAndActive(buyer, active);
    }

    /**
     * @param order hlutur af taginu Order
     * @return skilar null ef order inniheldur OrderItem sem ekki er hægt að kaupa,
     * annars skilar fallið order sem er búið að uppfæra
     */
    @Override
    public Order confirmOrder(Order order) {
        boolean valid = true;
        // Er eitthver auglýsing active=false eða með currentAmount < amount
        for (OrderItem item : order.getItems()) {
            if (!item.getAdvertisement().isActive() || item.getAdvertisement().getCurrentAmount() < item.getAmount()) {
                // Eyðum orderItem ef hann er ekki lengur mögulegur
                orderItemRepository.delete(item);
                valid = false;
            }
        }
        if (!valid) {
            return null;
        }
        // Minnkum currentAmount hjá öllum auglýsingum
        for (OrderItem item : order.getItems()) {
            Advertisement adToUpdate = item.getAdvertisement();
            adToUpdate.setCurrentAmount(adToUpdate.getCurrentAmount() - item.getAmount());
            advertisementRepository.save(adToUpdate);
        }
        // Setjum order sem false og vistum
        order.setActive(false);
        orderRepository.save(order);
        return order;
    }

    /**
     * @param order hlutur af taginu Order
     * @return double tala sem segir til um heildarkostnað OrderItem-a í Order
     */
    @Override
    public double totalPrice(Order order) {
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getAmount() * item.getAdvertisement().getPrice();
        }
        return total;
    }
}

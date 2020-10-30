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

@Service
public class OrderServiceImplementation implements OrderService {
    private OrderRepository orderRepository;
    private AdvertisementRepository advertisementRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository, AdvertisementRepository advertisementRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.advertisementRepository = advertisementRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByBuyer(Buyer buyer) {
        return orderRepository.findByBuyer(buyer);
    }

    @Override
    public Order findByBuyerAndActive(Buyer buyer, boolean active) {
        return orderRepository.findByBuyerAndActive(buyer, active);
    }

    @Override
    public Order confirmOrder(Order order) {
        boolean valid = true;
        // Er eitthver auglýsing active=false eða með currentAmount < amount
        for(OrderItem item : order.getItems()) {
            if(!item.getAdvertisement().isActive() || item.getAdvertisement().getCurrentAmount() < item.getAmount()) {
                // Eyðum orderItem ef hann er ekki lengur mögulegur
                orderItemRepository.delete(item);
                valid = false;
            }
        }
        if(!valid) {
            return null;
        }
        // Minnkum currentAmount hjá öllum auglýsingum
        for(OrderItem item : order.getItems()) {
            Advertisement adToUpdate = item.getAdvertisement();
            adToUpdate.setCurrentAmount(adToUpdate.getCurrentAmount()-item.getAmount());
            advertisementRepository.save(adToUpdate);
        }
        // Setjum order sem false og vistum
        order.setActive(false);
        orderRepository.save(order);
        return order;
    }

    @Override
    public double totalPrice(Order order) {
        double total = 0;
        for(OrderItem item : order.getItems()) {
            total += item.getAmount() * item.getAdvertisement().getPrice();
        }
        return total;
    }
}

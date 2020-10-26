package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.OrderRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderServiceImplementation(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void delete(Order order) {
        repository.delete(order);
    }

    /*@Override
    public Order insertItem(Order order, OrderItem item) {
        List<OrderItem> l = order.getItems();
        boolean addItem = true;
        for(OrderItem i : l) {
            if(i.getAdvertisement().getId() == item.getAdvertisement().getId()) {
                addItem = false;
                break;
            }
        }
        if(addItem) {
            l.add(item);
        }
        order.setItems(l);
        return order;
    }*/

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> findByBuyer(Buyer buyer) {
        return repository.findByBuyer(buyer);
    }

    @Override
    public Order findByBuyerAndActive(Buyer buyer, boolean active) {
        return repository.findByBuyerAndActive(buyer, active);
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    void delete(Order order);
    //Order insertItem(Order order, OrderItem item);
    List<Order> findAll();
    Optional<Order> findById(long id);
    List<Order> findByBuyer(Buyer buyer);
    Order findByBuyerAndActive(Buyer buyer, boolean active);
}

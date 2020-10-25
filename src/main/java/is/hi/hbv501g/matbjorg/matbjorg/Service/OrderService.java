package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order, Buyer buyer);
    void delete(Order order);
    List<Order> findAll();
    Optional<Order> findById(long id);
    List<Order> findByBuyer(Buyer buyer);
    /*Order findByBuyerAndActive(Buyer buyer);*/
}

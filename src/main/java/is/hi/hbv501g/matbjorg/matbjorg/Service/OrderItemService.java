package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem, Buyer buyer);
    void delete(OrderItem orderItem);
    List<OrderItem> findAll();
    Optional<OrderItem> findById(long id);
}

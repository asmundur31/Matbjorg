package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem save(OrderItem orderItem);
    void delete(OrderItem orderItem);
    List<OrderItem> findAll();
    Optional<OrderItem> findById(long id);
}

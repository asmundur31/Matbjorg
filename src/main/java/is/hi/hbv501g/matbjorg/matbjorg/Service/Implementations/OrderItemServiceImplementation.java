package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Order;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.OrderItem;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.OrderItemRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
    private OrderItemRepository repository;

    @Autowired
    public OrderItemServiceImplementation(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        OrderItem exists = findByAdvertisement(orderItem.getAdvertisement());
        if(exists == null) {
            return repository.save(orderItem);
        }
        exists.setAmount(orderItem.getAmount());
        return repository.save(exists);
    }

    @Override
    public void delete(OrderItem orderItem) {
        repository.delete(orderItem);
    }

    @Override
    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public OrderItem findByAdvertisement(Advertisement advertisement) {
        return repository.findByAdvertisement(advertisement);
    }
}

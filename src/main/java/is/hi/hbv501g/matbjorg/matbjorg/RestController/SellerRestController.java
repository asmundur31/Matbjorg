package is.hi.hbv501g.matbjorg.matbjorg.RestController;

import is.hi.hbv501g.matbjorg.matbjorg.DTO.*;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.*;
import is.hi.hbv501g.matbjorg.matbjorg.Service.OrderService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest/sellers")
public class SellerRestController {
    private SellerService sellerService;
    private OrderService orderService;

    public SellerRestController(SellerService sellerService, OrderService orderService) {
        this.sellerService = sellerService;
        this.orderService = orderService;
    }

    @GetMapping("")
    List<SellerDTO> all() {
        List<Seller> sellers = sellerService.findAll();
        List<SellerDTO> sellersDTO = new ArrayList<>();
        for(int i=0; i<sellers.size(); i++) {
            SellerDTO sellerDTO = parseSeller(sellers.get(i));
            sellersDTO.add(sellerDTO);
        }
        // TODO: eitthver galli í active og past advertisements, þurfum eitthvað að skoða þetta
        return sellersDTO;
    }

    @GetMapping("/{id}")
    SellerDTO one(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.findById(id);
        SellerDTO sellerDTO = parseSeller(seller.get());
        // TODO: eitthver galli í active og past advertisements
        return sellerDTO;
    }

    /**
     * Hjálpar fall til að parse-a seller í sellerDTO
     * @param seller
     * @return
     */
    private SellerDTO parseSeller(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(seller.getId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setEmail(seller.getEmail());

        List<AdvertisementDTO> activeAdvertisementDTOs = new ArrayList<>();
        for(int i=0; i<seller.getActiveAdvertisements().size(); i++) {
            AdvertisementDTO advertisementDTO = parseAdvertisement(seller.getActiveAdvertisements().get(i));
            activeAdvertisementDTOs.add(advertisementDTO);
        }
        sellerDTO.setActiveAdvertisements(activeAdvertisementDTOs);

        List<AdvertisementDTO> pastAdvertisementDTOs = new ArrayList<>();
        for(int i=0; i<seller.getPastAdvertisements().size(); i++) {
            AdvertisementDTO advertisementDTO = parseAdvertisement(seller.getPastAdvertisements().get(i));
            pastAdvertisementDTOs.add(advertisementDTO);
        }
        sellerDTO.setPastAdvertisements(pastAdvertisementDTOs);

        List<OrderDTO> activeOrderDTOs = new ArrayList<>();
        for(int i=0; i<seller.getActiveOrders().size(); i++) {
            OrderDTO orderDTO = parseOrder(seller.getActiveOrders().get(i));
            activeOrderDTOs.add(orderDTO);
        }
        sellerDTO.setActiveOrders(activeOrderDTOs);

        return sellerDTO;
    }

    /**
     * Hjálpar fall til að parse-a advertisement í advertisementDTO
     * @param advertisement
     * @return
     */
    private AdvertisementDTO parseAdvertisement(Advertisement advertisement) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        advertisementDTO.setId(advertisement.getId());
        advertisementDTO.setName(advertisement.getName());
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setActive(advertisement.isActive());
        advertisementDTO.setOriginalAmount(advertisement.getOriginalAmount());
        advertisementDTO.setCurrentAmount(advertisement.getCurrentAmount());
        advertisementDTO.setPrice(advertisement.getPrice());
        advertisementDTO.setExpireDate(advertisement.getExpireDate());
        advertisementDTO.setCreatedAt(advertisement.getCreatedAt());
        advertisementDTO.setTags(advertisement.getTags());
        advertisementDTO.setPictureName(advertisement.getPictureName());
        return advertisementDTO;
    }

    /**
     * Hjálparfall til að parse-a order í orderDTO
     * @param order
     * @return
     */
    private OrderDTO parseOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());

        List<OrderItemDTO> itemDTOs = new ArrayList<>();
        for(int i=0; i<order.getItems().size(); i++) {
            OrderItemDTO orderItemDTO = parseOrderItem(order.getItems().get(i));
            itemDTOs.add(orderItemDTO);
        }
        orderDTO.setItems(itemDTOs);

        Buyer buyer = order.getBuyer();
        BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getName(), buyer.getEmail());
        orderDTO.setBuyer(buyerDTO);

        orderDTO.setActive(order.isActive());
        orderDTO.setTimeOfPurchase(order.getTimeOfPurchase());
        orderDTO.setTotalPrice(order.getTotalPrice());
        return orderDTO;
    }

    /**
     * Hjálparfall til að parse-a orderItem í orderItemDTO
     * @param orderItem
     * @return
     */
    private OrderItemDTO parseOrderItem(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        Advertisement advertisement = orderItem.getAdvertisement();
        orderItemDTO.setAdvertisement(parseAdvertisement(advertisement));
        orderItemDTO.setAmount(orderItem.getAmount());
        return orderItemDTO;
    }
}
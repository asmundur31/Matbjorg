package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import java.util.ArrayList;
import java.util.List;

public class SellerDTO {
    private long id;
    private String name;
    private String email;
    private List<AdvertisementDTO> activeAdvertisements = new ArrayList<>();
    private List<AdvertisementDTO> pastAdvertisements = new ArrayList<>();
    private List<OrderDTO> activeOrders = new ArrayList<>();

    public SellerDTO() {
    }

    public SellerDTO(long id, String name, String email, List<AdvertisementDTO> activeAdvertisements, List<AdvertisementDTO> pastAdvertisements, List<OrderDTO> activeOrders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.activeAdvertisements = activeAdvertisements;
        this.pastAdvertisements = pastAdvertisements;
        this.activeOrders = activeOrders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AdvertisementDTO> getActiveAdvertisements() {
        return activeAdvertisements;
    }

    public void setActiveAdvertisements(List<AdvertisementDTO> activeAdvertisements) {
        this.activeAdvertisements = activeAdvertisements;
    }

    public List<AdvertisementDTO> getPastAdvertisements() {
        return pastAdvertisements;
    }

    public void setPastAdvertisements(List<AdvertisementDTO> pastAdvertisements) {
        this.pastAdvertisements = pastAdvertisements;
    }

    public List<OrderDTO> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(List<OrderDTO> activeOrders) {
        this.activeOrders = activeOrders;
    }
}

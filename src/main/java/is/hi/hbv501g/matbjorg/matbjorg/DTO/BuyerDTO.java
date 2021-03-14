package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;

public class BuyerDTO {
    private long id;
    private String name;
    private String email;

    public BuyerDTO() {
    }

    public BuyerDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public BuyerDTO(Buyer buyer) {
        this.id = buyer.getId();
        this.name = buyer.getName();
        this.email = buyer.getEmail();
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
}

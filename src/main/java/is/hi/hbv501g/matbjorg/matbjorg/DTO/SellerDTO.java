package is.hi.hbv501g.matbjorg.matbjorg.DTO;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

public class SellerDTO {
    private long id;
    private String name;
    private String email;

    public SellerDTO() {
    }

    public SellerDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public SellerDTO(Seller seller) {
        this.id = seller.getId();
        this.name = seller.getName();
        this.email = seller.getEmail();
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

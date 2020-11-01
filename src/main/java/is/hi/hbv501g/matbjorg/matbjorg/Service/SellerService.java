package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;
import java.util.Optional;

/**
 * SellerService er interface fyrir þjónustu fyrir Seller
 */
public interface SellerService {
    /**
     * Vistar seller
     *
     * @param seller Seller sem á að vista
     * @return seller af tagi Seller sem var verið að vista
     */
    Seller save(Seller seller);

    /**
     * Eyðir seller
     *
     * @param seller Seller sem á að eyða
     */
    void delete(Seller seller);

    /**
     * Sækir þá alla Seller-a
     *
     * @return listi sem inniheldur alla Seller-a
     */
    List<Seller> findAll();

    /**
     * Sækir þann Seller hlut sem hefur name sem Name
     *
     * @param name strengur sem verður leitað eftir
     * @return hlutur af taginu Seller sem hefur name sem Name
     */
    Seller findByName(String name);


    /**
     * Sækir Seller sem hefur email sem Email
     *
     * @param email strengur sem leitað verður eftir
     * @return Seller hlutur sem hefur email sem Email
     */
    Seller findByEmail(String email);

    /**
     * Sækir Seller með id sem Id
     *
     * @param id long tala
     * @return Ílát með Seller með id sem Id eða tómt ílát ef Seller með Id = id finnst ekki
     */
    Optional<Seller> findById(long id);

    /**
     * Skráir söluaðila inn
     *
     * @param seller hlutur af taginu Seller sem á að skrá inn
     * @return hlutur af taginu Seller sem var verið að skrá inn
     */
    Seller login(Seller seller);
}

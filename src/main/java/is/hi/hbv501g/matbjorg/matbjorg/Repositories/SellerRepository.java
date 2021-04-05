package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * SellerRepository hefur samskipti við töfluna Seller í gagnagrunninum
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    /**
     * Vistar seller í gagnagrunn
     *
     * @param seller Seller sem á að vista
     * @return seller af tagi Seller sem var verið að vista
     */
    Seller save(Seller seller);

    /**
     * Eyðir seller úr gagnagrunni
     *
     * @param seller Seller sem á að eyða
     */
    void delete(Seller seller);

    /**
     * Sækir þá alla Seller-a úr gagnagrunni
     *
     * @return listi sem inniheldur alla Seller hluti úr gagnagrunninum
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
}

package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    /**
     * Vistum kaupanda í gagnagrunni
     * @param buyer kaupandi sem á að vista
     * @return skilar kaupanda sem var vistaður
     */
    Buyer save(Buyer buyer);

    /**
     * Eyðum kaupanda úr gagnagrunni
     * @param buyer kaupandi sem á að eyða
     */
    void delete(Buyer buyer);

    /**
     * Náum í alla kaupendur
     * @return skilar lista af öllum kaupendum
     */
    List<Buyer> findAll();

    /**
     * Finnum kaupanda með ákveðið nafn
     * @param name nafn kaupanda sem við viljum finna
     * @return skilar lista af kaupendum sem hafa tilgreint nafn
     */
    List<Buyer> findByName(String name);

    /**
     * Finnum kaupanda eftir netfangi
     * @param email netfang sem leita á að
     * @return skilar notanda sem hefur tilgreint netfang
     */
    Buyer findByEmail(String email);

    /**
     * Finnum kaupanda eftir id/auðkenni
     * @param id auðkenni sem leita á að
     * @return skilar íláti með hlut af kaupanda með tilgreint auðkenni
     */
    Optional<Buyer> findById(long id);

    Buyer findByToken(String token);
}

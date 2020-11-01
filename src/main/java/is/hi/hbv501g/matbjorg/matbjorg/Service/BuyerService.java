package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Buyer;

import java.util.List;
import java.util.Optional;

/**
 * BuyerService er þjónusta fyrir Buyer
 */

public interface BuyerService {
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
     * @return skilar ílát með hlut af kaupanda með tilgreint auðkenni
     */
    Optional<Buyer> findById(long id);

    /**
     * Athugum hvort kaupandi er skráður inn
     * @param buyer kaupandi sem á að athuga
     * @return skilar okkur null ef kaupandi er ekki skráður inn. Annars skilar það okkur
     * viðeigandi kaupanda
     */
    Buyer login(Buyer buyer);
}

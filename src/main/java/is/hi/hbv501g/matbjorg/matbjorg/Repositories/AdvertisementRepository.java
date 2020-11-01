package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * AdvertisementRepository hefur samskipti við töfluna Advertisement í gagnagrunninum
 */

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    /**
     * Vistar auglýsingu í gagnagrunninn
     * @param advertisement ný auglýsing
     * @return auglýsing sem var verið að vista
     */
    Advertisement save(Advertisement advertisement);

    /**
     * Eyðir auglýsingu úr gagnagrunninum
     * @param advertisement auglýsing sem á að eyða
     */
    void delete(Advertisement advertisement);

    /**
     * Uppfærir gagnagrunninn, þ.e. breytir gildinu á active fyrir gefna auglýsingu
     * @param advertisementID auðkenning á auglýsnigu sem á að breyta
     * @param active hefur uppfært gildi
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE Advertisement advertisement SET advertisement.active = ?2 WHERE advertisement.id = ?1")
    public void updateActive(long advertisementID, boolean active);

    /**
     * Nær í allar auglýsingar
     * @return skilar lista af auglýsingu
     */
    List<Advertisement> findAll();

    /**
     * Nær í allar auglýsingar sem hafa sama gildi og active
     * @param active gildið sem auglýsingarnar eiga að hafa, þ.e. annað hvort true eða false
     * @return listi af auglýsingum með sama gildi á active
     */
    List<Advertisement> findByActive(boolean active);

    /**
     * Nær í allar auglýsingar með sama nafn
     * @param name nafn á auglýsingu
     * @return listi af auglýsingum með sama nafn
     */
    List<Advertisement> findByName(String name);

    /**
     * Nær í auglýsingu með ákveðið id
     * @param id auðkenning á auglýsingu
     * @return ílát með hlut af auglýsingu með viðkomandi id
     */
    Optional<Advertisement> findById(long id);

    /**
     * Nær í allar auglýsingar með sama eiganda
     * @param seller eigandi
     * @return listi af auglýsingum með sama eiganda
     */
    List<Advertisement> findByOwner(Seller seller);

    /**
     * Nær í allar auglýsingar með sama eiganda og með sama gildi á active
     * @param seller eigandi
     * @param active gildið sem auglýsingarnar eiga að hafa, þ.e. annað hvort true eða false
     * @return listi af auglýsingum með sama eiganda og sama gildi á active
     */
    List<Advertisement> findByOwnerAndActive(Seller seller, boolean active);

    /**
     * Leitar að ákveðinni auglýsingu eftir gefnum leitarstreng
     * @param search leitarstrengur
     * @return skilar lista af auglýsingum sem eiga við þennan leitarstreng
     */
    @Query(value = "SELECT advertisement FROM Advertisement advertisement WHERE advertisement.name LIKE %?1%"
            + "OR advertisement.description LIKE %?1%")
    public List<Advertisement> findByKeyWord(String search);
}

package is.hi.hbv501g.matbjorg.matbjorg.Service;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;

import java.util.List;
import java.util.Optional;

/**
 * AdvertisementService er interface fyrir þjónustu fyrir auglýsingar
 */

public interface AdvertisementService {
    /**
     * Vistar auglýsingu
     * @param advertisement ný auglýsing
     * @param seller eigandi auglýsingar
     * @return auglýsing sem var verið að vista
     */
    Advertisement save(Advertisement advertisement, Seller seller);

    /**
     * Eyðir auglýsingu
     * @param advertisement auglýsing sem á að eyða
     */
    void delete(Advertisement advertisement);

    /**
     * Uppfærir gildið á active fyrir auglýsingar
     */
    void updateActive();

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
    List<Advertisement> findByName(String name);

    /**
     * Nær í auglýsingu með ákveðið id
     * @param id auðkenning á auglýsingu
     * @return ílát með hlut af auglýsingu með viðkomandi id
     */
    Optional<Advertisement> findById(long id);
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
    List<Advertisement> findByKeyWord(String search);

    /**
     * Síar út eigendur sem ekki eru valdir
     * @param sellerList listi af eigendum sem við viljum sjá auglýsingar hjá
     * @return skilar lista af auglýsingum sem valdir eigendur eiga
     */
    List<Advertisement> filterBySellers(List<Seller> sellerList);

    /**
     * Síar út tög sem ekki eru valdir
     * @param tags listi af tögum sem við viljum sjá auglýsingar hjá
     * @return skilar lista af auglýsingum sem valin tög eiga
     */
    List<Advertisement> filterByTags(List<String> tags);
}

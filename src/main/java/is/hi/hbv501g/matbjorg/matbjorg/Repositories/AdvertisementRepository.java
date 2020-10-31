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

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Advertisement save(Advertisement advertisement);
    void delete(Advertisement advertisement);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Advertisement advertisement SET advertisement.active = ?2 WHERE advertisement.id = ?1")
    public void updateActive(long advertisementID, boolean active);

    List<Advertisement> findAll();
    List<Advertisement> findByActive(boolean active);
    List<Advertisement> findByName(String name);
    Optional<Advertisement> findById(long id);
    List<Advertisement> findByOwner(Seller seller);
    List<Advertisement> findByOwnerAndActive(Seller seller, boolean active);

    @Query(value = "SELECT advertisement FROM Advertisement advertisement WHERE advertisement.name LIKE %?1%"
            + "OR advertisement.description LIKE %?1%")
    public List<Advertisement> findByKeyWord(String search);
}

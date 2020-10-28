package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Advertisement save(Advertisement advertisement);
    void delete(Advertisement advertisement);
    List<Advertisement> findAll();
    List<Advertisement> findByName(String name);
    Optional<Advertisement> findById(long id);
    List<Advertisement> findByOwner(Seller seller);

    @Query(value = "SELECT advertisement FROM Advertisement advertisement WHERE advertisement.name LIKE %?1%"
            + "OR advertisement.description LIKE %?1%")
    public List<Advertisement> findByKeyWord(String search);
}

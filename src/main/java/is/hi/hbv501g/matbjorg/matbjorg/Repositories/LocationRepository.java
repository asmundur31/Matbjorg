package is.hi.hbv501g.matbjorg.matbjorg.Repositories;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Location;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
    @Query(
        value = "select * from location where id in (select seller_locations.locations_id from seller_locations Join location ON location.id = seller_locations.locations_id and seller_locations.seller_id=:#{#seller.getId()});",
        nativeQuery = true)
    List<Location> findBySeller(@Param("seller") Seller seller);
    Location findById(long id);
}

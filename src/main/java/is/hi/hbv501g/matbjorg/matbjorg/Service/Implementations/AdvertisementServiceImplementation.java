package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.AdvertisementRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * AdvertisementServiceImplementation er klasi sem implementar AdvertisementService
 * og útfærir viðeigandi aðferðir og kallar á AdvertisementRepository
 */

@Service
public class AdvertisementServiceImplementation implements AdvertisementService {
    /**
     * repository hefur samskipti við töfluna Advertisement í gagnagrunninum
     * UPLOAD_PICTURE_PATH er strengur sem að hefur rétt path á möppuna sem á að geyma myndir
     */
    AdvertisementRepository repository;
    public static String UPLOAD_PICTURE_PATH = System.getProperty("user.dir")+"/src/main/resources/static/img/advertisementImages/";

    /**
     * Smiður fyrir AdvertisementServiceImplementation
     * @param advertisementRepository repository sem hefur samskipti við töfluna Advertisement í gagnagrunninum
     */
    @Autowired
    public AdvertisementServiceImplementation(AdvertisementRepository advertisementRepository) {
        this.repository = advertisementRepository;
    }

    @Override
    public List<Advertisement> findByActive(boolean active) {
        return repository.findByActive(active);
    }

    @Override
    public Advertisement save(Advertisement advertisement, Seller seller, MultipartFile picture) {
        advertisement.setOwner(seller);
        advertisement.setCurrentAmount(advertisement.getOriginalAmount());
        // Uploadum myndinni í img/advertisementImages
        Boolean tokst = uploadImage(picture);
        if(tokst) {
            advertisement.setPictureName(picture.getOriginalFilename());
        } else {
            advertisement.setPictureName("default.jpg");
        }
        return repository.save(advertisement);
    }

    private Boolean uploadImage(MultipartFile picture) {
        Boolean tokst = false;
        List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/gif");
        // Ef mynd er með rétt extension
        if(contentTypes.contains(picture.getContentType())) {
            try {
                byte[] bytes = picture.getBytes();
                Path path = Paths.get(UPLOAD_PICTURE_PATH, picture.getOriginalFilename());
                Files.write(path, bytes);
                tokst = true;
            } catch (Exception e) {
                System.out.println("Villa við að uploada mynd");
                e.printStackTrace();
            }
        }
        return tokst;
    }

    @Override
    public void delete(Advertisement advertisement) {
        repository.delete(advertisement);
    }

   @Override
    public void updateActive() {
        LocalDateTime lt = LocalDateTime.now();
        for(Advertisement ad: repository.findAll()){
            if(ad.getCurrentAmount()<=0 || lt.compareTo(ad.getExpireDate())>0) {
                repository.updateActive(ad.getId(),false);
            }
        }
    }

    @Override
    public List<Advertisement> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Advertisement> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Advertisement> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Advertisement> findByOwner(Seller seller) {
        return repository.findByOwner(seller);
    }

    @Override
    public List<Advertisement> findByOwnerAndActive(Seller seller, boolean active) {
        return repository.findByOwnerAndActive(seller, active);
    }

    @Override
    public List<Advertisement> findByKeyWord(String search) {
        if (search != null) {
            List<Advertisement> found = repository.findByKeyWord(search);
            List<Advertisement> foundAndActive = new ArrayList<>();
            for (Advertisement ad : found) {
                if (ad.isActive()) {
                    foundAndActive.add(ad);
                }
            }
            return foundAndActive;
        }
        return repository.findAll();
    }

    @Override
    public List<Advertisement> filterBySellers(List<Seller> sellerList) {
        if (!sellerList.isEmpty()) {
            List<Advertisement> advertisementList = new ArrayList<Advertisement>();
            for (Seller seller : sellerList) {
                List<Advertisement> advertisementBySeller = repository.findByOwner(seller);
                for (Advertisement advertisement : advertisementBySeller) {
                    if (advertisement.isActive()) {
                        advertisementList.add(advertisement);
                    }
                }
            }
            return advertisementList;
        }
        return repository.findAll();
    }

    @Override
    public List<Advertisement> filterByTags(List<String> tags) {

        if (!tags.isEmpty()) {

            List<Tag> listOfTags = new ArrayList<Tag>();
            for (String tag : tags) {
                listOfTags.add(Tag.valueOf(tag));
            }

            List<Advertisement> advertisements = repository.findAll();
            List<Advertisement> filterResults = new ArrayList<>();

            for (Tag tag : listOfTags) {
                for (Advertisement advertisement : advertisements) {
                    if (advertisement.getTags().contains(tag) && !filterResults.contains(advertisement) && advertisement.isActive()) {
                        filterResults.add(advertisement);
                    }
                }
            }
            return filterResults;
        }
        return repository.findAll();
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Service.Implementations;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Picture;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.AdvertisementRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Repositories.PictureRepository;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
    AdvertisementRepository advertisementRepository;
    PictureRepository pictureRepository;
    public static String UPLOAD_PICTURE_PATH = System.getProperty("user.dir")+"/src/main/resources/static/img/advertisementImages/";

    /**
     * Smiður fyrir AdvertisementServiceImplementation
     * @param advertisementRepository repository sem hefur samskipti við töfluna Advertisement í gagnagrunninum
     */
    @Autowired
    public AdvertisementServiceImplementation(AdvertisementRepository advertisementRepository, PictureRepository pictureRepository) {
        this.advertisementRepository = advertisementRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Advertisement> findByActive(boolean active) {
        return advertisementRepository.findByActive(active);
    }

    @Override
    public Advertisement save(Advertisement advertisement, Seller seller, MultipartFile picture) {
        // Byrjum á því að vista myndina
        String pictureName = StringUtils.cleanPath(picture.getOriginalFilename());
        Picture pic = new Picture();
        try {
            pic = new Picture(pictureName, picture.getContentType(), picture.getBytes());
        } catch (IOException e) {
            // Förum hingað ef valda myndin klikkar
            // Setja default mynd
            Resource resource = new ClassPathResource(UPLOAD_PICTURE_PATH+"default.jpg");
            try {
                // Reynum að sækja default mynd í file system
                InputStream inputStream = resource.getInputStream();
                pic = new Picture("default", "jpg", FileCopyUtils.copyToByteArray(inputStream));
            } catch (IOException ioException) {
                // Allt klikkar, ættum vonandi aldrei að lenda í þessu
                ioException.printStackTrace();
            }
        }
        pictureRepository.save(pic);

        // Næst vistum við auglýsinguna
        advertisement.setOwner(seller);
        advertisement.setCurrentAmount(advertisement.getOriginalAmount());
        advertisement.setCreatedAt(LocalDateTime.now());
        advertisement.setPicture(pic);

        System.out.println("Hér");
        return advertisementRepository.save(advertisement);
    }

    private Boolean uploadImage(MultipartFile picture, String pictureName) {
        Boolean tokst = false;
        List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/gif");
        // Ef mynd er með rétt extension
        if(contentTypes.contains(picture.getContentType())) {
            try {
                byte[] bytes = picture.getBytes();
                Path path = Paths.get(UPLOAD_PICTURE_PATH, pictureName);
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
        advertisementRepository.delete(advertisement);
    }

   @Override
    public void updateActive() {
        LocalDateTime lt = LocalDateTime.now();
        for(Advertisement ad: advertisementRepository.findAll()){
            if(ad.getCurrentAmount()<=0 || lt.compareTo(ad.getExpireDate())>0) {
                advertisementRepository.updateActive(ad.getId(),false);
            }
        }
    }

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> findByName(String name) {
        return advertisementRepository.findByName(name);
    }

    @Override
    public Optional<Advertisement> findById(long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public List<Advertisement> findByOwner(Seller seller) {
        return advertisementRepository.findByOwner(seller);
    }

    @Override
    public List<Advertisement> findByOwnerAndActive(Seller seller, boolean active) {
        return advertisementRepository.findByOwnerAndActive(seller, active);
    }

    @Override
    public List<Advertisement> findByKeyWord(String search) {
        if (search != null) {
            List<Advertisement> found = advertisementRepository.findByKeyWord(search);
            List<Advertisement> foundAndActive = new ArrayList<>();
            for (Advertisement ad : found) {
                if (ad.isActive()) {
                    foundAndActive.add(ad);
                }
            }
            return foundAndActive;
        }
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> filterBySellers(List<Seller> sellerList) {
        if (!sellerList.isEmpty()) {
            List<Advertisement> advertisementList = new ArrayList<Advertisement>();
            for (Seller seller : sellerList) {
                List<Advertisement> advertisementBySeller = advertisementRepository.findByOwner(seller);
                for (Advertisement advertisement : advertisementBySeller) {
                    if (advertisement.isActive()) {
                        advertisementList.add(advertisement);
                    }
                }
            }
            return advertisementList;
        }
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> filterByTags(List<String> tags) {

        if (!tags.isEmpty()) {

            List<Tag> listOfTags = new ArrayList<Tag>();
            for (String tag : tags) {
                listOfTags.add(Tag.valueOf(tag));
            }

            List<Advertisement> advertisements = advertisementRepository.findAll();
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
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> createdToday() {
        LocalDate lt = LocalDate.now();
        List<Advertisement> adToday = new ArrayList<>();
        for(Advertisement ad: advertisementRepository.findAll()){
            if(lt.isEqual(ad.getCreatedAt().toLocalDate())) {
                adToday.add(ad);
            }
        }
        return adToday;
    }

    @Override
    public List<Advertisement> expireToday() {
        LocalDate lt = LocalDate.now();
        List<Advertisement> exToday = new ArrayList<>();
        for(Advertisement ad: advertisementRepository.findAll()){
            if(lt.isEqual(ad.getExpireDate().toLocalDate())) {
                exToday.add(ad);
            }
        }
        return exToday;
    }
}

package is.hi.hbv501g.matbjorg.matbjorg.Controller;

import is.hi.hbv501g.matbjorg.matbjorg.Entities.Advertisement;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Seller;
import is.hi.hbv501g.matbjorg.matbjorg.Entities.Tag;
import is.hi.hbv501g.matbjorg.matbjorg.Service.AdvertisementService;
import is.hi.hbv501g.matbjorg.matbjorg.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private AdvertisementService advertisementService;
    private SellerService sellerService;

    @Autowired
    public SearchController(AdvertisementService advertisementService, SellerService sellerService) {
        this.advertisementService = advertisementService;
        this.sellerService = sellerService;
    }

    @RequestMapping("/search")
    public String Search(Model model) {
        model.addAttribute("advertisements", advertisementService.findAll());
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());
        return "search";
    }

    @RequestMapping("/showAllAdvertisements")
    public String showAll(Model model) {
        model.addAttribute("advertisements", advertisementService.findAll());
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());
        return "search";
    }

    @RequestMapping("/searchByKeyword")
    public String SearchByKeyword(@Param("search") String search, Model model) {
        List<Advertisement> advertisementList = advertisementService.findByKeyWord(search);
        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("tags", Tag.values());
        model.addAttribute("sellers", sellerService.findAll());
        return "search";
    }

    @RequestMapping("/filterBySellers")
    public String filterBySellers(@RequestParam List<String> sellers, Model model) {

        List<Seller> sellerList = new ArrayList<Seller>();

        for (String seller : sellers) {
            sellerList.add(sellerService.findByName(seller));
        }

        List<Advertisement> advertisementList = advertisementService.filterBySellers(sellerList);

        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("sellers", sellerService.findAll());
        model.addAttribute("tags", Tag.values());
        return "search";
    }


    @RequestMapping("/filterByTags")
    public String filterByTags(@RequestParam List<String> tags, Model model) {

        List<Advertisement> advertisementList = advertisementService.filterByTags(tags);

        model.addAttribute("advertisements", advertisementList);
        model.addAttribute("sellers", sellerService.findAll());
        model.addAttribute("tags", Tag.values());
        return "search";
    }

}

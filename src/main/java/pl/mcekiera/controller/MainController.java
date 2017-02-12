package pl.mcekiera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mcekiera.service.TorrentRecommendationsService;

@RestController
public class MainController {

    @RequestMapping("/")
    public String home(){
        TorrentRecommendationsService service = new TorrentRecommendationsService();

        return service.getRecommendedMovies("cekin@vp.pl").get(0).toString();
    }
}

package pl.mcekiera.controller;

import org.springframework.web.bind.annotation.*;
import pl.mcekiera.model.Movie;
import pl.mcekiera.service.TorrentRecommendationsService;

import java.util.List;

@RestController
public class RequestController {
    public static TorrentRecommendationsService service = new TorrentRecommendationsService();

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    List<Movie> recommend(@RequestParam(value="id", defaultValue="") String id){
        return service.getRecommendedMovies(id);
    }
}

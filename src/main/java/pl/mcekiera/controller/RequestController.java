package pl.mcekiera.controller;

import org.springframework.web.bind.annotation.*;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;
import pl.mcekiera.respository.DataAccessObject;
import pl.mcekiera.service.TorrentRecommendationsService;

import java.util.List;

@RestController
public class RequestController {
    public static TorrentRecommendationsService service = new TorrentRecommendationsService();

    @RequestMapping("/api/")
    public @ResponseBody
    List<Movie> getRecommendations(@RequestParam(value="id", defaultValue="default") String id){
        return service.getRecommendedMovies(id);
    }

    @RequestMapping(value = "/profiles/", method = RequestMethod.PUT)
    public @ResponseBody
    void addProfile(@RequestParam(value="id", defaultValue="") String id,
                           @RequestParam(value="rating") double rating,
                           @RequestParam(value="whitelist") String whitelist,
                           @RequestParam(value="blacklist") String blacklist){

        DataAccessObject<Profile> dao = new DataAccessObject<Profile>(Profile.class);
        dao.saveOrUpdate(new Profile(id, whitelist, blacklist, rating));
    }
}

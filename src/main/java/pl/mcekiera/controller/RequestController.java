package pl.mcekiera.controller;

import org.hibernate.AssertionFailure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mcekiera.model.Dismiss;
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

    @RequestMapping(value = "/api/dis/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity dismiss(@RequestParam(value="pid", required = true) String profileId,
                           @RequestParam(value="mid", required = true) String movieId){

        DataAccessObject<Dismiss> dao = new DataAccessObject<Dismiss>(Dismiss.class);
        Dismiss dismiss = new Dismiss(profileId, movieId);
        try {
            dao.saveOrUpdate(dismiss);
        } catch (AssertionFailure ex) {
            return new ResponseEntity<String>("Duplicate entry to database",HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<String>(dismiss.toString(),HttpStatus.OK);
    }
}

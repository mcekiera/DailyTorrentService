package pl.mcekiera.controller;

import org.apache.log4j.Logger;
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
    private static Logger log = Logger.getLogger(RequestController.class);
    public static TorrentRecommendationsService service = new TorrentRecommendationsService();

    @RequestMapping("/api/")
    public @ResponseBody
    List<Movie> getRecommendations(@RequestParam(value="id", defaultValue="default") String id){
        log.info("Get recommendations request for: " + id);
        return service.getRecommendedMovies(id);
    }

    @RequestMapping(value = "/api/profile/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addProfile(@RequestParam(value="id", defaultValue="") String id,
                           @RequestParam(value="rating") double rating,
                           @RequestParam(value="whitelist") String whitelist,
                           @RequestParam(value="blacklist") String blacklist){

        log.info("Add profile request: " + id);
        DataAccessObject<Profile> dao = new DataAccessObject<Profile>(Profile.class);
        Profile profile = new Profile(id, whitelist, blacklist, rating);
        dao.saveOrUpdate(profile);
        return  new ResponseEntity<String>(profile.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = "/api/dis/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity dismiss(@RequestParam(value="pid", required = true) String profileId,
                           @RequestParam(value="mid", required = true) String movieId){

        log.info("Dismiss request: " + profileId + "," + movieId);
        DataAccessObject<Dismiss> dao = new DataAccessObject<Dismiss>(Dismiss.class);
        DataAccessObject<Movie> daom = new DataAccessObject<>(Movie.class);
        Dismiss dismiss = new Dismiss(profileId, movieId);
        Movie movie = daom.find(movieId);
        movie.dismiss();
        try {
            daom.saveOrUpdate(movie);
            dao.saveOrUpdate(dismiss);
        } catch (AssertionFailure ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<String>("Duplicate database entry",HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<String>(dismiss.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = "/api/apr/", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity approve(@RequestParam(value="pid", required = true) String profileId,
                           @RequestParam(value="mid", required = true) String movieId){

        log.info("Approve request: " + profileId + "," + movieId);
        DataAccessObject<Movie> dao = new DataAccessObject<>(Movie.class);
        Movie movie = dao.find(movieId);
        movie.approve();
        try {
            dao.saveOrUpdate(movie);
        } catch (AssertionFailure ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<String>("Database error",HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<String>(movie.toString(),HttpStatus.OK);
    }
}

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

/**
 * REST Controller, supports application API.
 */
@RestController
public class RequestController {
    private static Logger log = Logger.getLogger(RequestController.class);
    public static TorrentRecommendationsService service = new TorrentRecommendationsService();

    /**
     * Provides data with movies recommended to given profile.
     * @param id of profile
     * @return JSON data with Movie objects representation
     */
    @RequestMapping("/api/")
    public @ResponseBody
    List<Movie> getRecommendations(@RequestParam(value="id", defaultValue="default") String id){
        log.info("Get recommendations request for: " + id);
        return service.getRecommendedMovies(id);
    }

    /**
     * Provides an endpoint, for adding new profile to database
     * @param id of profile, email address
     * @param rating minimal rating preferred by profile user
     * @param whitelist favourite movie genres of profile user
     * @param blacklist movie genres disliked by profile user
     * @return String information about profile and HttoStatus OK
     */
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

    /**
     * Provides and endpoint, to mark given movie as dismissed by user of given profile.
     * @param profileId of user
     * @param movieId of dismissed movie
     * @return String information about dismiss and HttoStatus OK if succeded, or HttpStatus Bad request
     * with error information if not
     */
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

    /**
     * Provides and endpoint, to mark given movie as approved by user of given profile.
     * @param profileId of user
     * @param movieId of approved movie
     * @return String information about dismiss and HttoStatus OK if succeded, or HttpStatus Bad request
     * with error information if not
     */
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

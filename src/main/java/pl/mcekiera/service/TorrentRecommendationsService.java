package pl.mcekiera.service;

import org.apache.log4j.Logger;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;
import pl.mcekiera.respository.DataAccessObject;

import java.util.List;

public class TorrentRecommendationsService {
    private static Logger log = Logger.getLogger(TorrentRecommendationsService.class);

    public List<Movie> getRecommendedMovies(String profileId) {
        log.info("Recommendations service for: " + profileId);

        DataAccessObject<Profile> profileDAO = new DataAccessObject<>(Profile.class);
        DataAccessObject<Movie> moviesDAO = new DataAccessObject<>(Movie.class);

        Profile user = profileDAO.find(profileId);
        String query = createQuery(user);
        log.info("Query content: " + query);
        return moviesDAO.query(query, 50);
    }

    private String createQuery(Profile user) {
        String whitelist = createAlternativesChain(user.getWhitelist(), "LIKE");
        String blacklist = createAlternativesChain(user.getBlacklist(), "NOT LIKE");

        return  "FROM Movie m WHERE m.rating >= " + user.getMinRating() + whitelist + blacklist +
                "AND (m.imdbId NOT IN (SELECT d.imdbId FROM Dismiss d WHERE d.profileId = '" + user + "'))" +
                " ORDER BY m.publicationDate desc, rating desc";
    }

    private String createAlternativesChain(String listAsString, String connector) {
        String result = "";
        if(!listAsString.equals("")) {
            result = " AND (";
            for (String item : listAsString.split(",")) {
                if (!item.equals("")) {
                    result += "m.genre " + connector + " '%" + item.trim() + "%' OR ";
                }
            }
            result = result.replaceAll("(?i)\\s*or\\s$", ")");
        }
        return result;
    }
}

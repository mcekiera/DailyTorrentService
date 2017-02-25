package pl.mcekiera.service;

import org.apache.log4j.Logger;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;
import pl.mcekiera.respository.DataAccessObject;

import java.util.List;

/**
 * Concrete implementation of RecommendationService interface, using Hibernate to retrieve data from SQL relational database.
 */
public class TorrentRecommendationService implements RecommendationService<Movie>{
    private static Logger log = Logger.getLogger(TorrentRecommendationService.class);

    @Override
    public List<Movie> getRecommendations(String profileId) {
        log.info("Recommendations recommendationService for: " + profileId);

        DataAccessObject<Profile> profileDAO = new DataAccessObject<>(Profile.class);
        DataAccessObject<Movie> moviesDAO = new DataAccessObject<>(Movie.class);

        Profile user = profileDAO.find(profileId);
        String query = createQuery(user);
        log.info("Query content: " + query);
        return moviesDAO.query(query, 50);
    }

    /**
     * Generates HQL query for given database structure for retrieving Movie objects for recommendation service.
     * In HGL query generation, it uses data from particular user Profile object.
     * @param user Profile object of given user
     * @return String with HQL query
     */
    private String createQuery(Profile user) {
        String whitelist = createAlternativesChain(user.getWhitelist(), "LIKE");
        String blacklist = createAlternativesChain(user.getBlacklist(), "NOT LIKE");

        return  "FROM Movie m WHERE m.rating >= " + user.getMinRating() + whitelist + blacklist +
                "AND (m.imdbId NOT IN (SELECT d.imdbId FROM Dismiss d WHERE d.profileId = '" + user + "'))" +
                " ORDER BY m.publicationDate desc, rating desc";
    }

    /**
     * Creates specific part of HQl query, containing alternative chain (like '... OR ... OR ...'), from provided list
     * in String form
     * @param listAsString list of items delimited by ','
     * @param connector SQL keyword for specific chained function (ex. 'LIKE,'NOT LIKE')
     * @return String with chained alternatives
     */
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

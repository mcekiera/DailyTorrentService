package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;
import pl.mcekiera.respository.DataAccessObject;

import java.util.List;

public class TorrentRecommendationsService {

    public List<Movie> getRecommendedMovies(String profileId) {
        DataAccessObject<Profile> profileDAO = new DataAccessObject<>(Profile.class);
        DataAccessObject<Movie> moviesDAO = new DataAccessObject<>(Movie.class);
        Profile user = profileDAO.find(profileId);

        return moviesDAO.query(createQuery(user.getMinRating(),user.getWhitelist(),user.getBlacklist()));
    }

    private String createQuery(double userRating, String userWhitelist, String userBlacklist) {
        String whitelist = "(";
        for(String item : userWhitelist.split(",")) {
            whitelist += "m.genre like '%" + item.trim() + "%' or ";
        }
        whitelist = whitelist.replaceAll("\\s*or\\s$",")");

        String blacklist = "(";
        for(String item : userBlacklist.split(",")) {
            blacklist += "m.genre not like '%" + item.trim() + "%' or ";
        }
        blacklist = blacklist.replaceAll("\\s*or\\s$",")");

        return  "from Movie m where m.rating >= " + userRating +
                " and " + whitelist + " and " + blacklist +
                "order by m.publicationDate desc, rating desc";
    }

    public static void main(String[] args) {
        TorrentRecommendationsService service = new TorrentRecommendationsService();

        System.out.println(service.getRecommendedMovies("cekin@vp.pl"));
    }
}

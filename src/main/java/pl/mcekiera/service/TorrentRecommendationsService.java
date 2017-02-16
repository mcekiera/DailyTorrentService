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
        String whitelist = createAlternativesChain(userWhitelist, "like");
        String blacklist = createAlternativesChain(userBlacklist, "not like");
//
//        if(!userWhitelist.equals("")) {
//            whitelist = "and (";
//            for (String item : userWhitelist.split(",")) {
//                whitelist += "m.genre like '%" + item.trim() + "%' or ";
//            }
//            whitelist = whitelist.replaceAll("\\s*or\\s$", ")");
//        }
//
//
//        if(!userBlacklist.equals("")) {
//            blacklist = "and (";
//            for (String item : userBlacklist.split(",")) {
//                if (!item.equals("")) {
//                    blacklist += "m.genre not like '%" + item.trim() + "%' or ";
//                }
//            }
//            blacklist = blacklist.replaceAll("\\s*or\\s$", ")");
//        }

        System.out.println("from Movie m where m.rating >= " + userRating +
                " and " + whitelist + " " + blacklist +
                " order by m.publicationDate desc, rating desc");

        return  "from Movie m where m.rating >= " + userRating + whitelist + blacklist +
                " order by m.publicationDate desc, rating desc";
    }

    private String createAlternativesChain(String listAsString, String connector) {
        String result = "";
        if(!listAsString.equals("")) {
            result = "and (";
            for (String item : listAsString.split(",")) {
                if (!item.equals("")) {
                    result += "m.genre " + connector + " '%" + item.trim() + "%' or ";
                }
            }
            result = result.replaceAll("\\s*or\\s$", ")");
        }
        return result;
    }

    public static void main(String[] args) {
        TorrentRecommendationsService service = new TorrentRecommendationsService();

        System.out.println(service.getRecommendedMovies("cekin@vp.pl"));
    }
}

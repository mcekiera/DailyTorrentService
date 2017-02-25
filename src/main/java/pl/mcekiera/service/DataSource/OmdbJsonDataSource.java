package pl.mcekiera.service.DataSource;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import pl.mcekiera.model.MovieBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data source implementation. Retrieves pieces of data from OMDB service.
 */
class OmdbJsonDataSource implements DataSource<MovieBuilder> {
    private static Logger log = Logger.getLogger(OmdbJsonDataSource.class);
    private final String source;
    private List<MovieBuilder> data;
    private final Pattern pattern;

    OmdbJsonDataSource(String url, List<MovieBuilder> list) {
        log.info("Retrieving movie data from URL: " + url);
        this.source = url;
        this.data = list;
        this.pattern = Pattern.compile("((?:.(?!(\\d{4})|HD|3D|LD|[\\[\\](){}]))*.).*(\\d{4})?.*");
    }

    /**
     * Process input and retrieved data, to combine it into complete MovieBuilders object.
     * @return List of MovieBuilders
     */
    @Override
    public List<MovieBuilder> getData() {
        List<MovieBuilder> result = null;
        try {
            result = combineData();
            if (result.size() == 0) {
                throw new InvalidDataSourceException(source);
            }
        } catch (InvalidDataSourceException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    /**
     * Combines YourBittorent data, with data retrieved from OMDB service, to create complate MovieBuilder object.
     * @return List of MovieBuilders
     */
    private List<MovieBuilder> combineData() {
        String title;
        String year;
        Matcher matcher;
        List<MovieBuilder> result = new ArrayList<>();

        for (MovieBuilder builder : data) {
            title = "?t=" + builder.getTorrentName();
            matcher = pattern.matcher(title);

            if(matcher.find()) {
                title = matcher.group(1).replaceAll("[\\s_.-]+", "+");
                year = null == matcher.group(2) ? "" : "&y=" + matcher.group(2);
                try {
                    result.add(getOmdbData(builder, source + title + year));
                } catch (InvalidDataSourceException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * Get data by HTTPRequest form OMDB Service, about proper movie title, year of release, IMDB rating, etc.
     * @param builder MovieBuilder with basic data from YouBittorent
     * @param url proper url for GET request
     * @return MovieBuilder object with complete movie data
     * @throws InvalidDataSourceException
     */
    private MovieBuilder getOmdbData(MovieBuilder builder, String url) throws InvalidDataSourceException {
        try {
            log.debug("Fetching data from URL: " + url);

            URL request = new URL(url);
            Scanner scanner = new Scanner(request.openStream());
            String response = scanner.useDelimiter("\\Z").next();
            JSONObject json = new JSONObject(response);
            scanner.close();

            log.debug("JSON: " + json);

            return builder.setTitle(json.getString("Title"))
                    .setYear(json.getString("Year"))
                    .setGenre(json.getString("Genre"))
                    .setImdbId(json.getString("imdbID"))
                    .setRating(json.getString("imdbRating"));

        } catch (IOException | JSONException e) {
            log.info("Exception during OMDB data retrieving");
            log.debug(e.getMessage());
            throw new InvalidDataSourceException(url);
        }
    }
}

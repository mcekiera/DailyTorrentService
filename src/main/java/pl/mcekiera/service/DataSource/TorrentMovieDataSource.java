package pl.mcekiera.service.DataSource;

import org.apache.log4j.Logger;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.MovieBuilder;
import pl.mcekiera.service.XMLSource.OnlineXmlSource;
import pl.mcekiera.service.XMLSource.XmlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves and parse data from YourBittoren and OMDB service, to create a list of newly added movies in torrent
 * repository. Suitable only for this particular service, with hardcoded parameters.
 */
public class TorrentMovieDataSource implements DataSource<Movie> {
    private static Logger log = Logger.getLogger(TorrentMovieDataSource.class);
    private final String rssUrl = "https://yourbittorrent.com/movies/rss.xml";
    private final String jsonUrl = "http://www.omdbapi.com/";

    /**
     * @return list of created Movie objects with data retrieved from YourBittoren and OMDB
     * @throws InvalidDataSourceException
     */
    @Override
    public List<Movie> getData() throws InvalidDataSourceException {
        return combineSources();
    }

    /**
     * Uses given data sources to retrieving and combining data fro Movie object creation
     * @return List of Movie objects
     */
    private List<Movie> combineSources() {
        List<Movie> movies = new ArrayList<>();
        List<MovieBuilder> temp = new ArrayList<>();


        log.info("RSS data processing from URL: " + rssUrl);
        XmlSource source = new OnlineXmlSource();
        YourBittorentRssDataSource rssDataSource = new YourBittorentRssDataSource(source.getDocument(rssUrl));

        try {
            temp = rssDataSource.getData();
        } catch (InvalidDataSourceException e) {
            log.info("Invalid data source exception");
            log.debug(e.getMessage());
        }
        log.info("RSS data collection finished");
        log.info("OMDB data processing form URL: " + jsonUrl);

        OmdbJsonDataSource jsonDataSource = new OmdbJsonDataSource(jsonUrl,temp);

        log.info("OMDB data collection succeeded");
        log.info("Creating movie objects");
        jsonDataSource.getData().forEach(item -> movies.add(item.build()));

        return movies;
    }

}

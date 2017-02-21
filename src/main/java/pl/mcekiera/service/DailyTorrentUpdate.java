package pl.mcekiera.service;

import org.apache.log4j.Logger;
import pl.mcekiera.model.Movie;
import pl.mcekiera.respository.DataAccessObject;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.InvalidDataSourceException;
import pl.mcekiera.service.DataSource.TorrentMovieDataSource;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Service, which fetch data from YourBittorrent RSS file, combine it with data from OMDB to create
 * list of Movie object, representing data about movie, and save it into database.
 */
public class DailyTorrentUpdate {
    private static Logger log = Logger.getLogger(DailyTorrentUpdate.class);
    private DataSource<Movie> source = new TorrentMovieDataSource();

    /**
     * Initialize process of data collecting and processing.
     */
    public void runService() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            log.info("Update service: start");

            Set<Movie> movies = new HashSet<>(source.getData());
            DataAccessObject<Movie> dao = new DataAccessObject<>(Movie.class);
            movies.forEach(dao::saveOrUpdate);

            log.info("Update service: done");
        }
        catch (InvalidDataSourceException e) {
            log.info("Invalid data source exception");
            log.error(e.getMessage());
        }
    }
}

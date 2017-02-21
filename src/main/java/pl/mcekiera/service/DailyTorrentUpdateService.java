package pl.mcekiera.service;

import org.apache.log4j.Logger;
import pl.mcekiera.model.Movie;
import pl.mcekiera.respository.DataAccessObject;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.InvalidDataSourceException;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Service, which fetch data from YourBittorrent RSS file, combine it with data from OMDB to create
 * list of Movie object, representing data about movie, and save it into database.
 */
public class DailyTorrentUpdateService implements UpdateService {
    private static Logger log = Logger.getLogger(DailyTorrentUpdateService.class);
    private DataSource<Movie> movieDataSource;

    public void setMovieDataSource(DataSource<Movie> movieDataSource) {
        this.movieDataSource = movieDataSource;
    }

    /**
     * Initialize process of data collecting and processing.
     */
    @Override
    public void runService() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            log.info("Update recommendationService: start");

            Set<Movie> movies = new HashSet<>(movieDataSource.getData());
            DataAccessObject<Movie> dao = new DataAccessObject<>(Movie.class);
            movies.forEach(dao::saveOrUpdate);

            log.info("Update recommendationService: done");
        }
        catch (InvalidDataSourceException e) {
            log.info("Invalid data source exception");
            log.error(e.getMessage());
        }
    }
}

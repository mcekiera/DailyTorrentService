package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.respository.DataAccessObject;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.InvalidDataSourceException;
import pl.mcekiera.service.DataSource.TorrentMovieDataSource;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class DailyTorrentUpdate {
    private DataSource<Movie> source = new TorrentMovieDataSource();

    public void runService() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            System.out.print("Start");

            Set<Movie> movies = new HashSet<>(source.getData());
            DataAccessObject<Movie> dao = new DataAccessObject<>(Movie.class);
            movies.forEach(dao::saveOrUpdate);

            System.out.println("Done");
        }
        catch (InvalidDataSourceException e) {
            System.out.print("Main ex");
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new DailyTorrentUpdate().runService();
    }
}

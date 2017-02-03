package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.InvalidDataSourceException;
import pl.mcekiera.service.DataSource.TorrentMovieDataSource;

import java.util.Locale;

public class DailyTorrentService {
    private DataSource<Movie> source = new TorrentMovieDataSource();

    public DailyTorrentService() {
        Locale.setDefault(Locale.ENGLISH);
        try {

            source.getData().forEach(System.out::println);
            System.out.print("Done");
        } catch (InvalidDataSourceException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new DailyTorrentService();
    }
}

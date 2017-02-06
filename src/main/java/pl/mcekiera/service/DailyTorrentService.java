package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.InvalidDataSourceException;
import pl.mcekiera.service.DataSource.TorrentMovieDataSource;

import java.util.List;
import java.util.Locale;

public class DailyTorrentService {
    private DataSource<Movie> source = new TorrentMovieDataSource();

    public DailyTorrentService() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            System.out.print("Start");
            List<Movie> list = source.getData();
            System.out.print(list.size());
            System.out.print("Done");
        } catch (InvalidDataSourceException e) {
            System.out.print("Main ex");
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new DailyTorrentService();
    }
}
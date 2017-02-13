package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.service.DataSource.DataSource;
import pl.mcekiera.service.DataSource.TorrentMovieDataSource;

import java.util.Locale;

public class DailyTorrentService {
    private DataSource<Movie> source = new TorrentMovieDataSource();

    public void runService() {
        Locale.setDefault(Locale.ENGLISH);
//        try {
            System.out.print("Start");

//            List<Movie> list = source.getData();
//            DataAccessObject<Movie> dao = new DataAccessObject<>(Movie.class);
//            list.forEach(dao::saveOrUpdate);

//            System.out.println(list.size());
            System.out.println("Done");
//        }
//        catch (InvalidDataSourceException e) {
//            System.out.print("Main ex");
//            e.printStackTrace();
//            e.getMessage();
//        }
    }
}

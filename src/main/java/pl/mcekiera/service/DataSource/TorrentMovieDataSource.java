package pl.mcekiera.service.DataSource;

import pl.mcekiera.model.Movie;
import pl.mcekiera.model.MovieBuilder;

import java.util.ArrayList;
import java.util.List;

public class TorrentMovieDataSource implements DataSource<Movie> {
    private final String rssUrl = "https://yourbittorrent.com/movies/rss.xml";
    private final String jsonUrl = "http://www.omdbapi.com/";


    @Override
    public List<Movie> getData() throws InvalidDataSourceException {
        return combineSources();
    }

    private List<Movie> combineSources() {
        List<Movie> movies = new ArrayList<>();
        List<MovieBuilder> temp = new ArrayList<>();


        System.out.print("Open service");
        YourBittorentRssDataSource rssDataSource = new YourBittorentRssDataSource(rssUrl);
        System.out.print("RSS retrieved");
        try {
            temp = rssDataSource.getData();
        } catch (InvalidDataSourceException e) {
            e.printStackTrace();
        }
        OmdbJsonDataSource jsonDataSource = new OmdbJsonDataSource(jsonUrl,temp);
        System.out.print("JSON retrieved");


        jsonDataSource.getData().forEach(item -> movies.add(item.build()));

        return movies;
    }

}

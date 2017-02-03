package pl.mcekiera.service.DataSource;

import pl.mcekiera.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class TorrentMovieDataSource implements DataSource<Movie> {
    private final String rssUrl = "https://yourbittorrent.com/movies/rss.xml";
    private final String jsonUrl = "http://www.omdbapi.com/";


    @Override
    public List<Movie> getData() throws InvalidDataSourceException {
        return combineSources();
    }

    private List<Movie> combineSources() throws InvalidDataSourceException {
        System.out.print("Open service");
        YourBittorentRssDataSource rssDataSource = new YourBittorentRssDataSource(rssUrl);
        System.out.print("RSS retrieved");
        OmdbJsonDataSource jsonDataSource = new OmdbJsonDataSource(jsonUrl,rssDataSource.getData());
        System.out.print("JSON retrieved");
        List<Movie> movies = new ArrayList<>();

        jsonDataSource.getData().forEach(item -> movies.add(item.build()));
        return movies;
    }

}

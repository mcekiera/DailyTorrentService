package pl.mcekiera.service.DataSource;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.mcekiera.model.MovieBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse data from RSS XML source into list of MovieBuilders with partly inserted data.
 */
public class YourBittorentRssDataSource implements DataSource<MovieBuilder> {
    private static Logger log = Logger.getLogger(YourBittorentRssDataSource.class);
    private Document xml;

    public YourBittorentRssDataSource(Document xml) {
        this.xml = xml;
    }

    /**
     * Provides collection of MovieBuilders partly filled with data necessary to create Movie instance
     * @return MovieBuilder collection
     * @throws InvalidDataSourceException if source does not contain XML file.
     */

    public List<MovieBuilder> getData() throws InvalidDataSourceException {
        return parseData(xml);
    }

    /**
     * Retrieves information from XML file and creates MovieBuilders with provided data
     * @param doc XML Document
     * @return List of MovieBuilders
     */
    private List<MovieBuilder> parseData(Document doc) {
        Elements list = doc.getElementsByTag("item");
        List<MovieBuilder> data = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            data.add(createMovieBuilder(list,i));
        }

        return data;
    }

    /**
     * Creates MovieBuilder instance with provided data
     * @param list XML Elements list
     * @param i index of current element
     * @return MovieBuilder
     */
    private MovieBuilder createMovieBuilder(Elements list, int i) {
        System.out.println(list.get(i));
        String title = list.get(i).getElementsByTag("title").text();
        String link = list.get(i).getElementsByTag("link").text();
        String date = list.get(i).getElementsByTag("pubDate").text();
        return new MovieBuilder().setTorrentName(title).setTorrentUrl(link).setPublication(date);
    }


}

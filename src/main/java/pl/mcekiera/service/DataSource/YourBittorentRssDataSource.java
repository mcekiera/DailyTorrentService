package pl.mcekiera.service.DataSource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.mcekiera.model.MovieBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse data from RSS XML source into list of MovieBuilders with partly inserted data.
 */
class YourBittorentRssDataSource implements DataSource<MovieBuilder> {
    private static Logger log = Logger.getLogger(YourBittorentRssDataSource.class);
    private final String source;

    YourBittorentRssDataSource(String url) {
        log.info("Parsing RSS source: " + url);
        this.source = url;
    }

    /**
     * Provides collection of MovieBuilders partly filled with data necessary to create Movie instance
     * @return MovieBuilder collection
     * @throws InvalidDataSourceException if source does not contain XML file.
     */
    @Override
    public List<MovieBuilder> getData() throws InvalidDataSourceException {
        try {
            Document xmlDoc = fetchDocument(source);
            System.out.println("Fetching XML data from: " + source);
            return parseData(xmlDoc);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new InvalidDataSourceException(source);
        }
    }

    /**
     * Retrieve XML document from given URL
     * @param source String with URL
     * @return XML Document
     * @throws IOException
     */
    private Document fetchDocument(String source) throws IOException {
        Document xmlDoc = null;
        try {
            xmlDoc = Jsoup.connect(source).get();
        } catch (MalformedURLException | IllegalArgumentException ex) {
            log.error(ex.getMessage());
//            TODO: implementation only for testing!
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(source).getFile());
            xmlDoc = Jsoup.parse(file, "UTF-8");
        }
        return xmlDoc;
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
        String title = list.get(i).getElementsByTag("title").text();
        String link = list.get(i).getElementsByTag("link").text();
        String date = list.get(i).getElementsByTag("pubDate").text();
        return new MovieBuilder().setTorrentName(title).setTorrentUrl(link).setPublication(date);
    }

}

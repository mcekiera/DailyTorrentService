package pl.mcekiera.service.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.mcekiera.model.MovieBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class YourBittorentRssDataSource implements DataSource<MovieBuilder> {
    private final String source;
    private boolean valid;

    public YourBittorentRssDataSource(String url) {
        this.source = url;
    }


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

    private Document fetchDocument(String source) throws IOException {
        Document xmlDoc = null;
        try {
            xmlDoc = Jsoup.connect(source).get();
        } catch (MalformedURLException | IllegalArgumentException ex) {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(source).getFile());
            xmlDoc = Jsoup.parse(file, "UTF-8");
        }
        return xmlDoc;
    }


    private List<MovieBuilder> parseData(Document doc) {
        Elements list = doc.getElementsByTag("item");
        List<MovieBuilder> data = new ArrayList<>();
        String title;
        String link;
        String date;

        for (int i = 0; i < list.size(); i++) {
            title = list.get(i).getElementsByTag("title").text();
            link = list.get(i).getElementsByTag("link").text();
            date = list.get(i).getElementsByTag("pubDate").text();
            MovieBuilder temp = new MovieBuilder().setTorrentName(title).setTorrentUrl(link).setPublication(date);
            System.out.println(temp.toString());
            data.add(temp);
        }

        return data;

    }

}

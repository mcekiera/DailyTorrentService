package pl.mcekiera.service.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import pl.mcekiera.model.MovieBuilder;
import pl.mcekiera.service.XMLSource.LocalXmlSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class YourBitttorentRssDataSourceTest {

    @Test
    public void getData() {
        LocalXmlSource source = new LocalXmlSource();
        String path = getClass().getClassLoader().getResource("test.xml").getPath();
        Document document = new Document("");

        try {
            document = Jsoup.parse(new File(path),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        YourBittorentRssDataSource bittorentRssDataSource = new YourBittorentRssDataSource(document);
        List<MovieBuilder> list = new ArrayList<>();

        try {
            list = bittorentRssDataSource.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MovieBuilder builder = list.get(0);

        assertEquals("Title",builder.getTorrentName());
        assertEquals(new Date(0),builder.getPublication());
    }
}

package pl.mcekiera.service.XMLSource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class OnlineXmlSource implements XmlSource{
    private static Logger log = Logger.getLogger(OnlineXmlSource.class);

    @Override
    public Document getDocument(String source) {
        Document xmlDoc = null;
        try {
            xmlDoc = Jsoup.connect(source).get();
        } catch (IllegalArgumentException | IOException ex) {
            log.error(ex.getMessage());

        }
        return xmlDoc;
    }
}

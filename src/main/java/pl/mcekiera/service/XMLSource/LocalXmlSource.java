package pl.mcekiera.service.XMLSource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * Implementation for parsing local XML files. For testing purposes.
 */
public class LocalXmlSource implements XmlSource {
    private static Logger log = Logger.getLogger(OnlineXmlSource.class);

    @Override
    public Document getDocument(String source) {
        Document xmlDoc = null;
        File file = new File(source);
        try {
            xmlDoc = Jsoup.parse(file,"UTF-8");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return xmlDoc;
    }
}

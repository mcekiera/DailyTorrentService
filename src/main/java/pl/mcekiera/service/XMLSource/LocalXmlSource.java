package pl.mcekiera.service.XMLSource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class LocalXmlSource implements XmlSource {
    private static Logger log = Logger.getLogger(OnlineXmlSource.class);

    @Override
    public Document getDocument(String source) {
        Document xmlDoc = null;
        File file = new File(source);
        try {
            xmlDoc = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return xmlDoc;
    }
}

package pl.mcekiera.service.XMLSource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

public class LocalXmlSource {
    private static Logger log = Logger.getLogger(OnlineXmlSource.class);


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

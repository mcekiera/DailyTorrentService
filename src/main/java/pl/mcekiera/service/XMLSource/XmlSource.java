package pl.mcekiera.service.XMLSource;

import org.jsoup.nodes.Document;

/**
 * Provides a way to diversify XML sources parsed in production and test code. Therefor XML documents could be parsed
 * by different tools, as long as finally will be passed as a Jsoup Document object.
 */
public interface XmlSource {
    /**
     * @param source URL to XML source
     * @return Jsoup Document object
     */
    Document getDocument(String source);
}

package pl.mcekiera.service.XMLSource;

import org.jsoup.nodes.Document;

public interface XmlSource {
    Document getDocument(String source);
}

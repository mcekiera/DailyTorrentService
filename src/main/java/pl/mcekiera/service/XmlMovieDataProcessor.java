package pl.mcekiera.service;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import pl.mcekiera.model.RawXmlMovieData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlMovieDataProcessor {
    private List<RawXmlMovieData> data;

    public boolean fetchData(String rssUrl) {

        try {
            URL url = new URL(rssUrl);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(url.openStream());
            data = parseData(xmlDoc);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public List<RawXmlMovieData> getRawXmlMovieData() {
        return null;
    }

    private List<RawXmlMovieData> parseData(Document doc) {
        NodeList list = doc.getElementsByTagName("item");
        List<RawXmlMovieData> data = new ArrayList<>();

        for (int i = 0; i < list.getLength(); i++) {
            String title = list.item(i).getChildNodes().item(1).getTextContent();
            String link = list.item(i).getChildNodes().item(3).getTextContent();
            String date = list.item(i).getChildNodes().item(5).getTextContent();
            RawXmlMovieData movie = new RawXmlMovieData(title, link, date);
            data.add(movie);
        }

        return data;
    }
}

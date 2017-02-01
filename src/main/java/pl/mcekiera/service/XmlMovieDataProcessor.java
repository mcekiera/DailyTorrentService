package pl.mcekiera.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.mcekiera.model.RawXmlMovieData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetch data in form of XML file from RSS source and process it to extract basic data about torrent movie files.
 */

public class XmlMovieDataProcessor {
    /**
     * List of RawXmlMovieData object, containing basic data about torrent files published in RSS source.
     */
    private List<RawXmlMovieData> data;

    /**
     * Fetch data from RSS source, and collect basic torrent movie data to populate data list.
     * @param rssUrl URL address from which data is fetched.
     * @return true if data was collected properly, or false, if any exception occurred, and therefore no data was
     * retrieved from source.
     */
    public boolean fetchData(String rssUrl) {

        try {
            URL url = new URL(rssUrl);

//            String out = new Scanner(new URL(rssUrl).openStream(), "UTF-8").useDelimiter("\\A").next();
//            out = out.replace("”|”","\"");
//            System.out.println(out);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = Jsoup.connect(rssUrl).get();
            data = parseData(xmlDoc);
            return true;
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }

    }

    /**
     * @return RawXmlMovieData objects list with data retrieved from RSS source.
     */
    public List<RawXmlMovieData> getRawXmlMovieData() {
        return data;
    }

    /**
     * Retrieves chosen data items and creates RawXmlMovieData object.
     * @param doc Document object representing fetched XML file.
     * @return list of retrieved RawXmlMovieData objects.
     */
    private List<RawXmlMovieData> parseData(Document doc) {
        Elements list = doc.getElementsByTag("item");
        List<RawXmlMovieData> data = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i).getElementsByTag("title").text();
            String link = list.get(i).getElementsByTag("link").text();
            String date = list.get(i).getElementsByTag("pubDate").text();
            data.add(new RawXmlMovieData(title, link, date));
        }

        return data;
    }
}

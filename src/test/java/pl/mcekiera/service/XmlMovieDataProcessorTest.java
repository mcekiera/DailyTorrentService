package pl.mcekiera.service;

import org.junit.Test;
import pl.mcekiera.model.RawXmlMovieData;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class XmlMovieDataProcessorTest {
    private XmlMovieDataProcessor processor = new XmlMovieDataProcessor();
    private ClassLoader loader = getClass().getClassLoader();

    @Test
    public void fetchDataTest() {
        String url = null;
        try {
            url = loader.getResource("rss.xml").toURI().toURL().toString();
        } catch (MalformedURLException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>" + url);
        assertTrue(processor.fetchData(url));
        assertFalse(processor.fetchData("notExistingFile.xml"));
    }

    @Test
    public void getRawXmlMovieDataTest() {
        RawXmlMovieData m0 = new RawXmlMovieData("Saat Uchakkey 2016 Hindi P-DvDRip-x264-MP3-Zi$t-WWRG", "https://yourbittorrent.com/torrent/10516165/saat-uchakkey-hindi-p-mp3-zi%24t-wwrg.html", "Fri, 20 Jan 2017 08:50:41 +0100");
        RawXmlMovieData m1 = new RawXmlMovieData("Jurm (2005) DVDRip x264 AAC","https://yourbittorrent.com/torrent/10516164/jurm-aac.html","Fri, 20 Jan 2017 08:50:41 +0100");
        RawXmlMovieData m2 = new RawXmlMovieData("The Science Of Disinformation -SecInc","https://yourbittorrent.com/torrent/10516161/the-science-of-disinformation-secinc.html","Fri, 20 Jan 2017 08:50:39 +0100");

        String url = null;
        try {
            url = loader.getResource("rss.xml").toURI().toURL().toString();
        } catch (MalformedURLException | URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }

        processor.fetchData(url);

        assertEquals(m0,processor.getRawXmlMovieData().get(0));
        assertEquals(m1,processor.getRawXmlMovieData().get(1));
        assertEquals(m2,processor.getRawXmlMovieData().get(2));
    }

    @Test
    public void equals() {
        RawXmlMovieData m1 = new RawXmlMovieData("title1", "link1", "01.01.01");
        RawXmlMovieData m2 = new RawXmlMovieData("title1", "link1", "01.01.01");
        RawXmlMovieData m3 = new RawXmlMovieData("title3", "link1", "01.01.01");
        RawXmlMovieData m4 = new RawXmlMovieData("title1", "link1", "01.01.04");
        RawXmlMovieData m5 = new RawXmlMovieData("title1", "link5", "01.01.01");

        assertEquals(m1,m2);
        assertNotEquals("Not null if title is different", m1,m3);
        assertNotEquals("Not null if link is different", m1,m5);
        assertNotEquals("Not null if date is different", m1,m4);
    }


}

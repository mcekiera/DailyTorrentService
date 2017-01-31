package pl.mcekiera.model;

import java.util.Date;

public class RawXmlMovieData {
    private final String title;
    private final String link;
    private final String publicationDate;

    public RawXmlMovieData(String title, String link, String date) {
        this.title = title;
        this.link = link;
        this.publicationDate = date;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }


}

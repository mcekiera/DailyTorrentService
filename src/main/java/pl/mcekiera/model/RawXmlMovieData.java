package pl.mcekiera.model;

/**
 * Represents raw torrent movie data fetched from XML file (RSS source), without a movie details.
 */

public class RawXmlMovieData {
    /**
     * Torrent file title, containing movie title.
     */
    private final String title;
    /**
     * Link to torrent file.
     */
    private final String link;
    /**
     * Date of torrent file publication in String form.
     */
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

    public String getPublicationDate() {
        return publicationDate;
    }


}

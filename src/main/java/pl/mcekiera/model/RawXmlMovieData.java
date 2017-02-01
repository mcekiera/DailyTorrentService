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

    /**
     * @return title property, represents title of movie described by retrieved data
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return link property, represents link to torrent file with movie described by retrieved data
     */
    public String getLink() {
        return link;
    }

    /**
     * @return  publication date property, represents publication date of torrent file with movie
     * described by retrieved data
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Determines if given RawXmlMovieData object is equal, understood as containing same fields values, to another object.
     * @param o another object.
     * @return return true if another object has same fields values as calling object.
     */
    @Override
    public boolean equals(Object o){
        boolean result;
        if((o == null) || (getClass() != o.getClass())){
            result = false;
        } else{
            RawXmlMovieData movie = (RawXmlMovieData)o;
            result = title.equals(movie.title) && link.equals(movie.link) && publicationDate.equals(movie.publicationDate);
        }

        return result;
    }

    /**
     * Creates hashcode using all objects fields.
     * @return hashcode for object
     */
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + publicationDate.hashCode();
        return result;
    }

}

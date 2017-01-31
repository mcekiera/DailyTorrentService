package pl.mcekiera.model;

import java.util.Collections;
import java.util.List;

public class Profile {
    private static int idCount = 0;

    private final int id;
    private final String login;
    private List<String> favourites;
    private List<String> disliked;

    public Profile(String login) {
        this.id = ++idCount;
        this.login = login;
    }

    public void addFavourites(String genre) {
        favourites.add(genre);
    }

    public void removeFavourites(String genre) {
        favourites.remove(genre);
    }

    public List<String> getFavourites() {
        return Collections.unmodifiableList(favourites);
    }

    public void addDisliked(String genre) {
        disliked.add(genre);
    }

    public void removeDisliked(String genre) {
        disliked.remove(genre);
    }

    public List<String> getDisliked() {
        return Collections.unmodifiableList(disliked);
    }

}

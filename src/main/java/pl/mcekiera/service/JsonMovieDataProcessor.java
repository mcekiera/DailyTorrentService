package pl.mcekiera.service;

import org.json.JSONException;
import org.json.JSONObject;
import pl.mcekiera.model.RawJsonMovieData;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class JsonMovieDataProcessor {
    private RawJsonMovieData data;

    public boolean fetchData(String url) {

        try {

            URL request = new URL(url);

            Scanner scanner = new Scanner(request.openStream());
            String response = scanner.useDelimiter("\\Z").next();
            System.out.println(response);
            JSONObject json = new JSONObject(response);
            scanner.close();

            data = new RawJsonMovieData(json.getString("Title"),
                    json.getString("Year"),
                    json.getString("Genre"),
                    json.getString("imdbRating"),
                    json.getString("imdbID"));

            return true;
        } catch (IOException | JSONException e) {
            System.out.println(url);
            e.printStackTrace();
            return false;
        }


    }

    public RawJsonMovieData getRawJsonMovieData() {
        return data;
    }
}

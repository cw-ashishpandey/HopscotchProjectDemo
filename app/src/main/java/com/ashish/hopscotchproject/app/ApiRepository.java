package com.ashish.hopscotchproject.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by amit.chauhan on 05-Sep-16.
 */
public class ApiRepository {
    private static String apiKey = "9txsnh3qkb5ufnphhqv5tv5z";

    public static String getMovieListUrl(String searchString) {
        String query = null;
        try {
            query = URLEncoder.encode(searchString, "Utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new StringBuilder("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + apiKey).append("&q=" + query).append("&page_limit=" + 5).toString();
    }

    public static String getMovieDetailUrl(String movieId) {
        return new StringBuilder("http://api.rottentomatoes.com/api/public/v1.0/movies/" + movieId + ".json?apikey=" + apiKey).toString();
    }
}

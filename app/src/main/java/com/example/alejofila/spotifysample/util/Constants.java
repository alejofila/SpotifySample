package com.example.alejofila.spotifysample.util;

/**
 * Created by alejofila on 15/09/15.
 */
public class Constants {
    public static final String BASE_URL = "https://api.spotify.com/v1/";
    public static final String  SEARCH_URL ="search";
    public static final String ALBUMS_URL = "artists/{}/albums";


    /**
     * HTTP PARAMS
     */
    public static final String PARAM_KEY_QUERY = "q";
    public static final String PARAM_KEY_TYPE="type";
    public final class ArtistKey{
        public final static String NAME ="name";
        public final static String POPULARITY ="popularity";
        public final static String FOLLOWERS ="followers";
        public final static String IMAGE = "image";
        public final static String ID ="id";
    }
}

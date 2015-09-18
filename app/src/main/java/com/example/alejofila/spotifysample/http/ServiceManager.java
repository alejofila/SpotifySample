package com.example.alejofila.spotifysample.http;

import android.util.Log;

import com.example.alejofila.spotifysample.util.Constants;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by alejofila on 15/09/15.
 */
public class ServiceManager {
    private static final String TAG =ServiceManager.class.getSimpleName() ;

    public ServiceManager(){

    }

    public static void searchArtist(String artist, Callback clientCallback){

        OkHttpClient client = new OkHttpClient();
        String strUrl = Constants.BASE_URL + Constants.SEARCH_URL;
        strUrl+="?"
                +Constants.PARAM_KEY_QUERY+"="+artist
                +"&"
                +Constants.PARAM_KEY_TYPE+"=artist";
        Log.i(TAG, "This is the target URL " + strUrl);
        Request request = new Request.Builder()
                .url(strUrl)
                .build();

        client.newCall(request).enqueue(clientCallback);
    }
    public static void searchAlbum(String artistID, Callback clientCallback){

        OkHttpClient client = new OkHttpClient();
        String strUrl = Constants.BASE_URL + Constants.ALBUMS_URL;
        strUrl = strUrl.replace("{}",artistID);
        Log.i(TAG, "This is the target URL " + strUrl);
        Request request = new Request.Builder()
                .url(strUrl)
                .build();

        client.newCall(request).enqueue(clientCallback);
    }
}

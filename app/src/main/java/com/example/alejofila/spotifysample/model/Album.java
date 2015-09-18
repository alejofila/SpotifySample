package com.example.alejofila.spotifysample.model;

import java.util.ArrayList;
import com.google.gson.annotations.*;

/**
 * Created by alejofila on 15/09/15.
 *
 * This is the POJO for receive the JSON payload
 */
public class Album {
    private String name;

    private ArrayList<SpotifyImage> images;

    @SerializedName(value = "external_urls")
    private ExternalUrl externalUrl;

    @SerializedName(value = "available_markets")
    private ArrayList<String> availableCountry;

    public String  showAvaibleCountrys(){
        StringBuilder sb = new StringBuilder("Only available in: ");

        for( String market: availableCountry) {
            sb.append(market);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExternalUrl getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(ExternalUrl externalUrl) {
        this.externalUrl = externalUrl;
    }

    public ArrayList<SpotifyImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<SpotifyImage> images) {
        this.images = images;
    }

    public ArrayList<String> getAvailableCountry() {
        return availableCountry;
    }

    public void setAvailableCountry(ArrayList<String> availableCountry) {
        this.availableCountry = availableCountry;
    }
}

package com.example.alejofila.spotifysample.model;
import com.google.gson.annotations.SerializedName;
/**
 * Created by alejofila on 17/09/15.
 */
public class ExternalUrl {

    @SerializedName(value = "spotify")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

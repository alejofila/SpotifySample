package com.example.alejofila.spotifysample.model;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alejofila on 15/09/15.
 */
public class Artist {

    private String name;
    @SerializedName(value = "followers")
    private Follower followersInfo;

    private float popularity;
    private ArrayList<SpotifyImage> images;
    private ArrayList<Album> albums;

    @SerializedName(value="id")
    private String spotifyID;

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }




    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSpotifyID() {
        return spotifyID;
    }

    public void setSpotifyID(String spotifyID) {
        this.spotifyID = spotifyID;
    }

    public Follower getFollowersInfo() {
        return followersInfo;
    }

    public void setFollowersInfo(Follower followersInfo) {
        this.followersInfo = followersInfo;
    }



    public ArrayList<SpotifyImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<SpotifyImage> images) {
        this.images = images;
    }
    public String toString(){
        return "Spotify id: "+spotifyID+
                "name"+name +
                " "+ followersInfo.toString()+ " popularity stat: "+popularity+
                images.toString();
    }
}

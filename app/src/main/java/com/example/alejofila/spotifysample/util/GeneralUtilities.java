package com.example.alejofila.spotifysample.util;

import android.os.Bundle;

import com.example.alejofila.spotifysample.model.Artist;
import com.example.alejofila.spotifysample.model.Follower;
import com.example.alejofila.spotifysample.model.SpotifyImage;

import java.util.ArrayList;

/**
 * Created by alejofila on 16/09/15.
 */
public class GeneralUtilities {

    public static Bundle fromArtistToBundle(Artist obj) {
        Bundle b = new Bundle();
        b.putString(Constants.ArtistKey.NAME, obj.getName());
        b.putInt(Constants.ArtistKey.FOLLOWERS, obj.getFollowersInfo().getTotal());
        b.putString(Constants.ArtistKey.IMAGE, obj.getImages().get(0).getUrl());
        b.putFloat(Constants.ArtistKey.POPULARITY, obj.getPopularity());
        b.putString(Constants.ArtistKey.ID, obj.getSpotifyID());
        return b;
    }

    public static Artist fromBundleToArtist(Bundle b) {
        Artist artist = new Artist();
        artist.setName(b.getString(Constants.ArtistKey.IMAGE));
        artist.setSpotifyID(b.getString(Constants.ArtistKey.ID));
        artist.setPopularity(b.getFloat(Constants.ArtistKey.POPULARITY));
        artist.setFollowersInfo(new Follower(b.getInt(Constants.ArtistKey.FOLLOWERS)));
        ArrayList<SpotifyImage> images = new ArrayList<SpotifyImage>();
        images.add(new SpotifyImage(b.getString(Constants.ArtistKey.IMAGE)));
        artist.setImages(images);
        return artist;


    }
}


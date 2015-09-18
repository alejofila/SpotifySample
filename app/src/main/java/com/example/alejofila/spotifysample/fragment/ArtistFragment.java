package com.example.alejofila.spotifysample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alejofila.spotifysample.R;
import com.example.alejofila.spotifysample.http.ServiceManager;
import com.example.alejofila.spotifysample.model.Album;
import com.example.alejofila.spotifysample.model.AlbumDeserializer;
import com.example.alejofila.spotifysample.model.Artist;
import com.example.alejofila.spotifysample.model.ArtistDeserializer;
import com.example.alejofila.spotifysample.util.CircleTransform;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alejofila on 16/09/15.
 */
public class ArtistFragment extends Fragment implements View.OnClickListener{
    public static final String TAG = ArtistFragment.class.getSimpleName();
    private Artist artist;

    private Handler mHandler;

    /**
     * UI VARIABLES
     */
    private ImageView imageArtist;
    private TextView txtName;
    private TextView txtFollowers;
    private TextView txtPopularity;
    private ProgressBar pb;
    private Button btnAlbums;
    private LinearLayout infoLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist,container,false);
        imageArtist= (ImageView) rootView.findViewById(R.id.img_artist);
        txtName =(TextView) rootView.findViewById(R.id.txt_artist_name);
        txtFollowers = (TextView) rootView.findViewById(R.id.txt_followers);
        txtPopularity =(TextView) rootView.findViewById(R.id.txt_popularity);
        btnAlbums =(Button) rootView.findViewById(R.id.btnAlbums);
        pb = (ProgressBar) rootView.findViewById(R.id.progress_bar_artist);
        infoLayout = (LinearLayout) rootView.findViewById(R.id.layout_info_artist);
        btnAlbums.setOnClickListener(this);

        mHandler = new Handler();
        /*
        if (getArguments()!= null)
        {
            loadUI(getArguments());
        }
        */
        loadUI();
        return rootView;
    }
    private void loadUI(){
        txtName.setText(artist.getName());
        Log.i(TAG,"amount of followers: "+artist.getFollowersInfo().getTotal());
        txtFollowers.setText("" + artist.getFollowersInfo().getTotal());
        txtPopularity.setText(""+artist.getPopularity());
        Picasso.with(getActivity())
                .load(artist.getImages().get(0).getUrl())
                .transform(new CircleTransform())
                .placeholder(R.drawable.ic_action_guitar)
                .fit()
                .into(imageArtist);

    }

    public void hideUI(){
        infoLayout.setVisibility(View.GONE);

    }

    public void showUI(){
        infoLayout.setVisibility(View.VISIBLE);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAlbums :
                hideUI();
                pb.setVisibility(View.VISIBLE);
                showAlbums();

                break;

        }
    }

    private void showAlbums() {
        findAlbums(artist.getSpotifyID());
    }

    private void findAlbums(String spotifyID) {

        Callback clientCallbak = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "Error");
                mOnGetAlbums.onGetAlbumsError(getString(R.string.error_connection));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showUI();
                        pb.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.VISIBLE);

                    }
                });

                String jsonString = response.body().string();
                Gson gson = new GsonBuilder().
                        registerTypeAdapter(Album[].class, new AlbumDeserializer())
                        .create();
                Album[] albums = gson.fromJson(jsonString, Album[].class);
                if(albums.length> 0)
                    mOnGetAlbums.onGetAlbumsSuccesfull(albums);
                else
                    mOnGetAlbums.onGetAlbumsError("No Albums for this artist");
            }
        };
        ServiceManager.searchAlbum(spotifyID, clientCallbak);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mOnGetAlbums = (OnGetAlbums) activity;
        }catch (ClassCastException e){
            Log.e(TAG, "The hosting activity must implement OnGetAlbums");
        }
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public interface OnGetAlbums{
        public void onGetAlbumsSuccesfull(Album[] albums);
        public void onGetAlbumsError(String message);
    }

    private OnGetAlbums mOnGetAlbums;
}

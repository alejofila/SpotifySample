package com.example.alejofila.spotifysample.fragment;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejofila.spotifysample.R;
import com.example.alejofila.spotifysample.model.Album;
import com.example.alejofila.spotifysample.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.PushbackInputStream;

/**
 * Created by Taidy on 18/09/2015.
 */
public class AlbumDetailFragment extends Fragment implements View.OnClickListener{

    /**
     * UI VARIABLES
     */
    private TextView albumTitle;
    private ImageView imageAlbum;
    private Button btnGo;

    /**
     * NON UI VARIABLES
     */
    private Album album;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album_detail,container,false);

        albumTitle = (TextView) rootView.findViewById(R.id.album_title);
        imageAlbum =(ImageView) rootView.findViewById(R.id.image_album_detail);
        btnGo = (Button) rootView.findViewById(R.id.btn_go_spotify);
        btnGo.setOnClickListener(this);

        loadUI();
        return  rootView;
    }

    private void loadUI() {
        albumTitle.setText(album.getName());
        Picasso.with(getContext()).load(album.getImages().get(0).getUrl())
                .placeholder(R.drawable.big_album_place_holder)
                .fit()
                .transform(new CircleTransform())
                .into(imageAlbum);

    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go_spotify:
                openBrowser(album.getExternalUrl().getUrl());
                break;
        }
    }

    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    public  void openBrowser(String url) {

        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        getContext().startActivity(Intent.createChooser(intent, "Chose browser"));
    }
}

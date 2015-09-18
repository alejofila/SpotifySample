package com.example.alejofila.spotifysample;


import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.alejofila.spotifysample.fragment.AlbumDetailFragment;
import com.example.alejofila.spotifysample.fragment.AlbumsFragment;
import com.example.alejofila.spotifysample.fragment.ArtistFragment;
import com.example.alejofila.spotifysample.fragment.SearchFragment;
import com.example.alejofila.spotifysample.model.Album;
import com.example.alejofila.spotifysample.model.Artist;


public class MainActivity extends AppCompatActivity implements SearchFragment.OnSearchPerformed,
        ArtistFragment.OnGetAlbums,
        AlbumsFragment.OnAlbumSelected {
    private static final String TAG = MainActivity.class.getSimpleName();
    Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            loadSearchFragment();
        ActionBar acbar = getSupportActionBar();
        acbar.setHomeButtonEnabled(true);
        acbar.setDefaultDisplayHomeAsUpEnabled(true);

        mHandler = new Handler();

    }

    private void loadSearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SearchFragment fragment = new SearchFragment();
        fm.beginTransaction().add(R.id.fragment_container, fragment, SearchFragment.TAG).
                commit();
    }


    @Override
    public void onSearchSuccesful(Artist artist) {
        // Bundle b = GeneralUtilities.fromArtistToBundle(artist);
        FragmentManager fm = getSupportFragmentManager();
        ArtistFragment fragment = new ArtistFragment();
        fragment.setArtist(artist);
        //fragment.setArguments(b);
        fm.beginTransaction().replace(R.id.fragment_container, fragment, ArtistFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSearchError(final String error) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onGetAlbumsSuccesfull(Album[] albums) {
        FragmentManager fm = getSupportFragmentManager();
        AlbumsFragment fragment = new AlbumsFragment();
        fragment.setAlbums(albums);
        fm.beginTransaction().replace(R.id.fragment_container, fragment, AlbumsFragment.TAG)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onGetAlbumsError(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onAlbumSelected(Album a) {
        FragmentManager fm = getSupportFragmentManager();
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        fragment.setAlbum(a);
        fm.beginTransaction().replace(R.id.fragment_container, fragment, AlbumsFragment.TAG)
                .addToBackStack(null)
                .commit();

    }
}

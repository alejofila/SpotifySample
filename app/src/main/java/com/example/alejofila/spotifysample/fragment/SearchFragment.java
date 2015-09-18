package com.example.alejofila.spotifysample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import com.example.alejofila.spotifysample.R;
import com.example.alejofila.spotifysample.http.ServiceManager;
import com.example.alejofila.spotifysample.model.Artist;
import com.example.alejofila.spotifysample.model.ArtistDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;

/**
 * Created by alejofila on 15/09/15.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    public static final String TAG = SearchFragment.class.getSimpleName();

    /**
     * Interface to communicate with the hosting activity
     */
    OnSearchPerformed mOnSearchPerformed;
    MenuItem searchMenuItem;
    SearchView searchView;
    ProgressBar pb;
    Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mHandler = new Handler();
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        pb = (ProgressBar) rootView.findViewById(R.id.progress_bar_search);
        setHasOptionsMenu(true);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (menu.size() == 0)
            inflater.inflate(R.menu.menu_main, menu);

        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    searchView.onActionViewCollapsed();
                    searchView.setIconified(true);
                }
            }
        });

        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, "This is the text submited" + query);
        performSearch(query);

        return false;
    }

    private void performSearch(final String artistName) {
        pb.setVisibility(View.VISIBLE);
        Callback clientCallbak = new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "Error");
                mOnSearchPerformed.onSearchError(getString(R.string.error_connection));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.GONE);
                    }
                });


            }

            @Override
            public void onResponse(Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.GONE);
                    }
                });


                String jsonString = response.body().string();
                Gson gson = new GsonBuilder().
                        registerTypeAdapter(Artist[].class, new ArtistDeserializer())
                        .create();
                Artist[] artists = gson.fromJson(jsonString, Artist[].class);
                if(artists.length > 0)
                    mOnSearchPerformed.onSearchSuccesful(artists[0]);
                else
                    mOnSearchPerformed.onSearchError("No artist with that name");


            }
        };
        ServiceManager.searchArtist(artistName, clientCallbak);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnSearchPerformed = (OnSearchPerformed) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, "The hosting activity must implement mOnSearchPerfmored");
        }

    }

    public interface OnSearchPerformed {
        public void onSearchSuccesful(Artist artist);
        public void onSearchError(String message);
    }
}


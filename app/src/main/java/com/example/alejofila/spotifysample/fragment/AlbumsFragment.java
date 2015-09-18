package com.example.alejofila.spotifysample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejofila.spotifysample.R;
import com.example.alejofila.spotifysample.adapter.AlbumAdapter;
import com.example.alejofila.spotifysample.model.Album;

/**
 * Created by alejofila on 16/09/15.
 */
public class AlbumsFragment extends Fragment {

    /**
     * Constants
     */
    public static final String TAG  = AlbumsFragment.class.getSimpleName();

    /**
     * NON UI Variables
     */
    private Album[]albums;

    /**
     * UI VARIABLES
     */

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AlbumAdapter.OnAlbumClicked mOnAlbumClicked = new AlbumAdapter.OnAlbumClicked() {
        @Override
        public void onAlbumClicked(Album album) {
            Log.i(TAG, "On album clicked called");
            mOnAlbumSelected.onAlbumSelected(album);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mOnAlbumSelected = (OnAlbumSelected) activity;
        }
        catch(ClassCastException e){
            e.printStackTrace();
        }
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_albums,container,false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_albums);
        mLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new AlbumAdapter(albums,getContext(), mOnAlbumClicked);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;

    }

    public Album[] getAlbums() {
        return albums;
    }

    public void setAlbums(Album[] albums) {
        this.albums = albums;
        Bundle b = new Bundle();
    }
    public interface OnAlbumSelected {
        public void onAlbumSelected(Album a);
    }
    OnAlbumSelected mOnAlbumSelected;
}

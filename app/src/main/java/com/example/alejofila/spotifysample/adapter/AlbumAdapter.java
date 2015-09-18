package com.example.alejofila.spotifysample.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alejofila.spotifysample.R;
import com.example.alejofila.spotifysample.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alejofila on 16/09/15.
 *
 * This is the adapter to populate the list of Albums
 */
public class AlbumAdapter extends RecyclerView.Adapter {
    private static final String TAG = AlbumAdapter.class.getSimpleName();
    private Context mContext;
    private Album[] albums;
    public AlbumAdapter(Album[] albums, Context mContext, OnAlbumClicked mOnAlbumClicked){
        this.albums = albums;
        this.mContext = mContext;
        this.mOnAlbumClicked = mOnAlbumClicked;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new AlbumViewHolder(item);
    }


    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Album  album = albums[position];
        AlbumViewHolder holder2  = (AlbumViewHolder) holder;
        holder2.vCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "On click detected inside adapter");
                mOnAlbumClicked.onAlbumClicked(albums[position]);
            }
        });
        holder2.vName.setText(album.getName());
        Picasso.with(mContext).load(album.getImages().get(0).getUrl())
                .placeholder(R.drawable.ic_album_place_holder)
                .into(holder2.vImage);
        if(album.getAvailableCountry().size()<5) {
            holder2.vMarkets.setText(album.showAvaibleCountrys());
            holder2.vMarkets.setVisibility(View.VISIBLE);
        }

    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return albums.length;
    }

    /**
     * AlbumViewHolder required for appliy recycler pattern
     */
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected ImageView vImage;
        protected CardView vCard;
        protected TextView vMarkets;


        public AlbumViewHolder(View v) {
            super(v);
            vCard = (CardView) v.findViewById(R.id.card_view);
            vName =  (TextView) v.findViewById(R.id.txt_album_name);
            vImage =(ImageView) v.findViewById(R.id.image_album);
            vMarkets = (TextView) v.findViewById(R.id.txt_markets);


        }
    }

    public interface OnAlbumClicked{
        public void onAlbumClicked(Album album);
    }
    private OnAlbumClicked mOnAlbumClicked;
}

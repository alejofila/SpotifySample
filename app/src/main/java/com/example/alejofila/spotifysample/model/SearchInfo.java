package com.example.alejofila.spotifysample.model;

/**
 * Created by alejofila on 16/09/15.
 */
import com.google.gson.annotations.*;

import java.util.ArrayList;

public class SearchInfo {

    @SerializedName(value = "href")
    private String searchUrl;
    @SerializedName(value = "type")
    private String tipoBusqueda;

    private int limit;
    @SerializedName(value = "next")
    private String nextUrl;

    private int total;
    @SerializedName(value = "items")
    private ArrayList<Artist> artistas;

    public ArrayList<Artist> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artist> artistas) {
        this.artistas = artistas;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return getSearchUrl()+ " "+ getTotal()+" " +getLimit()+" "+getNextUrl()+" "+getTipoBusqueda();
    }
}

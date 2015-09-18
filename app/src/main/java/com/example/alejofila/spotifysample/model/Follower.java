package com.example.alejofila.spotifysample.model;

/**
 * Created by alejofila on 16/09/15.
 */
public class Follower {
    private String href;
    private int total;

    public Follower(int total){
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Total of followers "+total;
    }
}

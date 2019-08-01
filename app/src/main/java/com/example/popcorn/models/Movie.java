package com.example.popcorn.models;


import org.parceler.Parcel;

@Parcel
public class Movie {
     String vote_average;
     String title;
     String poster_path;
     String backdrop_path;
     String overview;
     String release_date;

     public Movie(){}

    public Movie(String vote_average, String title, String poster_path, String backdrop_path, String overview, String release_date) {
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }
}

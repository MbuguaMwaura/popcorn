package com.example.popcorn;


public class Constants {

    public static final String API = BuildConfig.MOVIE_API;
    public static final String API_QUERY_PARAMETER = "api_key";
    public static final String API_DISCOVER_URL = "https://api.themoviedb.org/3/discover/movie";
    public static final String API_SEARCH_URL = "https://api.themoviedb.org/3/search/movie";
    public static final String API_SEARCH_QUERY = "query";
    public static final String API_START_DATEL = "primary_release_date.gte";
    public static final String API_END_DATEL = "primary_release_date.lte";
    public static final String API_SORT_BY = "popularity.desc";
    public static final String API_RATING = "certification.lte";
    public static final String API_COUNTRY = "certification_country";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String FIREBASE_CHILD_MOVIES = "movies";


}

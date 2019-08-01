package com.example.popcorn.services;

import com.example.popcorn.Constants;
import com.example.popcorn.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {

    public static void findPopular(Callback callback){

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder builder = HttpUrl.parse(Constants.API_DISCOVER_URL).newBuilder();
        builder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.API);
        builder.addQueryParameter("sort_by",Constants.API_SORT_BY);
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }


    public static void findKids(Callback callback){

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder builder = HttpUrl.parse(Constants.API_DISCOVER_URL).newBuilder();
        builder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.API);
        builder.addQueryParameter("sort_by",Constants.API_SORT_BY);
        builder.addQueryParameter(Constants.API_RATING, "G");
        builder.addQueryParameter(Constants.API_COUNTRY,"US");
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }


    public static void findTheatre(Callback callback){

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder builder = HttpUrl.parse(Constants.API_DISCOVER_URL).newBuilder();
        builder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.API);
        builder.addQueryParameter(Constants.API_START_DATEL,"2019-01-01");
        builder.addQueryParameter(Constants.API_END_DATEL,"2019-06-22");

        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void searchMovie(String search, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder builder = HttpUrl.parse(Constants.API_SEARCH_URL).newBuilder();
        builder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.API);
        builder.addQueryParameter(Constants.API_SEARCH_QUERY, search);
        String url = builder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Movie> processResults(Response response){
        ArrayList<Movie> movies = new ArrayList<>();

        try{

            String jsonData = response.body().string();
            JSONObject movieJSON = new JSONObject(jsonData);
            JSONArray resultsArrayJSON = movieJSON.getJSONArray("results");
            if (response.isSuccessful()){
                for (int i = 0;i < resultsArrayJSON.length();i++){
                    JSONObject resultJSON = resultsArrayJSON.getJSONObject(i);
                    String vote_average = resultJSON.getString("vote_average");
                    String title =resultJSON.getString("title");
                    String poster_path = Constants.IMAGE_URL+resultJSON.getString("poster_path");
                    String backdrop_path = Constants.IMAGE_URL+resultJSON.getString("backdrop_path");
                    String overview = resultJSON.getString("overview");
                    String release_date = resultJSON.getString("release_date");

                    Movie movie  = new Movie(vote_average,title,poster_path,backdrop_path,overview,release_date);
                    movies.add(movie);

                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

}

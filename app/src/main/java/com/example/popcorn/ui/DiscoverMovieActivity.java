package com.example.popcorn.ui;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.popcorn.R;
import com.example.popcorn.adapters.MovieAdapter;
import com.example.popcorn.models.Movie;
import com.example.popcorn.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DiscoverMovieActivity extends AppCompatActivity {
    public ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView popularMoviesView;
    @BindView(R.id.recyclerView2) RecyclerView theatreMovieView;
    @BindView(R.id.recyclerView3) RecyclerView kidsMovieView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_movie);
        ButterKnife.bind(this);

        Handler handler = new Handler();

                getPopularMovies();


        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getTheatreMovies();
            }

        }, 1000);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getKidsMovies();
            }

        }, 2000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        ButterKnife.bind(this);


        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView  = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                getMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }




    private void getMovies(String search){
        final MovieService movieService = new MovieService();
        movieService.searchMovie(search, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                movies = movieService.processResults(response);

                DiscoverMovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieAdapter = new MovieAdapter(movies, getApplicationContext());
                        popularMoviesView.setAdapter(movieAdapter);
                        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(DiscoverMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        popularMoviesView.setLayoutManager(horizontalLayoutManagaer);
                        popularMoviesView.setHasFixedSize(true);

                    }
                });
            }
        });
    }



    private void getPopularMovies(){
        final MovieService movieService = new MovieService();

        movieService.findPopular(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                movies = movieService.processResults(response);

                DiscoverMovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieAdapter = new MovieAdapter(movies, getApplicationContext());
                        popularMoviesView.setAdapter(movieAdapter);
                        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(DiscoverMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        popularMoviesView.setLayoutManager(horizontalLayoutManagaer);
                        popularMoviesView.setHasFixedSize(true);

                    }
                });
            }
        });
    }

    private void getTheatreMovies(){
        final MovieService movieService = new MovieService();

        movieService.findTheatre(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                movies = movieService.processResults(response);

                DiscoverMovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieAdapter = new MovieAdapter(movies, getApplicationContext());
                        theatreMovieView.setAdapter(movieAdapter);
                        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(DiscoverMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        theatreMovieView.setLayoutManager(horizontalLayoutManagaer);
                        theatreMovieView.setHasFixedSize(true);

                    }
                });
            }
        });
    }

    private void getKidsMovies(){
        final MovieService movieService = new MovieService();

        movieService.findKids(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                movies = movieService.processResults(response);

                DiscoverMovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieAdapter = new MovieAdapter(movies, getApplicationContext());
                        kidsMovieView.setAdapter(movieAdapter);
                        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(DiscoverMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        kidsMovieView.setLayoutManager(horizontalLayoutManagaer);
                        kidsMovieView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}

package com.example.popcorn.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.popcorn.R;
import com.example.popcorn.adapters.MovieFragmentAdapter;
import com.example.popcorn.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetails extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private MovieFragmentAdapter movieFragmentAdapter;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        movies = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        int startingPosition = getIntent().getIntExtra("position",0);

        movieFragmentAdapter = new MovieFragmentAdapter(getSupportFragmentManager(), movies);

        mViewPager.setAdapter(movieFragmentAdapter);
        mViewPager.setCurrentItem(startingPosition);

    }
}

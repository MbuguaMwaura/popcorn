package com.example.popcorn.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.popcorn.models.Movie;
import com.example.popcorn.ui.MovieDetails;
import com.example.popcorn.ui.MovieDetailsFragment;

import java.util.ArrayList;

public class MovieFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Movie> movies;

    public MovieFragmentAdapter(FragmentManager fm, ArrayList<Movie> movies){
        super(fm);
        this.movies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailsFragment.newInstance(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return movies.get(position).getTitle();
    }
}

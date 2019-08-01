package com.example.popcorn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popcorn.R;
import com.example.popcorn.models.Movie;
import com.example.popcorn.ui.MovieDetails;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies;
    private Context context;

    public MovieAdapter(ArrayList<Movie> movies, Context context){
        this.context = context;
        this.movies = movies;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {
            holder.bindMovie(movies.get(position));
    }


    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_movie_item,parent,false);
        MovieAdapter.MovieViewHolder movieViewHolder = new MovieAdapter.MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movieImage) ImageView movieImage;
        @BindView(R.id.movieTitle) TextView movieTitle;
        @BindView(R.id.movieDate) TextView movieDate;
        @BindView(R.id.movieRating) TextView movieRating;

        private Context context;

        public MovieViewHolder( View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(context, MovieDetails.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movie", Parcels.wrap(movies));
            context.startActivity(intent);
        }

        public void bindMovie(Movie movie){

            movieTitle.setText(movie.getTitle());
            movieDate.setText(movie.getRelease_date());
            Picasso.get().load(movie.getPoster_path()).resize(180,250).into(movieImage);
          movieRating.setText(movie.getVote_average());




        }
    }

}

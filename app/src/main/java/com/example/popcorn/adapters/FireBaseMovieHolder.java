package com.example.popcorn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popcorn.Constants;
import com.example.popcorn.R;
import com.example.popcorn.models.Movie;
import com.example.popcorn.ui.MovieDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FireBaseMovieHolder extends   RecyclerView.ViewHolder implements View.OnClickListener{

    View view;
    @BindView(R.id.savedMovieImage)
    ImageView savedMovieImage;
    @BindView(R.id.savedMovieTitle)
    TextView savedMovieTitle;
    @BindView(R.id.savedMovieRating) TextView savedMovieRating;
    @BindView(R.id.savedMovieDate) TextView savedMovieDate;
    Context context;

    public FireBaseMovieHolder(View itemView){
            super(itemView);
            view = itemView;
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

    public void bindMovie(Movie movie){
        ButterKnife.bind(this,view);

        savedMovieTitle.setText(movie.getTitle());
        savedMovieDate.setText(movie.getRelease_date());
        Picasso.get().load(movie.getPoster_path()).resize(200,400).into(savedMovieImage);
        savedMovieRating.setText(movie.getVote_average());
    }

    @Override
    public void onClick(View v){
        final ArrayList<Movie> movies = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    movies.add(dataSnapshot1.getValue(Movie.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("position",itemPosition+"");
                intent.putExtra("movie", Parcels.wrap(movies));

                context.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

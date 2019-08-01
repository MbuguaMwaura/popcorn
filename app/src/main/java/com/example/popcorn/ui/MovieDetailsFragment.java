package com.example.popcorn.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popcorn.Constants;
import com.example.popcorn.R;
import com.example.popcorn.models.Movie;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.posterBackDrop)
    ImageView posterBackDrop;
    @BindView(R.id.posterImage) ImageView posterImage;
    @BindView(R.id.movieDetailsTitle)
    TextView title;
    @BindView(R.id.movieOverView) TextView overview;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.watchlistBtn) ImageView watchListBtn;

    private Movie movie;


    public static MovieDetailsFragment newInstance(Movie movie) {
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        args.putParcelable("movies", Parcels.wrap(movie));
       movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = Parcels.unwrap(getArguments().getParcelable("movie"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details,container,false);
        ButterKnife.bind(this,view);

        title.setText(movie.getTitle());
        rating.setText(movie.getVote_average());
        overview.setText(movie.getOverview());
        Picasso.get().load(movie.getPoster_path()).resize(200, 300).centerCrop().into(posterImage);
        Picasso.get().load(movie.getBackdrop_path()).resize(500, 200).centerCrop().into(posterBackDrop);


        watchListBtn.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v){
        if (v == watchListBtn){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIES);
            reference.push().setValue(movie);
            Toast.makeText(getContext(), "saved to your watchlist", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(),SavedMovie.class);
            startActivity(intent);
        }
    }

}

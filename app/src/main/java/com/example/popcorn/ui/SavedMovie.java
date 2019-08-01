package com.example.popcorn.ui;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popcorn.Constants;
import com.example.popcorn.R;
import com.example.popcorn.adapters.FireBaseMovieHolder;
import com.example.popcorn.adapters.MovieFragmentAdapter;
import com.example.popcorn.models.Movie;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMovie extends AppCompatActivity {
    private DatabaseReference reference;
    private FirebaseRecyclerAdapter<Movie, FireBaseMovieHolder> adapter;
    @BindView(R.id.recyclerViewSaved)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movie);
        ButterKnife.bind(this);

        reference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_MOVIES);
        setUpFirebaseAdapter();



    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Movie> options = new FirebaseRecyclerOptions.Builder<Movie>()
                .setQuery(reference,Movie.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Movie, FireBaseMovieHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FireBaseMovieHolder holder, int position, @NonNull Movie model) {
                holder.bindMovie(model);
            }

            @NonNull
            @Override
            public FireBaseMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.saved_movie_item,viewGroup,false);
                return new FireBaseMovieHolder(view);
            }
        };
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null){
            adapter.stopListening();
        }
    }
}

package com.example.segundoauqui.firebase.view.mainactivity;

import android.util.Log;

import com.example.segundoauqui.firebase.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by segundoauqui on 8/23/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    MainActivityContract.View view;
    public static final String TAG = "Presenter";
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference movieReference;




    @Override
    public void attachView(MainActivityContract.View View) {
        this.view = View;
        database = FirebaseDatabase.getInstance();



    }

    @Override
    public void deattachView() {
        this.view = null;

    }

    // TODO: 8/23/17 implement firebase logic to save data

    @Override
    public void saveDataToCloud(String s) {

        // Write a message to the database
        myRef = database.getReference("message");

        myRef.getDatabase();

        myRef.setValue(s);


        view.onDataSaved(true);

    }

    @Override
    public void readFromCloud() {

        // Read from the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                view.dataRecieved(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void pushMovieToDb(Movie movie) {
        DatabaseReference movieReference = database.getReference("movie");
        movieReference.push().setValue(movie);

        movieReference.child(movie.getName()).setValue(movie);

        for (int i = 0; i < 5; i ++){
            movieReference.child("Movie" + i).setValue(movie);
        }

    }


    @Override
    public void getMovies() {
        Log.d(TAG, "getMovies: ");

        final List<Movie> movieList = new ArrayList<>();
        movieReference = database.getReference("movie");


        movieReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Movie movie =  snapshot.getValue(Movie.class);
                    movieList.add(movie);


                }
                view.updateMovieList(movieList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

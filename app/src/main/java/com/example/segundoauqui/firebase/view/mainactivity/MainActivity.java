package com.example.segundoauqui.firebase.view.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.segundoauqui.firebase.R;
import com.example.segundoauqui.firebase.injection.mainactivity.DaggerMainActivityComponent;
import com.example.segundoauqui.firebase.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{
    private static final String TAG = " MainActivity";
    @Inject
    MainActivityPresenter presenter;
    Button btnReadData;
    TextView tvTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadData = (Button) findViewById(R.id.btnReadData);
        tvTextView = (TextView) findViewById(R.id.tvTextView);


        setUpDaggerComponent();
        DaggerMainActivityComponent.create().inject(this);
        presenter.attachView(this);
        presenter.saveDataToCloud("data");
        presenter.readFromCloud();
    }

    public void setUpDaggerComponent(){
        DaggerMainActivityComponent.create().inject(this);
    }

    @Override
    public void showerror(String s) {

    }

    @Override
    public void onDataSaved(boolean isSaved) {

        Log.d(TAG, "onDataSaved: " + isSaved);

    }

    @Override
    public void dataRecieved(String data) {
        tvTextView.setText(data);

    }

    @Override
    public void updateMovieList(List<Movie> movieList) {
        Log.d(TAG, "updateMovieList: " + movieList.size());
        for(Movie movie: movieList){
            Log.d(TAG, "updateMovieList: " + movie.toString());}
    }


    public void ReadData(View view) {

        presenter.getMovies();
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
   }

    public void updateFirebaseDb(View view) {

        Movie movie = new Movie("Avngers", "sdfsdf", 2015);
        presenter.pushMovieToDb(movie);
    }
}

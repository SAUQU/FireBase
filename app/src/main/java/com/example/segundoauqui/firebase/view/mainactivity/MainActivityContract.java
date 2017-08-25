package com.example.segundoauqui.firebase.view.mainactivity;

import com.example.segundoauqui.firebase.BasePresenter;
import com.example.segundoauqui.firebase.BaseView;
import com.example.segundoauqui.firebase.model.Movie;

import java.util.List;

/**
 * Created by segundoauqui on 8/23/17.
 */

public interface MainActivityContract {


    interface View extends BaseView{
        void onDataSaved(boolean isSaved);
        void dataRecieved(String data);

        void updateMovieList(List<Movie> movieList);
    }

    interface Presenter extends BasePresenter<View>{
        void saveDataToCloud(String s);
        void readFromCloud();
        void pushMovieToDb(Movie movie);
        void getMovies();

    }
}


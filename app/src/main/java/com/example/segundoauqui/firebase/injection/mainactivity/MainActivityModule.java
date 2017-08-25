package com.example.segundoauqui.firebase.injection.mainactivity;

import com.example.segundoauqui.firebase.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by segundoauqui on 8/23/17.
 */

@Module
public class MainActivityModule {



    @Provides
    MainActivityPresenter providesMainActivityPresenter(){
        return new MainActivityPresenter();
    }
}

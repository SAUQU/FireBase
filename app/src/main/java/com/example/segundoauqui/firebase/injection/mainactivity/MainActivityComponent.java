package com.example.segundoauqui.firebase.injection.mainactivity;

import com.example.segundoauqui.firebase.view.mainactivity.MainActivity;

import dagger.Component;

/**
 * Created by segundoauqui on 8/23/17.
 */


@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

   void inject(MainActivity mainActivity);

}

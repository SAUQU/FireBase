package com.example.segundoauqui.firebase;

/**
 * Created by segundoauqui on 8/23/17.
 */

public interface BasePresenter <V extends BaseView> {

    void attachView(V view);

    void deattachView();




}

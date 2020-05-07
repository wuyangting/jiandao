package com.example.myapplication.base;

import retrofit2.http.PUT;

public class BasePre<V extends BaseView> {
    public V view;
    //M  V  P  (V  P M)
//拿到V层对象
    public void bindView(V view){
        this.view = view;
    }

}

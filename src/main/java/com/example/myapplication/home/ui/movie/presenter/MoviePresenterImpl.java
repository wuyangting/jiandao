package com.example.myapplication.home.ui.movie.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.movie.bean.MovieBean;
import com.example.myapplication.home.ui.movie.contract.MovieContract;
import com.example.myapplication.home.ui.movie.model.MovieModel;
import com.example.myapplication.net.INetCallback;

public class MoviePresenterImpl extends BasePre<MovieContract.MovieView> implements MovieContract.MoviePre {

    private final MovieModel movieModel;

    public MoviePresenterImpl() {
        movieModel = new MovieModel();
    }

    @Override
    public void getData() {
movieModel.getData(new INetCallback<MovieBean>() {
    @Override
    public void onSuccess(MovieBean movieBean) {
        view.setData(movieBean);
    }

    @Override
    public void onError(Throwable throwable) {

    }
});
    }

    @Override
    public void callMoviedData(String string) {

    }
}

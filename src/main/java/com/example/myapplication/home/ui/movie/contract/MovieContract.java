package com.example.myapplication.home.ui.movie.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.movie.bean.MovieBean;
import com.example.myapplication.net.INetCallback;

public class MovieContract {
    public interface MovieView extends BaseView {
        void setData(MovieBean movieBean);
    }

    public interface MovieModel{
        <T> void getData(INetCallback<T> netCallback);
    }
    public interface MoviePre {
        void getData();
        void callMoviedData(String string);
    }
}

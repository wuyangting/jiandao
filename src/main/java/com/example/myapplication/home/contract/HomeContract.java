package com.example.myapplication.home.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.net.INetCallback;

public class HomeContract {
    public interface MainView extends BaseView {
        void setData();
    }

    public interface MainModel{
       <T> void getData(INetCallback<T> netCallback);
    }
    public interface MainPre {
        void getData();
        void callHomeData(String string);
    }
}

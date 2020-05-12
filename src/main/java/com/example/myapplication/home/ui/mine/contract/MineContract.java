package com.example.myapplication.home.ui.mine.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.net.INetCallback;

public class MineContract {
    public interface MineView extends BaseView {
        void setData();

        void isQianDao();
    }

    public interface MineModel{
        <T> void getData(INetCallback<T> netCallback);

        <T> void qianDao(String token,INetCallback<T> netCallback);
    }
    public interface MinePre {
        void getData();
        void qianDao(String token);
        void callMineData(String string);
    }
}

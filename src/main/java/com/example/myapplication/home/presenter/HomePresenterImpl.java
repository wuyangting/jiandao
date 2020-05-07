package com.example.myapplication.home.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.bean.User;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.model.HomeModelImpl;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.utils.LogUtils;

public class HomePresenterImpl extends BasePre<HomeContract.MainView> implements HomeContract.MainPre {

    private final HomeModelImpl homeModel;

    public HomePresenterImpl() {
        homeModel = new HomeModelImpl(this);
    }


    @Override
    public void getData() {

    }

    @Override
    public void callHomeData(String string) {
        view.setData();
    }
}

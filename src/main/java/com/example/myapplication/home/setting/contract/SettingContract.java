package com.example.myapplication.home.setting.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.search.bean.SearchResultBean;
import com.example.myapplication.net.INetCallback;

public class SettingContract {
    public interface SettingView extends BaseView {

    }

    public interface SettingModel{
        <T> void getData(INetCallback<T> netCallback);
        <T> void getData(String version, INetCallback<T> netCallback);
    }
    public interface SettingPre {
        void getData(String version);
        void callSettingData(String string);
    }
}

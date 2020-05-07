package com.example.myapplication.home.ui.special.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.example.myapplication.net.INetCallback;

public class SpecialContract {
    public interface SpecialView extends BaseView {
        void setData(SpecialBean specialBean);
    }

    public interface SpecialModel{
        <T> void getData(INetCallback<T> netCallback);
    }
    public interface SpecialPre {
        void getData();
        void callSpecialData(String string);
    }
}

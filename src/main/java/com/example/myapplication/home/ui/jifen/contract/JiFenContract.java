package com.example.myapplication.home.ui.jifen.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.jifen.bean.JiFenBean;
import com.example.myapplication.net.INetCallback;

public class JiFenContract {
    public interface JiFenView extends BaseView {

        void setJiFenInfo(JiFenBean jiFenInfo);
    }

    public interface JiFenMode{
        <T> void getJiFenInfo(String token,INetCallback<T> iNetCallBack);

    }
    public interface JiFenPresenter{
        void getVerifiedJiFenInfo(String token);
    }
}

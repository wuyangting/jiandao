package com.example.myapplication.home.ui.recommend.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.net.INetCallback;

public class RecommendContract {
    public interface RecommendView extends BaseView {
        void setData(TabBean tabBean);
    }

    public interface RecommendModel{
       <T> void getData(INetCallback<T> netCallback);

    }
    public interface RecommendPre {
        void getData();
        void callRecommendData(String string);
    }
}

package com.example.myapplication.home.ui.recommend.fragment.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.net.INetCallback;

public class RecommendAndContract {
    public interface RecommendAndView extends BaseView {
        void setData(RecommendRecBean tabBean);
    }

    public interface RecommendAndModel{
       <T> void getData(INetCallback<T> netCallback);
        <T> void getData(String id,INetCallback<T> netCallback);
    }
    public interface RecommendAndPre {
        void getData();
        void callRecommendAndData(String string);
    }
}

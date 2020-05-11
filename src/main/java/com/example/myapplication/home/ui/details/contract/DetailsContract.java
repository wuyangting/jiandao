package com.example.myapplication.home.ui.details.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.net.INetCallback;

public class DetailsContract {
    public interface DetailsView extends BaseView {
        void setData(TabBean tabBean);
    }

    public interface DetailsModel{
       <T> void getData(INetCallback<T> netCallback);

    }
    public interface DetailsPre {
        void getData();
    }
}

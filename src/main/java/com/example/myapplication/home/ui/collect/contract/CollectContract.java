package com.example.myapplication.home.ui.collect.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.net.INetCallback;

public class CollectContract {
    public interface CollectView extends BaseView {
        //获取收藏的所有数据
        void setData(CollectArticlDataBean data);
    }

    public interface CollectModel{
        //获取收藏的所有数据
        <T> void getData(int type,String start,String point_time,String token,INetCallback<T> netCallback);
    }
    public interface CollectPre {
        //获取收藏的所有数据
       void getData(int type,String start,String point_time,String token);


    }
}

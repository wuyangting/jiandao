package com.example.myapplication.home.ui.details.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.details.InfoBean;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.net.INetCallback;

public class DetailsContract {
    public interface DetailsView extends BaseView {
        void setData(TabBean tabBean);

        //设置用户收藏，点赞数据
        void setInfo(InfoBean info);
        //通知V层是否收藏
        void setCollect();
        //通知V层是否点赞
        void setLike();

        void setAddIntegral();
    }

    public interface DetailsModel{
        //获取文章的推荐文章列表
       <T> void getData(String id,INetCallback<T> netCallback);
        //用户是否收藏文章
        <T>   void collect(String id,int type,String token,INetCallback<T> netCallback);
        //用户是否点赞
        <T>   void like(String id,int type,String token,INetCallback<T> netCallback);
        //获取用户点赞收藏文章的信息
        <T>  void getInfo(String id,String token,INetCallback<T> netCallback);


        //阅读文章增加积分
        <T>  void addIntegral(String id,String token,INetCallback<T> netCallback);
    }
    public interface DetailsPre {
        //获取文章的推荐文章列表
        void getData(String id);
        //用户是否收藏文章
        void collect(String id,int type,String token);
        //用户是否点赞
        void like(String id,int type,String token);
        //获取用户点赞收藏文章的信息
        void getInfo(String id,String token);
        //阅读文章增加积分
         void AddIntegral(String id,String token);

    }
}

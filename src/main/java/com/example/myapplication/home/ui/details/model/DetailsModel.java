package com.example.myapplication.home.ui.details.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.details.contract.DetailsContract;
import com.example.myapplication.home.ui.recommend.contract.RecommendContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.URLConstants;

import java.util.HashMap;

public class DetailsModel extends BaseModel implements DetailsContract.DetailsModel {

    @Override
    public <T> void getData(String id,INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.RECOMMEND_ARTICAL,commonParams,netCallback);
    }


    //用户是否收藏文章
    @Override
    public <T> void collect(String id, int type, String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("type",type+"");
        commonParams.put("token",token);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.COLLECT_ARTICL,commonParams,netCallback);
    }

    //用户是否点赞
    @Override
    public <T> void like(String id, int type, String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("type",type+"");
        commonParams.put("token",token);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.LIKE_ARTICL,commonParams,netCallback);
    }


    //获取用户点赞收藏文章的信息
    @Override
    public <T> void getInfo(String id, String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("token",token);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.GETARTICL_INFO,commonParams,netCallback);
    }

    @Override
    public <T> void addIntegral(String id, String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("token",token);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.ADDINTEGRAL,commonParams,netCallback);
    }
}
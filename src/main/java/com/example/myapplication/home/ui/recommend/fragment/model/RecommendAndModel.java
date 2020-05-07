package com.example.myapplication.home.ui.recommend.fragment.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.recommend.contract.RecommendContract;
import com.example.myapplication.home.ui.recommend.fragment.contract.RecommendAndContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;

import java.util.HashMap;

public class RecommendAndModel extends BaseModel implements RecommendAndContract.RecommendAndModel {
    @Override
    public <T> void getData(INetCallback<T> netCallback) {

    }

    @Override
    public <T> void getData(String id, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        commonParams.put("start","0");
        commonParams.put("number","0");
        commonParams.put("point_time ","0");
        commonParams.put("id",id);
        NetWorkFactory.getInstance().getNetWork().get("/app/v_1_3/article/recommendlist",commonParams,netCallback);
    }

}

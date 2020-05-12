package com.example.myapplication.home.ui.collect.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.URLConstants;

import java.util.HashMap;


public class CollectModel extends BaseModel implements CollectContract.CollectModel {

    @Override
    public <T> void getData(int type, String start, String point_time, String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("type",type+"");
        commonParams.put("start",start+"");
        commonParams.put("point_time",point_time+"");
        commonParams.put("token",token);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.COLLECT_DATA,commonParams,netCallback);
    }
}

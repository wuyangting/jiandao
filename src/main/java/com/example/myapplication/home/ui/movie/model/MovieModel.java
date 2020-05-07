package com.example.myapplication.home.ui.movie.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.movie.contract.MovieContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;

import java.util.HashMap;

public class MovieModel extends BaseModel implements MovieContract.MovieModel {
    @Override
    public <T> void getData(INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        commonParams.put("start","0");
        commonParams.put("number","0");
        commonParams.put("point_time ","0");
        NetWorkFactory.getInstance().getNetWork().get("/app/v_1_3/article/videolist",commonParams,netCallback);
    }
}

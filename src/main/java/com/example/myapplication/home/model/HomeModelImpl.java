package com.example.myapplication.home.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.presenter.HomePresenterImpl;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.URLConstants;

import java.util.HashMap;

public class HomeModelImpl extends BaseModel implements HomeContract.MainModel {
    private HomePresenterImpl presenter;

    public HomeModelImpl(HomePresenterImpl presenter) {
        this.presenter = presenter;
    }


    @Override
    public <T> void getData(INetCallback<T> netCallback) {
        //        测试添加参数
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
//        commonParams.put("start","0");
//        commonParams.put("number","0");
//        commonParams.put("point_time ","0");

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

//        NetWorkFactory.getInstance().getNetWork().get(URLConstants.VEDIO_LIST,commonParams ,netCallback);
        NetWorkFactory.getInstance().getNetWork().get("/api/column/columnlist",commonParams,netCallback);
    }
}

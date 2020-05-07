package com.example.myapplication.home.setting.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.setting.contract.SettingContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;

import java.util.HashMap;

public class SettingModel extends BaseModel implements SettingContract.SettingModel {

    @Override
    public <T> void getData(INetCallback<T> netCallback) {

    }

    @Override
    public <T> void getData(String version, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("version",version);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get("/api/user/checkupdate",commonParams,netCallback);
    }

}

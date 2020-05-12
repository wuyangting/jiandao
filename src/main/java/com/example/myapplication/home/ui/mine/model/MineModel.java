package com.example.myapplication.home.ui.mine.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.ui.mine.contract.MineContract;
import com.example.myapplication.home.ui.special.contract.SpecialContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.URLConstants;

import java.util.HashMap;

public class MineModel extends BaseModel implements MineContract.MineModel {
    @Override
    public <T> void getData(INetCallback<T> netCallback) {

    }

    @Override
    public <T> void qianDao(String token, INetCallback<T> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        commonParams.put("token ",token);
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.QIANDAO,commonParams,netCallback);
    }
}

package com.example.myapplication.home.ui.jifen.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.jifen.contract.JiFenContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.URLConstants;

import java.util.HashMap;

public class JiFenModel extends BaseModel implements JiFenContract.JiFenMode {

    @Override
    public <T> void getJiFenInfo(String token, INetCallback<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("token",token);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().get(URLConstants.JIFEN_INFO,commonParams,iNetCallBack);
    }
}

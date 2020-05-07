package com.example.myapplication.home.search.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.search.bean.SearchResultBean;
import com.example.myapplication.home.search.contract.SearchContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;

import java.util.HashMap;

public class SearchModel extends BaseModel implements SearchContract.SearchModel {
    @Override
    public <T> void getData(INetCallback<T> netCallback) {

    }

    @Override
    public <T> void getData(String keyWord, INetCallback<T> netCallback) {
//


//        NetWorkFactory.getInstance().getNetWork().get("/app/v_1_3/article/recommendlist",commonParams,netCallback);
    }

    public void getData(String search_result, int point_time, int start, INetCallback<SearchResultBean> netCallback) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("start",start+"");
        commonParams.put("number","0");
        commonParams.put("point_time ",point_time+"");
        commonParams.put("keywords",search_result);
        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get("/app/v_1_1/article/search",commonParams,netCallback);

    }
}

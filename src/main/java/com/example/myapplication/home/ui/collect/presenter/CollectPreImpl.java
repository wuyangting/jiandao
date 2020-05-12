package com.example.myapplication.home.ui.collect.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.home.ui.collect.model.CollectModel;
import com.example.myapplication.net.INetCallback;

public class CollectPreImpl extends BasePre<CollectContract.CollectView> implements CollectContract.CollectPre {

    private final CollectModel collectModel;

    public CollectPreImpl() {
        collectModel = new CollectModel();
    }

    @Override
    public void getData(int type, String start, String point_time, String token) {
        collectModel.getData(type, start, point_time, token, new INetCallback<CollectArticlDataBean>() {
            @Override
            public void onSuccess(CollectArticlDataBean collectArticlDataBean) {
                view.setData(collectArticlDataBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

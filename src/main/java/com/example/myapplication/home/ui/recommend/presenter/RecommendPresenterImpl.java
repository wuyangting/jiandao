package com.example.myapplication.home.ui.recommend.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.home.ui.recommend.contract.RecommendContract;
import com.example.myapplication.home.ui.recommend.model.RecommendModel;
import com.example.myapplication.net.INetCallback;

public class RecommendPresenterImpl extends BasePre<RecommendContract.RecommendView> implements RecommendContract.RecommendPre {

    private final RecommendModel recommendModel;

    public RecommendPresenterImpl() {
        recommendModel = new RecommendModel();
    }

    @Override
    public void getData() {
        recommendModel.getData(new INetCallback<TabBean>() {
            @Override
            public void onSuccess(TabBean tabBean) {
                view.setData(tabBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void callRecommendData(String string) {

    }
}

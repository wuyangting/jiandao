package com.example.myapplication.home.ui.recommend.fragment.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.recommend.contract.RecommendContract;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.home.ui.recommend.fragment.contract.RecommendAndContract;
import com.example.myapplication.home.ui.recommend.fragment.model.RecommendAndModel;
import com.example.myapplication.net.INetCallback;

public class RecommendPresenterFragment extends BasePre<RecommendAndContract.RecommendAndView> implements RecommendContract.RecommendPre {

    private final RecommendAndModel recommendAndModel;

    @Override
    public void getData() {

    }

    @Override
    public void callRecommendData(String string) {

    }

    public RecommendPresenterFragment() {
        recommendAndModel = new RecommendAndModel();
    }

    public void getData(String id) {
recommendAndModel.getData(id, new INetCallback<RecommendRecBean>() {
    @Override
    public void onSuccess(RecommendRecBean recommendRecBean) {
        view.setData(recommendRecBean);
    }

    @Override
    public void onError(Throwable throwable) {

    }
});
    }
}

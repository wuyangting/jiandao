package com.example.myapplication.home.ui.details.pre;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.details.contract.DetailsContract;
import com.example.myapplication.home.ui.details.model.DetailsModel;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.example.myapplication.home.ui.special.contract.SpecialContract;
import com.example.myapplication.home.ui.special.model.SpecialModel;
import com.example.myapplication.net.INetCallback;

public class DetailsPresenterImpl extends BasePre<DetailsContract.DetailsView> implements DetailsContract.DetailsPre {

    private final DetailsModel detailsModel;

    public DetailsPresenterImpl() {
        detailsModel = new DetailsModel();
    }

    @Override
    public void getData() {
        detailsModel.getData(new INetCallback<SpecialBean>() {
            @Override
            public void onSuccess(SpecialBean specialBean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

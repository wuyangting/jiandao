package com.example.myapplication.home.ui.special.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.example.myapplication.home.ui.special.contract.SpecialContract;
import com.example.myapplication.home.ui.special.model.SpecialModel;
import com.example.myapplication.net.INetCallback;

public class SpecialPresenterImpl extends BasePre<SpecialContract.SpecialView> implements SpecialContract.SpecialPre {

    private final SpecialModel specialModel;

    public SpecialPresenterImpl() {
        specialModel = new SpecialModel();
    }

    @Override
    public void getData() {
        specialModel.getData(new INetCallback<SpecialBean>() {
            @Override
            public void onSuccess(SpecialBean specialBean) {
view.setData(specialBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void callSpecialData(String string) {

    }
}

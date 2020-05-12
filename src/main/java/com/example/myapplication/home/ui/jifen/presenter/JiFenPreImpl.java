package com.example.myapplication.home.ui.jifen.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.jifen.bean.JiFenBean;
import com.example.myapplication.home.ui.jifen.contract.JiFenContract;
import com.example.myapplication.home.ui.jifen.model.JiFenModel;
import com.example.myapplication.net.INetCallback;

public class JiFenPreImpl extends BasePre<JiFenContract.JiFenView> implements JiFenContract.JiFenPresenter {

    private final JiFenModel jiFenModel;

    public JiFenPreImpl() {
        jiFenModel = new JiFenModel();
    }

    @Override
    public void getVerifiedJiFenInfo(String token) {
        jiFenModel.getJiFenInfo(token, new INetCallback<JiFenBean>() {
            @Override
            public void onSuccess(JiFenBean jiFenBean) {
                view.setJiFenInfo(jiFenBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

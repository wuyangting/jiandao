package com.example.myapplication.home.ui.details.pre;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.details.InfoBean;
import com.example.myapplication.home.ui.details.contract.DetailsContract;
import com.example.myapplication.home.ui.details.model.DetailsModel;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
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
    public void getData(String id) {
        detailsModel.getData(id,new INetCallback<SpecialBean>() {
            @Override
            public void onSuccess(SpecialBean specialBean) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

        @Override
        public void collect(String id, int type, String token) {
            detailsModel.collect(id, type, token, new INetCallback<VerfiedBean>() {
                @Override
                public void onSuccess(VerfiedBean affirmRegisterBean) {
                    if(affirmRegisterBean.getCode()==1){
                        view.setCollect();
                    }else {
                        view.showToast(affirmRegisterBean.getMessage());
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }

        @Override
        public void like(String id, int type, String token) {
            detailsModel.like(id, type, token, new INetCallback<VerfiedBean>() {
                @Override
                public void onSuccess(VerfiedBean affirmRegisterBean) {
                    if(affirmRegisterBean.getCode()==1){
                        view.setLike();
                    }else {
                        view.showToast("已点赞");
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }

        @Override
        public void getInfo(String id, String token) {
            detailsModel.getInfo(id, token, new INetCallback<InfoBean>() {
                @Override
                public void onSuccess(InfoBean infoBean) {
                    if(infoBean.getCode()=="1"){
                        view.setInfo(infoBean);
                    }
                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
        }

    @Override
    public void AddIntegral(String id, String token) {
        detailsModel.addIntegral(id, token, new INetCallback<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean affirmRegisterBean) {
                if(affirmRegisterBean.getCode()==1){
                    view.setAddIntegral();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

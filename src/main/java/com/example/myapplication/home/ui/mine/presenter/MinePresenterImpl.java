package com.example.myapplication.home.ui.mine.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.mine.contract.MineContract;
import com.example.myapplication.home.ui.mine.model.MineModel;
import com.example.myapplication.home.ui.special.contract.SpecialContract;
import com.example.myapplication.net.INetCallback;

public class MinePresenterImpl extends BasePre<MineContract.MineView> implements MineContract.MinePre {

    private final MineModel mineModel;

    public MinePresenterImpl() {
        mineModel = new MineModel();
    }

    @Override
    public void getData() {

    }

    @Override
    public void qianDao(String token) {
        mineModel.qianDao(token, new INetCallback<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                if(verfiedBean.getCode()==1){
                    view.showToast("签到成功");
                    view.isQianDao();
                }else if(verfiedBean.getCode()==6){
                    view.showToast("已签到");
                    view.isQianDao();
                }else {
                    view.showToast(verfiedBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    @Override
    public void callMineData(String string) {

    }
}

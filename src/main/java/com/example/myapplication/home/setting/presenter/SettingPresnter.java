package com.example.myapplication.home.setting.presenter;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.setting.bean.CheckUpdateBean;
import com.example.myapplication.home.setting.contract.SettingContract;
import com.example.myapplication.home.setting.model.SettingModel;
import com.example.myapplication.net.INetCallback;

public class SettingPresnter extends BasePre<SettingContract.SettingView> implements SettingContract.SettingPre {

    private final SettingModel settingModel;

    public SettingPresnter() {
        settingModel = new SettingModel();
    }

    @Override
    public void getData(String version) {
settingModel.getData(version, new INetCallback<CheckUpdateBean>() {
    @Override
    public void onSuccess(CheckUpdateBean checkUpdateBean) {
        if(checkUpdateBean.getData().getIs_upgrade()==1){
            view.showToast("当前为最新版本");
        }else {
            view.showToast("当前不是最新版本");
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }
});
    }

    @Override
    public void callSettingData(String string) {

    }
}

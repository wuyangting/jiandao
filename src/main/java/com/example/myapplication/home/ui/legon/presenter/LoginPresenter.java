package com.example.myapplication.home.ui.legon.presenter;

import android.util.Log;
import android.widget.Toast;


import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.LoginPsdInfoBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.home.ui.legon.model.LoginModel;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.utils.SpUtil;

public class LoginPresenter extends BasePre<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {

   LoginModel iLoginMode;

    public LoginPresenter() {
        iLoginMode = new LoginModel();
    }

    @Override
    public void getVerified(String phoneNum,String type) {


        iLoginMode.getVerified(phoneNum, type, new INetCallback<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean s) {


                view.getVerified(s);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void login(String mobile, String smsCode) {
        iLoginMode.login(mobile, smsCode, new INetCallback<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {
                if(affirmRegisterBean.getCode()=="4"){
                    view.showToast("需要完善密码");
                }else if(affirmRegisterBean.getCode()=="1"){
                    if (null != affirmRegisterBean.getData().getToken().getValue() && affirmRegisterBean.getData().getToken().getValue() != "") {

//                用户信息 直接展示， 在本地存储了
                        SpUtil.setParam("token", affirmRegisterBean.getData().getToken().getValue());
                        SpUtil.setParam("expire_time", affirmRegisterBean.getData().getToken().getExpire_time());
                        SpUtil.setParam("head_url", affirmRegisterBean.getData().getUser_info().getHead_url());
                        SpUtil.setParam("nickname", affirmRegisterBean.getData().getUser_info().getNickname());
                        SpUtil.setParam("mobile", affirmRegisterBean.getData().getUser_info().getMobile());

                        view.showToast("登录成功，跳转HomeActivity");

                    }



                }else {
                    view.showToast("登陆失败");
                }
            }
            @Override
            public void onError(Throwable throwable) {

            }
        });
    }


    @Override
    public void checkSmsCode(String phoneNum, String smsCode, String type) {

        Log.e("TAG",phoneNum+"验证p层码值："+smsCode);

        iLoginMode.checkSmsCode(phoneNum, smsCode, type, new INetCallback<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {

                view.checkSmsCodeResult(verfiedBean);

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    public void regist(String phone, String pass, String confrim_pass) {
        iLoginMode.regist(phone,pass,confrim_pass, new INetCallback<AffirmRegisterBean>() {
            @Override
            public void onSuccess(AffirmRegisterBean affirmRegisterBean) {
                if(affirmRegisterBean.getCode().equals("1")){
                    if (null != affirmRegisterBean.getData().getToken().getValue() && affirmRegisterBean.getData().getToken().getValue() != "") {

//                用户信息 直接展示， 在本地存储了
                        SpUtil.setParam("token", affirmRegisterBean.getData().getToken().getValue());
                        SpUtil.setParam("expire_time", affirmRegisterBean.getData().getToken().getExpire_time());
                        SpUtil.setParam("head_url", affirmRegisterBean.getData().getUser_info().getHead_url());
                        SpUtil.setParam("nickname", affirmRegisterBean.getData().getUser_info().getNickname());
                        SpUtil.setParam("mobile", affirmRegisterBean.getData().getUser_info().getMobile());

                        view.showToast("注册成功，跳转HomeActivity");

                    }

                }else if(affirmRegisterBean.getCode()=="3"){
                    view.showToast("已经注册过");
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    public void login_pass(String phone, String pass) {
        iLoginMode.login_pass(phone,pass, new INetCallback<LoginPsdInfoBean>() {
            @Override
            public void onSuccess(LoginPsdInfoBean affirmRegisterBean) {
                if(affirmRegisterBean.getCode()==1) {
                    if (null != affirmRegisterBean.getData().getToken().getValue() && affirmRegisterBean.getData().getToken().getValue() != "") {

//                用户信息 直接展示， 在本地存储了

                       SpUtil.setParam("token", affirmRegisterBean.getData().getToken().getValue());
                        SpUtil.setParam("expire_time", affirmRegisterBean.getData().getToken().getExpire_time());
                        SpUtil.setParam("head_url", affirmRegisterBean.getData().getUser_info().getHead_url());
                        SpUtil.setParam("nickname", affirmRegisterBean.getData().getUser_info().getNickname());
                        SpUtil.setParam("mobile", affirmRegisterBean.getData().getUser_info().getMobile()+"");

                     view.showToast("登录成功，跳转HomeActivity");

                    }
                }else {
                    view.showToast("登陆失败");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                String message =
                        throwable.getMessage();
                view.showToast(message);
            }
        });



    }

    public void forgetPass(String mobile, String sms_code, String pass) {
        iLoginMode.forgetPass(mobile,sms_code,pass, new INetCallback<VerfiedBean>() {
            @Override
            public void onSuccess(VerfiedBean verfiedBean) {
                if(verfiedBean.getCode()==1) {
                    view.showToast("修改成功");
                }else if(verfiedBean.getCode()==2){
                    view.showToast("修改失败");
                }else {
                    view.showToast("需要登录");
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

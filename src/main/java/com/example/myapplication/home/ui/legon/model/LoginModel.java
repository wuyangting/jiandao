package com.example.myapplication.home.ui.legon.model;

import android.util.Log;

import com.example.myapplication.base.BaseModel;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.LoginPsdInfoBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.net.NetWorkFactory;
import com.example.myapplication.net.ParamsUtils;
import com.example.myapplication.net.api.NetApi;
import com.example.myapplication.net.api.URLConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class LoginModel extends BaseModel implements LoginContract.ILoginMode {
    @Override
    public <T> void getVerified(String phoneNum, String type, INetCallback<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SENDVERIFIED,commonParams,iNetCallBack);
    }

    @Override
    public <T> void login(String mobile, String smsCode, INetCallback<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("sms_code",smsCode);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.LOGIN,commonParams,iNetCallBack);
    }

    @Override
    public <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallback<T> iNetCallBack) {
        Log.e("TAG",phoneNum+"验证m层码值："+smsCode);



        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);
        commonParams.put("sms_code",smsCode);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.CHECKSMSCODE,commonParams,iNetCallBack);
    }

    public void regist(String phone, String pass, String confrim_pass, INetCallback<AffirmRegisterBean> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phone);
        commonParams.put("password",pass);
        commonParams.put("affirm_password",confrim_pass);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.REGIST,commonParams,iNetCallBack);
    }

    public void login_pass(String phone, String pass, INetCallback<LoginPsdInfoBean> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("username",phone);
        commonParams.put("password",pass);

//        for (String key: commonParams.keySet()) {
//            Log.e("TAG","key="+key+",values="+commonParams.get(key));
//        }
//        NetWorkFactory.getInstance().getNetWork().post(URLConstants.LOGIN_PASS,commonParams,iNetCallBack);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(addNetHeaderInterceptor())
//                .addInterceptor(addCacheInterceptor())
//                .cache(cache)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NetApi netApi = retrofit.create(NetApi.class);


        netApi.post(URLConstants.LOGIN_PASS,commonParams)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        LoginPsdInfoBean bean = new LoginPsdInfoBean();
                        try {
                            JSONObject object = new JSONObject(responseBody.string());
                            int code = object.optInt("code");
                            String message =  object.optString("message");
                            if (code == 1) {
//                                    用GSON   或者JSONObject或者JSONArray去解析
                                JSONObject dataObject = object.optJSONObject("data");
                                JSONObject tokenObject = dataObject.optJSONObject("token");

                                String tokenValue =  tokenObject.optString("value");
                                int expire_time =  tokenObject.optInt("expire_time");

                                LoginPsdInfoBean.DataBean dataBean = new LoginPsdInfoBean.DataBean();

                                LoginPsdInfoBean.DataBean.TokenBean tokenBean = new LoginPsdInfoBean.DataBean.TokenBean();
                                tokenBean.setValue(tokenValue);
                                tokenBean.setExpire_time(expire_time);

                                dataBean.setToken(tokenBean);

                                LoginPsdInfoBean.DataBean.UserInfoBean userInfoBean = new LoginPsdInfoBean.DataBean.UserInfoBean();

                                JSONObject infoObject = dataObject.optJSONObject("user_info");


                                userInfoBean.setHead_url(infoObject.optString("head_url"));
                                userInfoBean.setNickname(infoObject.optString("nickname"));
                                String mobile1 = infoObject.optString("mobile");
                                String email = infoObject.optString("email");
                                String qq_bind = infoObject.optString("qq_bind");
                                String qq_openid = infoObject.optString("qq_openid");
                                String qq_unionid = infoObject.optString("qq_unionid");
                                String sina_bind = infoObject.optString("sina_bind");
                                String sina_openid = infoObject.optString("sina_openid");
                                String sina_unionid = infoObject.optString("sina_unionid");
                                String wechat_bind = infoObject.optString("wechat_bind");
                                String wechat_openid = infoObject.optString("wechat_openid");
                                String wechat_unionid = infoObject.optString("wechat_unionid");
                                String notice_count = infoObject.optString("notice_count");
                                String my_integral = infoObject.optString("my_integral");
                                String check_in_status = infoObject.optString("check_in_status");

                                dataBean.setUser_info(userInfoBean);
                                bean.setData(dataBean);
                                bean.setCode(code);
iNetCallBack.onSuccess(bean);
                            } else {
                                bean.setCode(code);
                                bean.setMessage(message);

                                Throwable throwable = new Throwable(object.optString("message"));
                                iNetCallBack.onError(throwable);
                                Log.e("TAG", "登录失败" + object.optString("message"));
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void forgetPass(String mobile, String sms_code, String pass, INetCallback<VerfiedBean> iNetCallBack) {

        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("sms_code",sms_code);
        commonParams.put("password",pass);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.FORGET_PASS,commonParams,iNetCallBack);
    }
}

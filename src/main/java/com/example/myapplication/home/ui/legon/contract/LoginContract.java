package com.example.myapplication.home.ui.legon.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.net.INetCallback;

/**
 * 契约类
 * 契约     约定
 */
public class LoginContract {

        public interface ILoginView extends BaseView {

//            逻辑判断在P层判断--为了简单一点，扔道Acitivty
            void getVerified(VerfiedBean s);

            void  getLoginResult(String string);

            void checkSmsCodeResult(VerfiedBean verfiedBean);

            void getUserInfo(AffirmRegisterBean affirmRegisterBean);

        }
        public interface ILoginMode{
           <T> void getVerified(String phoneNum, String type, INetCallback<T> iNetCallBack);
            <T> void login(String mobile, String smsCode, INetCallback<T> iNetCallBack);
            <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallback<T> iNetCallBack);

        }
        public interface ILoginPresenter{
            void getVerified(String string, String type);
            void login(String mobile, String smsCode);
            void checkSmsCode(String phoneNum, String smsCode, String type);
        }
}

package com.example.myapplication.home.ui.legon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.home.ui.legon.presenter.LoginPresenter;
import com.example.myapplication.home.view.HomeActivity;
import com.example.myapplication.utils.SpUtil;
import com.tamsiree.rxkit.RxConstTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassWordLoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.ILoginView {

    private ImageView mClose;
    private EditText mPhoneEt;
    private EditText mPassEt;
    private TextView mPassForget;
    private Button mLegon;
    private TextView mLoginVerfi;
    private TextView mRegist;
    private ImageView mWechat;
    private ImageView mQq;
    private ImageView mXinlang;
    private LinearLayout mLin;


    @Override
    protected LoginPresenter initPre() {
        return new LoginPresenter();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
@Override
    protected void initView() {
        mClose = (ImageView) findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mPhoneEt = (EditText) findViewById(R.id.et_phone);
        mPassEt = (EditText) findViewById(R.id.et_pass);
        mPassForget = (TextView) findViewById(R.id.forget_pass);
        mPassForget.setOnClickListener(this);
        mLegon = (Button) findViewById(R.id.legon);
        mLegon.setOnClickListener(this);
        mLoginVerfi = (TextView) findViewById(R.id.verfi_login);
        mLoginVerfi.setOnClickListener(this);
        mRegist = (TextView) findViewById(R.id.regist);
        mRegist.setOnClickListener(this);
        mWechat = (ImageView) findViewById(R.id.wechat);
        mWechat.setOnClickListener(this);
        mQq = (ImageView) findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mXinlang = (ImageView) findViewById(R.id.xinlang);
        mXinlang.setOnClickListener(this);
        mLin = (LinearLayout) findViewById(R.id.lin);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pass_word_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                // TODO 20/05/09
                finish();
                break;
            case R.id.forget_pass:
                // TODO 20/05/09
                forget_pass();
                break;
            case R.id.legon:
                // TODO 20/05/09
                legon();
                break;
            case R.id.verfi_login:
                // TODO 20/05/09
                Intent intent = new Intent(this, LegonActivity.class);
                startActivity(intent);
                break;
            case R.id.regist:
                Intent intent1 = new Intent(this, RegistActivity.class);
                startActivity(intent1);
                // TODO 20/05/09
                break;
            case R.id.wechat:
                // TODO 20/05/09
                break;
            case R.id.qq:
                // TODO 20/05/09
                break;
            case R.id.xinlang:
                // TODO 20/05/09
                break;
            default:
                break;
        }
    }

    private void forget_pass() {
        String phone = mPhoneEt.getText().toString();
        if(!TextUtils.isEmpty(phone)&&isMobileNO(phone)){
            Intent intent = new Intent(this,ForgetPassActivity.class);
            intent.putExtra("phone",phone);
            startActivity(intent);
        }else {
            Toast.makeText(this, "输入手机号有误", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        boolean flag = false;
        try{
            Pattern p = Pattern.compile( RxConstTool.REGEX_MOBILE_SIMPLE+"");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
            Log.e("TAG","手机号错误"+e.getMessage());
            flag = false;
        }
        return flag;
    }
    private void legon() {
        String phone = mPhoneEt.getText().toString();
        String pass = mPassEt.getText().toString();
        if(!phone.isEmpty()&&!pass.isEmpty()){
           pre.login_pass(phone,pass);
        }else {
            Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getVerified(VerfiedBean s) {

    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {

    }

    @Override
    public void getUserInfo(AffirmRegisterBean affirmRegisterBean) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if(msg.equals("登录成功，跳转HomeActivity")){
            SpUtil.setParam("isLogin",true);
            String token = (String) SpUtil.getParam("token", "");
            Toast.makeText(this,token, Toast.LENGTH_SHORT).show();
            MyApplication.isLogin=true;
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
        }
    }
}

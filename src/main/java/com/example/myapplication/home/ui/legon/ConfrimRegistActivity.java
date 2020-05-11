package com.example.myapplication.home.ui.legon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ConfrimRegistActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.ILoginView {

    private ImageView mClose;
    private EditText mPass;
    private EditText mPassConfrim;
    private Button mRegist;
    private TextView mVerfiLegonConfrim;
    private TextView mPassLegonConfrim;
    private ImageView mWechat;
    private ImageView mQq;
    private ImageView mXinlang;
    private String phone;


    @Override
    protected LoginPresenter initPre() {
      return   new LoginPresenter();
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
        mPass = (EditText) findViewById(R.id.pass);
        mPassConfrim = (EditText) findViewById(R.id.confrim_pass);
        mRegist = (Button) findViewById(R.id.regist);
        mRegist.setOnClickListener(this);
        mVerfiLegonConfrim = (TextView) findViewById(R.id.confrim_verfi_legon);
        mVerfiLegonConfrim.setOnClickListener(this);
        mPassLegonConfrim = (TextView) findViewById(R.id.confrim_pass_legon);
        mPassLegonConfrim.setOnClickListener(this);
        mWechat = (ImageView) findViewById(R.id.wechat);
        mWechat.setOnClickListener(this);
        mQq = (ImageView) findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mXinlang = (ImageView) findViewById(R.id.xinlang);
        mXinlang.setOnClickListener(this);
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_confrim_regist;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                // TODO 20/05/09
                finish();
                break;
            case R.id.regist:
                // TODO 20/05/09
                regist();
                break;
            case R.id.confrim_verfi_legon:
                Intent intent = new Intent(this, LegonActivity.class);
                startActivity(intent);
                // TODO 20/05/09
                break;
            case R.id.confrim_pass_legon:
                Intent intent1 = new Intent(this, PassWordLoginActivity.class);
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

    private void regist() {
        String pass = mPass.getText().toString();
        String confrim_pass = mPassConfrim.getText().toString();
        if(!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(confrim_pass)){
            if(pass.length()>=6&&confrim_pass.length()>=6){
                if(pass.equals(confrim_pass)){
                    pre.regist(phone,pass,confrim_pass);
                }else {
                    Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "密码长度不能低于6位", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "请输入密码或者确认密码", Toast.LENGTH_SHORT).show();
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
        if(msg.equals("注册成功，跳转HomeActivity")){
            SpUtil.setParam("isLogin",true);
            String token = (String) SpUtil.getParam("token", "");
            Toast.makeText(this,token, Toast.LENGTH_SHORT).show();
            MyApplication.isLogin=true;
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}

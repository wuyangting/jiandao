package com.example.myapplication.home.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.legon.LegonActivity;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.home.ui.legon.presenter.LoginPresenter;

public class ConfimPassActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView, View.OnClickListener {

    private ImageView mClose;
    private EditText mPass;
    private EditText mPassConfrim;
    private Button mComplete;
    private String mobile;
    private String sms_code;

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
        mPass = (EditText) findViewById(R.id.pass);
        mPassConfrim = (EditText) findViewById(R.id.confrim_pass);
        mComplete = (Button) findViewById(R.id.complete);
        mComplete.setOnClickListener(this);
        mobile = getIntent().getStringExtra("mobile");
        sms_code = getIntent().getStringExtra("sms_code");

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_confim_pass;
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
        if(msg.equals("修改成功")){
            Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ConfimPassActivity.this, LegonActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                // TODO 20/05/11
                finish();
                break;
            case R.id.complete:
                // TODO 20/05/11
                confrim_Pass();
                break;
            default:
                break;
        }
    }

    private void confrim_Pass() {
        String pass = mPass.getText().toString().trim();
        String confrim_Pass = mPassConfrim.getText().toString().trim();
        if(!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(confrim_Pass)){
            if(pass.length()>=6&&confrim_Pass.length()>=6){
                if(pass.equals(confrim_Pass)){
                    pre.forgetPass(mobile,sms_code,pass);
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
}

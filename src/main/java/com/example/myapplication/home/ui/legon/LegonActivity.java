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

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegonActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.ILoginView {

    private ImageView mClose;
    private EditText mPhoneEt;
    private EditText mVerfiCodeEt;
    private TextView mVerficodeGet;
    private Button mLegon;
    private TextView mLegonPass;
    private TextView mRegist;
    private ImageView mWechat;
    private ImageView mQq;
    private ImageView mXinlang;
    private LinearLayout mLin;
    private String edit_phone_num;
    private String edit_sms_code;
    private Timer timer;
    private TimerTask timerTask;
    private int countdown=60;


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
        mVerfiCodeEt = (EditText) findViewById(R.id.et_verfi_code);
        mVerficodeGet = (TextView) findViewById(R.id.get_verficode);
        mVerficodeGet.setOnClickListener(this);
        mLegon = (Button) findViewById(R.id.legon);
        mLegon.setOnClickListener(this);
        mLegonPass = (TextView) findViewById(R.id.pass_legon);
        mLegonPass.setOnClickListener(this);
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
        return R.layout.activity_legon;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                // TODO 20/05/09
                finish();
                break;
            case R.id.get_verficode:
                getCode();
                // TODO 20/05/09
                break;
            case R.id.legon:
                // TODO 20/05/09
                legon();
                break;
            case R.id.pass_legon:
                // TODO 20/05/09
                Intent intent = new Intent(this, PassWordLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.regist:
                regist();
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
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }

    private void legon() {
        edit_phone_num = mPhoneEt.getText().toString();
        edit_sms_code = mVerfiCodeEt.getText().toString();
        if( !TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)){
            if( !TextUtils.isEmpty(edit_sms_code)){
//                    需要用正则表达式判断验证码是否是6位，且都是数字
                Pattern pattern = Pattern.compile("\\d{6}");
                boolean matches = pattern.matcher(edit_sms_code).matches();
                if(matches){
                    Log.e("TAG",edit_phone_num+"验证码值："+edit_sms_code);

                    pre.checkSmsCode(edit_phone_num,edit_sms_code,"4");
                }else Toast.makeText(LegonActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(LegonActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(LegonActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
    }

    private void getCode() {
        String phonenum = mPhoneEt.getText().toString();
        if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
            pre.getVerified(phonenum,"4");
            mVerficodeGet.setClickable(false);
            initTimer();
        }else Toast.makeText(LegonActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
    }

    private void initTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
            countdown--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mVerficodeGet.setText(countdown+"");
                        if(countdown==0){
                            countdown=60;
                            timer.cancel();//1384562
                            mVerficodeGet.setClickable(true);
                            mVerficodeGet.setText(R.string.getcode);
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask,0,1000);

    }

    @Override
    public void getVerified(VerfiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(this, "发送验证码成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "发送验证码失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {

        if(verfiedBean.getCode() ==1){

            pre.login(edit_phone_num,edit_sms_code);

        }else Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
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
    public void notifi(int posi){

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer!=null){
            timer.cancel();//15473254126
            mVerficodeGet.setText("获取验证码");
        }
    }


}

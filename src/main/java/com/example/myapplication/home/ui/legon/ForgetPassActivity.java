package com.example.myapplication.home.ui.legon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.ConfimPassActivity;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.home.ui.legon.presenter.LoginPresenter;
import com.tamsiree.rxkit.RxConstTool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.ILoginView {

    private ImageView mClose;
    private EditText mPhone;
    private EditText mGetcodeEt;
    private TextView mCodeGet;
    private Button mNext;
    private Timer timer;
    private TimerTask timerTask;
    private int countdown=60;
    private String edit_phone_num;
    private String edit_sms_code;


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
        mPhone = (EditText) findViewById(R.id.phone);
        mGetcodeEt = (EditText) findViewById(R.id.et_getcode);
        mCodeGet = (TextView) findViewById(R.id.get_code);
        mCodeGet.setOnClickListener(this);
        mNext = (Button) findViewById(R.id.next);
        mNext.setOnClickListener(this);
        mPhone.setText(getIntent().getStringExtra("phone"));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_pass;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                finish();
                // TODO 20/05/09
                break;
            case R.id.get_code:
                // TODO 20/05/09
                getCode();
                break;
            case R.id.next:
                // TODO 20/05/09
                next();
                break;
            default:
                break;
        }
    }

    private void next() {
        edit_phone_num = mPhone.getText().toString();
        edit_sms_code = mGetcodeEt.getText().toString();
        if( !TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)){
            if( !TextUtils.isEmpty(edit_sms_code)){
//                    需要用正则表达式判断验证码是否是6位，且都是数字
                Pattern pattern = Pattern.compile("\\d{6}");
                boolean matches = pattern.matcher(edit_sms_code).matches();
                if(matches){
                    Log.e("TAG",edit_phone_num+"验证码值："+edit_sms_code);

                    pre.checkSmsCode(edit_phone_num,edit_sms_code,"2");
                }else Toast.makeText(ForgetPassActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(ForgetPassActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(ForgetPassActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
    }

    private void getCode() {
            String phonenum = mPhone.getText().toString();
            if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
                pre.getVerified(phonenum,"2");
                mCodeGet.setClickable(false);
                initTimer();
            }else Toast.makeText(ForgetPassActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
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

    @Override
    public void getVerified(VerfiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(this, "发送验证码成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "发送验证码失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer!=null){
            timer.cancel();//15473254126
            mCodeGet.setText("获取验证码");
        }
    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        //说明验证码正确
        if(verfiedBean.getCode()==1){
            showToast("验证码正确");
            Intent intent = new Intent(this, ConfimPassActivity.class);
            intent.putExtra("mobile",edit_phone_num);
            intent.putExtra("sms_code",edit_sms_code);
            startActivity(intent);
        }else {
            showToast("验证码错误");
        }
    }

    @Override
    public void getUserInfo(AffirmRegisterBean affirmRegisterBean) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
                        mCodeGet.setText(countdown+"");
                        if(countdown==0){
                            countdown=60;
                            timer.cancel();//1384562
                            mCodeGet.setClickable(true);
                            mCodeGet.setText(R.string.getcode);
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask,0,1000);

    }
}

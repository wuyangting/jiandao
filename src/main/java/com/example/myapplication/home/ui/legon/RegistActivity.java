package com.example.myapplication.home.ui.legon;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegistActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView, View.OnClickListener {


    private ImageView mClose;
    private EditText mEtPhoneReg;
    private EditText mEtVerfiCodeReg;
    private TextView mVerficodeGet;
    private Button mNextReg;
    private TextView mVerfiLegonReg;
    private TextView mPassLegonReg;
    private ImageView mWechat;
    private ImageView mQq;
    private ImageView mXinlang;
    private String edit_phone_num;
    private String edit_sms_code;
    private int countdown=60;
    private Timer timer;
    private TimerTask timerTask;

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
        mEtPhoneReg = (EditText) findViewById(R.id.reg_et_phone);
        mEtVerfiCodeReg = (EditText) findViewById(R.id.reg_et_verfi_code);
        mVerficodeGet = (TextView) findViewById(R.id.get_verficode);
        mVerficodeGet.setOnClickListener(this);
        mNextReg = (Button) findViewById(R.id.reg_next);
        mNextReg.setOnClickListener(this);
        mVerfiLegonReg = (TextView) findViewById(R.id.reg_verfi_legon);
        mVerfiLegonReg.setOnClickListener(this);
        mPassLegonReg = (TextView) findViewById(R.id.reg_pass_legon);
        mPassLegonReg.setOnClickListener(this);
        mWechat = (ImageView) findViewById(R.id.wechat);
        mWechat.setOnClickListener(this);
        mQq = (ImageView) findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mXinlang = (ImageView) findViewById(R.id.xinlang);
        mXinlang.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void getVerified(VerfiedBean s) {

    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        //说明验证码正确
        if(verfiedBean.getCode()==1){
            showToast("成功");
            Intent intent = new Intent(this,ConfrimRegistActivity.class);
            intent.putExtra("phone",edit_phone_num);
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
            case R.id.reg_next:
                // TODO 20/05/09
                //下一步进入之前两次判断：1.手机号是否正确2.验证验证码的正确都为true时进入下一个页面，输入密码
                next();
                break;
            case R.id.reg_verfi_legon:
                Intent intent = new Intent(this, LegonActivity.class);
                startActivity(intent);
                // TODO 20/05/09
                break;
            case R.id.reg_pass_legon:
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
    private void getCode() {
        String phonenum = mEtPhoneReg.getText().toString();
        if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
            pre.getVerified(phonenum,"1");
            initTimer();
        }else Toast.makeText(RegistActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
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
                            timer.cancel();//13845627314
                            mVerficodeGet.setClickable(true);
                            mVerficodeGet.setText(R.string.getcode);
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask,0,1000);

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
    private void next() {
        edit_phone_num = mEtPhoneReg.getText().toString();
        edit_sms_code = mEtVerfiCodeReg.getText().toString();
        if( !TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)){
            if( !TextUtils.isEmpty(edit_sms_code)){
//                    需要用正则表达式判断验证码是否是6位，且都是数字
                Pattern pattern = Pattern.compile("\\d{6}");
                boolean matches = pattern.matcher(edit_sms_code).matches();
                if(matches){
                    Log.e("TAG",edit_phone_num+"验证码值："+edit_sms_code);

                    pre.checkSmsCode(edit_phone_num,edit_sms_code,"1");
                }else Toast.makeText(RegistActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(RegistActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(RegistActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer!=null){
            timer.cancel();
            mVerficodeGet.setText("获取验证码");
        }
    }
}

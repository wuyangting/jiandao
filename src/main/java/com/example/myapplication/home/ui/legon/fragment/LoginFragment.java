package com.example.myapplication.home.ui.legon.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.legon.LegonActivity;
import com.example.myapplication.home.ui.legon.bean.AffirmRegisterBean;
import com.example.myapplication.home.ui.legon.bean.VerfiedBean;
import com.example.myapplication.home.ui.legon.contract.LoginContract;
import com.example.myapplication.home.ui.legon.presenter.LoginPresenter;
import com.example.myapplication.utils.Constans;
import com.tamsiree.rxkit.RxConstTool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.ILoginView, View.OnClickListener {


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
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter initPre() {
        return new LoginPresenter();
    }

    @Override
    protected void initView(View inflate) {
        mClose = (ImageView) inflate.findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mPhoneEt = (EditText)inflate. findViewById(R.id.et_phone);
        mVerfiCodeEt = (EditText) inflate. findViewById(R.id.et_verfi_code);
        mVerficodeGet = (TextView) inflate. findViewById(R.id.get_verficode);
        mVerficodeGet.setOnClickListener(this);
        mLegon = (Button)inflate.  findViewById(R.id.legon);
        mLegon.setOnClickListener(this);
        mLegonPass = (TextView) inflate. findViewById(R.id.pass_legon);
        mLegonPass.setOnClickListener(this);
        mRegist = (TextView) inflate. findViewById(R.id.regist);
        mRegist.setOnClickListener(this);
        mWechat = (ImageView) inflate. findViewById(R.id.wechat);
        mWechat.setOnClickListener(this);
        mQq = (ImageView)inflate.  findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mXinlang = (ImageView) inflate. findViewById(R.id.xinlang);
        mXinlang.setOnClickListener(this);
        mLin = (LinearLayout)inflate.  findViewById(R.id.lin);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_legon;
    }

    @Override
    public void getVerified(VerfiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getLoginResult(String string) {

    }

    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        if(verfiedBean.getCode() ==1){

            pre.login(edit_phone_num,edit_sms_code);

        }else Toast.makeText(getActivity(), "验证码输入错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserInfo(AffirmRegisterBean affirmRegisterBean) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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
        LegonActivity activity = (LegonActivity) getActivity();
        activity.notifi(Constans.REGIST);
    }

    private void finish() {
        getActivity().finish();
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
                }else Toast.makeText(getActivity(), "验证码输入错误", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getActivity(), "请输入验证码", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(getActivity(), "请输入正确得手机号", Toast.LENGTH_SHORT).show();
    }

    private void initTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                countdown--;
                getActivity().runOnUiThread(new Runnable() {
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
    private void getCode() {
        String phonenum = mPhoneEt.getText().toString();
        if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
            pre.getVerified(phonenum,"4");
            mVerficodeGet.setClickable(false);
            initTimer();
        }else Toast.makeText(getActivity(), "请输入正确得手机号", Toast.LENGTH_SHORT).show();
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

}

package com.example.myapplication.home.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.setting.contract.SettingContract;
import com.example.myapplication.home.setting.model.SettingModel;
import com.example.myapplication.home.setting.presenter.SettingPresnter;
import com.example.myapplication.utils.SpUtil;

public class SettingActivity extends BaseActivity<SettingPresnter> implements View.OnClickListener, SettingContract.SettingView {

    private ImageView mBack;
    private TextView mTxtSetting;
    private TextView mCacheClean;
    private CheckBox mCacheCheckClean;
    private CheckBox mCheckPush;
    private TextView mAgreementUser;
    private TextView mPolicyPrivacy;
    private TextView mUpdateCheck;
    private TextView mAppRecommend;
    private Button mLegonExit;
    private TextView mVersion;
    private String version;


@Override
    protected void initView() {
        version = (String)SpUtil.getParam("version","");
        mBack = (ImageView) findViewById(R.id.back);

        mTxtSetting = (TextView) findViewById(R.id.setting_txt);
        mTxtSetting.setOnClickListener(this);
        mCacheClean = (TextView) findViewById(R.id.clean_cache);
        mCacheClean.setOnClickListener(this);
        mCacheCheckClean = (CheckBox) findViewById(R.id.clean_cache_check);
        mCheckPush = (CheckBox) findViewById(R.id.push_check);
        mAgreementUser = (TextView) findViewById(R.id.user_agreement);
        mAgreementUser.setOnClickListener(this);
        mPolicyPrivacy = (TextView) findViewById(R.id.privacy_policy);
        mPolicyPrivacy.setOnClickListener(this);
        mUpdateCheck = (TextView) findViewById(R.id.check_update);
        mUpdateCheck.setOnClickListener(this);
        mAppRecommend = (TextView) findViewById(R.id.recommend_app);
        mAppRecommend.setOnClickListener(this);
        mLegonExit = (Button) findViewById(R.id.exit_legon);
        mLegonExit.setOnClickListener(this);
        mVersion = (TextView) findViewById(R.id.version);
        mBack.setOnClickListener(this);

        initListener();
       mVersion.setText("见道"+version);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingPresnter initPre() {
        return new SettingPresnter();
    }
@Override
    protected void initListener() {

        mCacheCheckClean.setChecked((Boolean) SpUtil.getParam("cache_switch", false));
        mCheckPush.setChecked((Boolean) SpUtil.getParam("push_switch", false));
        //强力缓存开关
        mCacheCheckClean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpUtil.setParam("cache_switch", true);
                } else {
                    SpUtil.setParam("cache_switch", false);
                }
            }
        });
        //推送开关
        mCheckPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpUtil.setParam("push_switch", true);
                } else {
                    SpUtil.setParam("push_switch", false);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 20/05/01
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.setting_txt:// TODO 20/05/01
                break;
            case R.id.clean_cache:// TODO 20/05/01
                Toast.makeText(this, "清除缓存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_agreement:// TODO 20/05/01
                Toast.makeText(this, "用户协议", Toast.LENGTH_SHORT).show();
                break;
            case R.id.privacy_policy:// TODO 20/05/01
                Toast.makeText(this, "隐私政策", Toast.LENGTH_SHORT).show();
                break;
            case R.id.check_update:// TODO 20/05/01
               checkUpdate();
                break;
            case R.id.recommend_app:// TODO 20/05/01
                Toast.makeText(this, "推荐app", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_legon:// TODO 20/05/01
                if(MyApplication.isLogin){
                    SpUtil.setParam("isLogin",false);
                    MyApplication.isLogin=false;
                    Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void checkUpdate() {
        pre.getData(version);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
}
}

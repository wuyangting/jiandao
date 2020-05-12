package com.example.myapplication.home.ui.jifen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.jifen.bean.JiFenBean;
import com.example.myapplication.home.ui.jifen.contract.JiFenContract;
import com.example.myapplication.home.ui.jifen.presenter.JiFenPreImpl;
import com.example.myapplication.utils.SpUtil;

public class JiFenActivity extends BaseActivity<JiFenPreImpl> implements View.OnClickListener, JiFenContract.JiFenView {

    private ImageView mBack;
    private TextView mJifen;
    private TextView mQiandaoIs;
    private TextView mPrecentRead;
    private TextView mPrecentShare;
    private String token;

    @Override
    protected JiFenPreImpl initPre() {
        return new JiFenPreImpl();
    }

    @Override
    protected void initListener() {

        pre.getVerifiedJiFenInfo(token);
    }

    @Override
    protected void initData() {

    }
@Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mJifen = (TextView) findViewById(R.id.jifen);
        mQiandaoIs = (TextView) findViewById(R.id.is_qiandao);
        mPrecentRead = (TextView) findViewById(R.id.read_precent);
        mPrecentShare = (TextView) findViewById(R.id.share_precent);
    token = (String) SpUtil.getParam("token", "");
}

    @Override
    protected int getLayout() {
        return R.layout.activity_ji_fen;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 20/05/12
                finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void setJiFenInfo(JiFenBean jiFenInfo) {
        if(jiFenInfo.getCode()=="2"){
            showToast(jiFenInfo.getMessage());
        }else {
            JiFenBean.DataBean data = jiFenInfo.getData();
            //设置积分总数
           mJifen.setText( data.getMy_integral());
           //判断是否签到
            if(data.getCheck_in_status()=="1"){
                mQiandaoIs.setText("已签到");
            }else {
                mQiandaoIs.setText("未签到");
            }

            //设置阅读文章和分享文章的积分百分比
            mPrecentRead.setText(data.getRead_article_count()+"/10");
            mPrecentShare.setText(data.getShare_article_count()+"/10");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

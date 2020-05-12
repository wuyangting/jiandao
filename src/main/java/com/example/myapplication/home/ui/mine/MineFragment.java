package com.example.myapplication.home.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.setting.SettingActivity;
import com.example.myapplication.home.ui.collect.CollectActivity;
import com.example.myapplication.home.ui.jifen.JiFenActivity;
import com.example.myapplication.home.ui.legon.LegonActivity;
import com.example.myapplication.home.ui.mine.contract.MineContract;
import com.example.myapplication.home.ui.mine.presenter.MinePresenterImpl;
import com.example.myapplication.utils.SpUtil;


public class MineFragment extends BaseFragment<MinePresenterImpl> implements MineContract.MineView, View.OnClickListener {

    private ImageView mUserpic;
    private TextView mTxtJifen;
    private TextView mTxtShoucang;
    private TextView mTxtMes;
    private TextView mTxtSetting;
    private Button mLegon;
    private TextView mDescNolegon;
    private TextView mLegonorno;
    private String token;
    private Button mNotifiSign;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected MinePresenterImpl initPre() {
        return new MinePresenterImpl();
    }

    @Override
    protected void initView(View inflate) {

        mUserpic = (ImageView) inflate.findViewById(R.id.userpic);
        mTxtJifen = (TextView) inflate.findViewById(R.id.jifen_txt);
        mTxtShoucang = (TextView) inflate.findViewById(R.id.shoucang_txt);
        mTxtMes = (TextView) inflate.findViewById(R.id.mes_txt);
        mTxtSetting = (TextView) inflate.findViewById(R.id.setting_txt);
        mLegon = (Button) inflate.findViewById(R.id.legon);
        mNotifiSign = (Button) inflate.findViewById(R.id.sign_notifi);
        mNotifiSign.setOnClickListener(this);
        mUserpic.setOnClickListener(this);
        mTxtJifen.setOnClickListener(this);
        mTxtShoucang.setOnClickListener(this);
        mTxtMes.setOnClickListener(this);
        mTxtSetting.setOnClickListener(this);
        mLegon.setOnClickListener(this);
        mLegonorno = (TextView) inflate.findViewById(R.id.legonorno);
        mDescNolegon = (TextView) inflate.findViewById(R.id.nolegon_desc);
        if (MyApplication.isLogin) {
            mLegon.setVisibility(View.GONE);
            mDescNolegon.setVisibility(View.GONE);
            String head_url = (String) SpUtil.getParam("head_url", "");
            token = (String) SpUtil.getParam("token", "");
            if (head_url != null) {
                Glide.with(getActivity()).load(head_url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mUserpic);
            }
            String nickname = (String) SpUtil.getParam("nickname", "");
            mLegonorno.setText(nickname);
        } else {
            mLegon.setVisibility(View.VISIBLE);
            mDescNolegon.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (MyApplication.isLogin) {
            mLegon.setVisibility(View.GONE);
            mDescNolegon.setVisibility(View.GONE);
            String head_url = (String) SpUtil.getParam("head_url", "");
            if (head_url != null) {
                Glide.with(getActivity()).load(head_url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mUserpic);
            }
            String nickname = (String) SpUtil.getParam("nickname", "");
            mLegonorno.setText(nickname);
        } else {
            mLegon.setVisibility(View.VISIBLE);
            mDescNolegon.setVisibility(View.VISIBLE);
            mLegonorno.setText(R.string.no_login);
            Glide.with(getActivity()).load(R.drawable.mine_pic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mUserpic);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void setData() {

    }

    @Override
    public void isQianDao() {
        mNotifiSign.setEnabled(false);
        mNotifiSign.setBackgroundResource(R.color.gray);
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.legon:
                // TODO 20/05/01
                legon();
                break;
            case R.id.jifen_txt:// TODO 20/05/01
                jifen();
                break;
            case R.id.sign_notifi:// TODO 20/05/01
                qianDao();
                break;
            case R.id.shoucang_txt:// TODO 20/05/01
                shouCang();
                break;
            case R.id.mes_txt:// TODO 20/05/01
                message();
                break;
            case R.id.setting_txt:// TODO 20/05/01
                setting();
                break;
            case R.id.userpic:// TODO 20/05/01
                userPic();
                break;
            default:
                break;
        }
    }

    private void qianDao() {
        if (MyApplication.isLogin) {
            pre.qianDao(token);
        } else {
            Intent intent = new Intent(getActivity(), LegonActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void userPic() {
        if (MyApplication.isLogin) {
            Toast.makeText(getActivity(), "您已经登陆成功，我们会尽快开发这个模块功能的！！！", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getActivity(), LegonActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void legon() {
        if (MyApplication.isLogin) {
            Toast.makeText(getActivity(), "您已经登陆成功了", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getActivity(), LegonActivity.class);
            startActivity(intent);
        }

    }

    private void setting() {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    private void message() {
        Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    private void shouCang() {
        Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), CollectActivity.class));
    }

    private void jifen() {
        if (MyApplication.isLogin) {
            startActivity(new Intent(getActivity(), JiFenActivity.class));
        } else {
            Intent intent = new Intent(getActivity(), LegonActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }
    }
}

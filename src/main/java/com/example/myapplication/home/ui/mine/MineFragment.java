package com.example.myapplication.home.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.setting.SettingActivity;
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
        mUserpic.setOnClickListener(this);
        mTxtJifen.setOnClickListener(this);
        mTxtShoucang.setOnClickListener(this);
        mTxtMes.setOnClickListener(this);
        mTxtSetting.setOnClickListener(this);
        mLegon.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void setData() {

    }

    @Override
    public void showToast(String msg) {

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

    private void userPic() {
        if(MyApplication.isLogin){
            Toast.makeText(getActivity(), "您已经登陆成功，我们会尽快开发这个模块功能的！！！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    private void legon() {
        if(MyApplication.isLogin){
            Toast.makeText(getActivity(), "您已经登陆成功了", Toast.LENGTH_SHORT).show();
        }else {
            SpUtil.setParam("isLogin",true);
            MyApplication.isLogin=true;
            Toast.makeText(getActivity(), "恭喜您,登陆成功", Toast.LENGTH_SHORT).show();
        }

    }

    private void setting() {
     startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    private void message() {
        Toast.makeText(getActivity(), "消息", Toast.LENGTH_SHORT).show();
    }

    private void shouCang() {
        Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
    }

    private void jifen() {
        Toast.makeText(getActivity(), "积分", Toast.LENGTH_SHORT).show();
    }
}

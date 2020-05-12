package com.example.myapplication.home.ui.details;

import android.content.Intent;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.details.adapter.Details_Rec_Adapter;
import com.example.myapplication.home.ui.details.contract.DetailsContract;
import com.example.myapplication.home.ui.details.pre.DetailsPresenterImpl;
import com.example.myapplication.home.ui.legon.LegonActivity;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.utils.LoginUtil;
import com.example.myapplication.utils.SpUtil;

import java.util.ArrayList;

public class DetailsActivity extends BaseActivity<DetailsPresenterImpl> implements DetailsContract.DetailsView, View.OnClickListener {

    private ImageView mBackInfo;
    private ImageView mFxInfo;
    private ImageView mCollectInfo;
    private ImageView mGoodInfo;
    private ImageView mSearchInfo;
    private String id;
    private RecyclerView mRecDetail;
    private String link;
    private ArrayList<RecommendRecBean.ResultData> resultData;
    private Details_Rec_Adapter adapter;
    private String token;
    //收藏
private int collect=1;
//点赞
private int like=1;
    @Override
    protected DetailsPresenterImpl initPre() {
        return new DetailsPresenterImpl();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        //获取文章的推荐文章列表
        pre.getData(id);

        //获取用户点赞，收藏文章等信息
        if(LoginUtil.ifLogin()){
            pre.getInfo(id,token);
            pre.AddIntegral(id,token);
        }

    }

    @Override
    protected void initView() {
        mBackInfo = (ImageView) findViewById(R.id.info_back);
        mBackInfo.setOnClickListener(this);
        mFxInfo = (ImageView) findViewById(R.id.info_fx);
        mFxInfo.setOnClickListener(this);
        mCollectInfo = (ImageView) findViewById(R.id.info_collect);
        mCollectInfo.setOnClickListener(this);
        mGoodInfo = (ImageView) findViewById(R.id.info_good);
        mGoodInfo.setOnClickListener(this);
        mSearchInfo = (ImageView) findViewById(R.id.info_search);
        mSearchInfo.setOnClickListener(this);
        mRecDetail = (RecyclerView) findViewById(R.id.detail_rec);
        link = getIntent().getStringExtra("link");
        id = getIntent().getStringExtra("id");
        resultData = new ArrayList<>();
        mRecDetail.setLayoutManager(new LinearLayoutManager(this));
        initWeb();
        initImage();
        adapter = new Details_Rec_Adapter(resultData);
        mRecDetail.setAdapter(adapter);
        if(MyApplication.isLogin){
            token = (String) SpUtil.getParam("token", "");
        }
    }

    private void initImage() {
        RecommendRecBean.ResultData image = new RecommendRecBean.ResultData();
        image.type=1;
        resultData.add(image);
    }

    private void initWeb() {
        RecommendRecBean.ResultData web = new RecommendRecBean.ResultData();
        web.data=link;
        web.type=0;
        resultData.add(web);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void setData(TabBean tabBean) {

    }

    @Override
    public void setInfo(InfoBean info) {
        if(info.getCode()=="1"){
            InfoBean.DataBean data = info.getData();
            String is_collect = data.getIs_collect();
            if(is_collect=="1"){
                mCollectInfo.setSelected(true);
                collect=2;
            }else {
                collect=1;
            }
            String is_good = data.getIs_good();
            if(is_good=="1"){
                mGoodInfo.setSelected(true);
            }
        }
    }

    @Override
    public void setCollect() {
        if(collect==1){
            showToast("收藏成功");
            collect=2;
            mCollectInfo.setSelected(true);
        }else {
            showToast("取消收藏");
            collect=1;
            mCollectInfo.setSelected(false);
        }
    }

    @Override
    public void setLike() {
        mGoodInfo.setSelected(true);
    }

    @Override
    public void setAddIntegral() {
        showToast("积分加10");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_back:
                // TODO 20/05/08
                finish();
                break;
            case R.id.info_fx:
                // TODO 20/05/08
                break;
            case R.id.info_collect:
                // TODO 20/05/08
                collectOrCancelCollect();
                break;
            case R.id.info_good:
                likeOeancelLike();
                // TODO 20/05/08
                break;
            case R.id.info_search:
                // TODO 20/05/08
                break;
            default:
                break;
        }
    }

    private void likeOeancelLike() {
        if(LoginUtil.ifLogin()){
//            判断是点赞还是点踩，1赞，2踩
            pre.like(id,like,token);

        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LegonActivity.class);
            startActivity(intent);
        }
    }

    private void collectOrCancelCollect() {
        if(LoginUtil.ifLogin()){
            //判断是收藏还是取消收藏，1收藏，2取消收藏
            pre.collect(id,collect,token);
        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LegonActivity.class);
            startActivity(intent);
        }
    }

}

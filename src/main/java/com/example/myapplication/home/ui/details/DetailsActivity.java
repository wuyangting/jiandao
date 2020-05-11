package com.example.myapplication.home.ui.details;

import android.view.View;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.details.adapter.Details_Rec_Adapter;
import com.example.myapplication.home.ui.details.contract.DetailsContract;
import com.example.myapplication.home.ui.details.pre.DetailsPresenterImpl;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;

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

    @Override
    protected DetailsPresenterImpl initPre() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

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
    public void showToast(String msg) {

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
                break;
            case R.id.info_good:
                // TODO 20/05/08
                break;
            case R.id.info_search:
                // TODO 20/05/08
                break;
            default:
                break;
        }
    }

}

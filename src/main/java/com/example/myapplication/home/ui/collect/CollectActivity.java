package com.example.myapplication.home.ui.collect;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.collect.adapter.CollectPageAdapter;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.home.ui.collect.fragment.AllDataFragment;
import com.example.myapplication.home.ui.collect.presenter.CollectPreImpl;
import com.example.myapplication.utils.SpUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CollectActivity extends BaseActivity<CollectPreImpl> implements CollectContract.CollectView, View.OnClickListener {

    private ImageView mBack;
    private TextView mEdit;
    private TabLayout mTabCollect;
    private ViewPager mViewpageCollect;
    private String token;
    private int type=0;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private CollectPageAdapter adapter;
    private AllDataFragment allFragment;
    private AllDataFragment movieFragment;

    @Override
    protected CollectPreImpl initPre() {
        return new CollectPreImpl();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mEdit = (TextView) findViewById(R.id.edit);
        mEdit.setOnClickListener(this);
        mTabCollect = (TabLayout) findViewById(R.id.collect_tab);
        mViewpageCollect = (ViewPager) findViewById(R.id.collect_viewpage);
        initFragment();
        initTabs();
        adapter = new CollectPageAdapter(getSupportFragmentManager(), fragments, tabs);
        mTabCollect.setupWithViewPager(mViewpageCollect);
        mViewpageCollect.setAdapter(adapter);
        mTabCollect.setSelectedTabIndicator(0);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        //全部
        allFragment = new AllDataFragment();
        Bundle all = new Bundle();
        all.putInt("type",0);
        allFragment.setArguments(all);


        //视频
        movieFragment = new AllDataFragment();
        Bundle movie = new Bundle();
        movie.putInt("type",4);
        movieFragment.setArguments(movie);
        fragments.add(allFragment);
        fragments.add(movieFragment);
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add("全部");
        tabs.add("视频");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public void setData(CollectArticlDataBean data) {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 20/05/12
                break;
            case R.id.edit:
                // TODO 20/05/12
                break;
            default:
                break;
        }
    }
}

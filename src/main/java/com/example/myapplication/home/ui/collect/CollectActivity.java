package com.example.myapplication.home.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.ui.collect.adapter.CollectPageAdapter;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.home.ui.collect.fragment.AllDataFragment;
import com.example.myapplication.home.ui.collect.presenter.CollectPreImpl;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CollectActivity extends BaseActivity<CollectPreImpl> implements CollectContract.CollectView, View.OnClickListener {

    private ImageView mBack;
    private TextView mEdit;
    private TabLayout mTabCollect;
    private ViewPager mViewpageCollect;
    private String token;
    private int type = 0;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private CollectPageAdapter adapter;
    private AllDataFragment allFragment;
    private AllDataFragment movieFragment;
    private CheckBox mCheckAll;
    private TextView mCheckTextAll;
    private TextView mDelete;
    private boolean EDIT_TYPE = false;
    private ConstraintLayout mCon;

    @Override
    protected CollectPreImpl initPre() {
        return new CollectPreImpl();
    }

    @Override
    protected void initListener() {
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断用户是否按压
                if (buttonView.isPressed()) {
                    allFragment.setCheck(isChecked);
                    movieFragment.setCheck(isChecked);
                }
            }
        });
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
        mCheckAll = (CheckBox) findViewById(R.id.all_check);
        mCheckTextAll = (TextView) findViewById(R.id.all_check_text);
        mDelete = (TextView) findViewById(R.id.delete);
        mDelete.setOnClickListener(this);
        mCon = (ConstraintLayout) findViewById(R.id.con);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        //全部
        allFragment = new AllDataFragment();
        Bundle all = new Bundle();
        all.putInt("type", 0);
        allFragment.setArguments(all);


        //视频
        movieFragment = new AllDataFragment();
        Bundle movie = new Bundle();
        movie.putInt("type", 4);
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
                //修改状态
                updateType();
                //通知Fragment更换状态
                movieFragment.updateType(EDIT_TYPE);
                allFragment.updateType(EDIT_TYPE);
                //根据状态判断控件是否隐藏
                ifHint();
                break;
            case R.id.delete:// TODO 20/05/13
                deleteData();
                break;
            default:
                break;
        }
    }

    private void deleteData() {
        allFragment.notifiDelete();
        movieFragment.notifiDelete();
    }

    private void ifHint() {
        if (EDIT_TYPE) {
            mCheckAll.setVisibility(View.VISIBLE);
            mCheckTextAll.setVisibility(View.VISIBLE);
            mDelete.setVisibility(View.VISIBLE);
            mCon.setVisibility(View.VISIBLE);
        } else {
            mCheckAll.setVisibility(View.GONE);
            mCheckTextAll.setVisibility(View.GONE);
            mDelete.setVisibility(View.GONE);
            mCon.setVisibility(View.GONE);
        }
    }

    private void updateType() {

        if (EDIT_TYPE) {
            EDIT_TYPE = false;
            mEdit.setText(R.string.edit);
        } else {
            EDIT_TYPE = true;
            mEdit.setText(R.string.complete);
            mCheckAll.setChecked(false);
        }
    }

    public void notifiIsAllCheck(boolean isAll) {
        if(isAll){
            mCheckAll.setChecked(true);
        }else {
            mCheckAll.setChecked(false);
        }
    }
}

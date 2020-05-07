package com.example.myapplication.home.ui.recommend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.search.SearchActivity;
import com.example.myapplication.home.ui.recommend.adapter.RecommendPageAdapter;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.home.ui.recommend.contract.RecommendContract;
import com.example.myapplication.home.ui.recommend.fragment.RecommendAndFragment;
import com.example.myapplication.home.ui.recommend.presenter.RecommendPresenterImpl;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendPresenterImpl> implements RecommendContract.RecommendView {
    private TabLayout mTabRecommend;
    private ViewPager mPageRecommend;
    private ArrayList<Fragment> fragments;
    private RecommendPageAdapter adapter;
    private NavigationView mNavigation;
    private DrawerLayout mDraw;
    private Toolbar mTool;
    private MenuItem item;
    private MaterialSearchView mSearch;
    private ImageView mIm;
    private ArrayList<String> tabs;

    @Override
    protected void initListener() {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.action_search) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);

                }else if(itemId==R.id.commend){
                    mPageRecommend.setCurrentItem(0);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.strategy){
                    mPageRecommend.setCurrentItem(1);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.project){
                    mPageRecommend.setCurrentItem(2);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.road){
                    mPageRecommend.setCurrentItem(3);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.mechanical){
                    mPageRecommend.setCurrentItem(4);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.special){
                    mPageRecommend.setCurrentItem(5);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.editorial){
                    mPageRecommend.setCurrentItem(6);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.immediately){
                    mPageRecommend.setCurrentItem(7);
                    mDraw.closeDrawer(Gravity.LEFT);
                }else if(itemId==R.id.extend){
                    mPageRecommend.setCurrentItem(8);
                    mDraw.closeDrawer(Gravity.LEFT);
                }

                return false;
            }

        }

        );
    mIm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
    });
    }

    @Override
    protected void initData() {
        pre.getData();
    }

    @Override
    protected RecommendPresenterImpl initPre() {
        return new RecommendPresenterImpl();
    }

    @Override
    protected void initView(View inflate) {

        mTabRecommend = (TabLayout) inflate.findViewById(R.id.recommend_tab);
        mPageRecommend = (ViewPager) inflate.findViewById(R.id.recommend_page);
        mTabRecommend.setSelectedTabIndicator(0);
        mNavigation = (NavigationView) inflate.findViewById(R.id.navigation);
        mDraw = (DrawerLayout) inflate.findViewById(R.id.draw);
        mTool = (Toolbar) inflate.findViewById(R.id.tool);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), mDraw, mTool, R.string.open, R.string.close);
        mIm = (ImageView) inflate.findViewById(R.id.im);
        drawerToggle.syncState();
        mDraw.addDrawerListener(drawerToggle);
        mTool.setNavigationIcon(R.drawable.ic_home_top_logo);



    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void setData(TabBean tabBean) {
        List<TabBean.DataBean.ListBean> list = tabBean.getData().getList();
        fragments = new ArrayList<>();
        tabs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TabBean.DataBean.ListBean listBean = list.get(i);
            tabs.add(listBean.getName());
            RecommendAndFragment movieFragment = new RecommendAndFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Id", listBean.getId());
            movieFragment.setArguments(bundle);
            fragments.add(movieFragment);
        }
        adapter = new RecommendPageAdapter(getChildFragmentManager(), fragments, tabs);
        mPageRecommend.setOffscreenPageLimit(4);   //参数写多少就是加载多少页
        mPageRecommend.setAdapter(adapter);
        mTabRecommend.setupWithViewPager(mPageRecommend);
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = mTabRecommend.getTabAt(i);
            //设置自定义布局
            tab.setCustomView(getTabView(i));
        }
        setListener();
    }

    private void setListener() {
        mTabRecommend.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        GradientDrawable drawable = new GradientDrawable();
                        drawable.setCornerRadius(10);
                        drawable.setStroke(1, Color.parseColor("#D81B60"));
                        drawable.setColor( Color.parseColor("#D81B60"));
                        TextView customView = (TextView) tab.getCustomView();
                        customView.setBackground(drawable);
                        break;
                    case 1:
                        GradientDrawable drawable1 = new GradientDrawable();
                        drawable1.setCornerRadius(10);
                        drawable1.setStroke(1, Color.parseColor("#003372"));
                        drawable1.setColor( Color.parseColor("#003372"));
                        TextView customView1 = (TextView) tab.getCustomView();
                        customView1.setBackground(drawable1);
                        break;
                    case 2:
                        GradientDrawable drawable2 = new GradientDrawable();
                        drawable2.setCornerRadius(10);
                        drawable2.setStroke(1, Color.parseColor("#4A8950"));
                        drawable2.setColor( Color.parseColor("#4A8950"));
                        TextView customView2 = (TextView) tab.getCustomView();
                        customView2.setBackground(drawable2);
                        break;
                    case 3:
                        GradientDrawable drawable3 = new GradientDrawable();
                        drawable3.setCornerRadius(10);
                        drawable3.setStroke(1, Color.parseColor("#2883B0"));
                        drawable3.setColor( Color.parseColor("#2883B0"));
                        TextView customView3 = (TextView) tab.getCustomView();
                        customView3.setBackground(drawable3);
                        break;
                    case 4:
                        GradientDrawable drawable4 = new GradientDrawable();
                        drawable4.setCornerRadius(10);
                        drawable4.setStroke(1, Color.parseColor("#A18A6D"));
                        drawable4.setColor( Color.parseColor("#A18A6D"));
                        TextView customView4 = (TextView) tab.getCustomView();
                        customView4.setBackground(drawable4);
                        break;
                    case 5:
                        GradientDrawable drawable5 = new GradientDrawable();
                        drawable5.setCornerRadius(10);
                        drawable5.setStroke(1, Color.parseColor("#C85306"));
                        drawable5.setColor( Color.parseColor("#C85306"));
                        TextView customView5 = (TextView) tab.getCustomView();
                        customView5.setBackground(drawable5);
                        break;
                    case 6:
                        GradientDrawable drawable6 = new GradientDrawable();
                        drawable6.setCornerRadius(10);
                        drawable6.setStroke(1, Color.parseColor("#F6B051"));
                        drawable6.setColor( Color.parseColor("#F6B051"));
                        TextView customView6 = (TextView) tab.getCustomView();
                        customView6.setBackground(drawable6);
                        break;
                    case 7:
                        GradientDrawable drawable7 = new GradientDrawable();
                        drawable7.setCornerRadius(10);
                        drawable7.setStroke(1, Color.parseColor("#E03A2E"));
                        drawable7.setColor( Color.parseColor("#E03A2E"));
                        TextView customView7 = (TextView) tab.getCustomView();
                        customView7.setBackground(drawable7);
                        break;
                    case 8:
                        GradientDrawable drawable8 = new GradientDrawable();
                        drawable8.setCornerRadius(10);
                        drawable8.setStroke(1, Color.parseColor("#9149B4"));
                        drawable8.setColor( Color.parseColor("#9149B4"));
                        TextView customView8 = (TextView) tab.getCustomView();
                        customView8.setBackground(drawable8);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                GradientDrawable drawable = new GradientDrawable();
//                drawable.setStroke(1, Color.parseColor("#ffffff"));
//                drawable.setColor( Color.parseColor("#ffffff"));
                TextView customView = (TextView) tab.getCustomView();
                customView.setBackground(drawable);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getTabView(int i) {
        TextView textView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.text, null);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        textView.setText(tabs.get(i));
        if(i==0){
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(10);
            drawable.setStroke(1, Color.parseColor("#D81B60"));
            drawable.setColor( Color.parseColor("#D81B60"));
            textView.setBackground(drawable);
        }
        return textView;
    }

    @Override
    public void showToast(String msg) {

    }


}

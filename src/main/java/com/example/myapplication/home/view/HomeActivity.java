package com.example.myapplication.home.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.home.adapter.HomeViewPagerAdapter;
import com.example.myapplication.home.contract.HomeContract;
import com.example.myapplication.home.presenter.HomePresenterImpl;
import com.example.myapplication.home.ui.mine.MineFragment;
import com.example.myapplication.home.ui.movie.MovieFragment;
import com.example.myapplication.home.ui.recommend.RecommendFragment;
import com.example.myapplication.home.ui.special.SpecialFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class HomeActivity extends BaseActivity<HomePresenterImpl> implements HomeContract.MainView {

ArrayList<Integer> colors=new ArrayList<>();
    private long firstTime = 0;
    private ViewPager mPageHome;
    private TabLayout mTabHome;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabs;
    private HomeViewPagerAdapter pagerAdapter;
    private ArrayList<Integer> images;
    private NavigationView mNavigation;
    private DrawerLayout mDraw;
    private Toolbar mTool;
    private MenuItem item;

    @Override
    protected HomePresenterImpl initPre() {
        return new HomePresenterImpl();
    }

    @Override
    protected void initListener() {
//mTabHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        if(tab.getPosition()==2){
//
//        }
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//});
    }

    @Override
    protected void initData() {
//父类不能掉子类的方法
        pre.getData();
    }

    @Override
    protected void initView() {
        initFragments();
        initImages();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        initTabs();
        mPageHome = (ViewPager) findViewById(R.id.home_page);
        mTabHome = (TabLayout) findViewById(R.id.home_tab);


        mTabHome.setSelectedTabIndicator(0);
        pagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), tabs, fragments);
        mPageHome.setAdapter(pagerAdapter);
        mTabHome.setupWithViewPager(mPageHome);
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = mTabHome.getTabAt(i);
            //设置自定义布局
            tab.setCustomView(getTabView(i));
        }


    }

    //根据索引获取对应的tab的自定义view
    private View getTabView(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView tv = inflate.findViewById(R.id.tv);
        ImageView iv = inflate.findViewById(R.id.iv);
        tv.setText(tabs.get(position));
//tv.setBackgroundColor(colors.get(position));
        iv.setImageResource(images.get(position));
        return inflate;
    }

    private void initImages() {
        images = new ArrayList<>();
        images.add(R.drawable.recommend);
        images.add(R.drawable.movie);
        images.add(R.drawable.special);
        images.add(R.drawable.mine);
    }

    private void initTabs() {
        tabs = new ArrayList<>();
        tabs.add("推荐");
        tabs.add("视频");
        tabs.add("专题");
        tabs.add("我的");
    }

    private void initFragments() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new RecommendFragment());
        fragments.add(new MovieFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new MineFragment());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void setData() {

//设置数据 刷新页面
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            firstTime = secondTime;
            Toast.makeText(this, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        "释放资源停止视频播放"
        JCVideoPlayer.releaseAllVideos();
    }



}

package com.example.myapplication.home.ui.recommend.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.recommend.fragment.adapter.NewsBannerAdapter;
import com.example.myapplication.home.ui.recommend.fragment.adapter.RecommendAdapter;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.home.ui.recommend.fragment.contract.RecommendAndContract;
import com.example.myapplication.home.ui.recommend.fragment.presenter.RecommendPresenterFragment;
import com.example.myapplication.utils.Constans;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RecommendAndFragment extends BaseFragment<RecommendPresenterFragment> implements RecommendAndContract.RecommendAndView {
    private List<View> banner_views = new ArrayList<>();
    private RecyclerView mRecommendAdapterFragment;
    private RecommendAdapter adapter;
    private ViewPager mViewpageBanner;
    private Banner_Indicator mIndicatorBanner;
    private int viewpage_Current_Pos;
    private int current_banner_item;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String id = arguments.getString("Id");
        Log.i("Fragmentde1嵌套", "initData: " + id);
        pre.getData(id);
    }

    @Override
    protected RecommendPresenterFragment initPre() {
        return new RecommendPresenterFragment();
    }

    @Override
    protected void initView(View inflate) {
        mViewpageBanner = (ViewPager) inflate.findViewById(R.id.banner_viewpage);
        mIndicatorBanner = (Banner_Indicator) inflate.findViewById(R.id.banner_indicator);
        mRecommendAdapterFragment = (RecyclerView) inflate.findViewById(R.id.fragment_recommend_adapter);
        mRecommendAdapterFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecommendAdapterFragment.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        List<RecommendRecBean.ResultData> resultData = new ArrayList<>();
        adapter = new RecommendAdapter(resultData);

        mRecommendAdapterFragment.setAdapter(adapter);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend_and;
    }

    @Override
    public void setData(RecommendRecBean data2) {

        initBanner(data2);
        ArrayList<RecommendRecBean.ResultData> resultData = new ArrayList<>();

        //跑马灯文字数据
        StringBuffer taxts = new StringBuffer();
        List<RecommendRecBean.DataBean.FlashListBean> flash_list = data2.getData().getFlash_list();
        for (int i = 0; i < flash_list.size(); i++) {
            taxts.append(flash_list.get(i).getTheme() + "                  ");
        }
        RecommendRecBean.ResultData textBean = new RecommendRecBean.ResultData();
        textBean.data = taxts.toString();
        textBean.type = RecommendRecBean.ResultData.TYPE_TEXT;
        resultData.add(textBean);
//      文章列表
        for (int i = 0; i < data2.getData().getArticle_list().size(); i++) {
            RecommendRecBean.DataBean.ArticleListBean articleListBean = data2.getData().getArticle_list().get(i);
            RecommendRecBean.ResultData artical = new RecommendRecBean.ResultData();
            if (articleListBean.getColumn_name().equals(Constans.TE_XIE)) {
                artical.type = RecommendRecBean.ResultData.TYPE_TE_XIE;
            } else if (articleListBean.getView_type() == 4) {
                artical.type = RecommendRecBean.ResultData.TYPE_VIDEO;
            } else {
                artical.type = RecommendRecBean.ResultData.TYPE_LIST;
            }
            artical.data = articleListBean;
            resultData.add(artical);
//            Toast.makeText(getActivity(), articleListBean.getColumn_name(), Toast.LENGTH_SHORT).show();
        }
        adapter.addData(resultData);

        Log.d("数据", "setData: " + data2.toString());

    }

    private void initBanner(RecommendRecBean data2) {
        for (int i = 0; i <data2.getData().getBanner_list().size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(getContext()).inflate(R.layout.news_banner_item,null,false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage =  ban_view.findViewById(R.id.benner_image);
            bannerContent.setText(data2.getData().getBanner_list().get(i).getDescription());
            Glide.with(getContext()).load(data2.getData().getBanner_list().get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击了"+current_banner_item+"个view", Toast.LENGTH_SHORT).show();
                }
            });
        };

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        mViewpageBanner.setAdapter(bannerAdapter);

//        设置图片数量，总数
        mIndicatorBanner.setBannerImageSize(data2.getData().getBanner_list().size());
//        设置当前轮播图位置，默认0
        mIndicatorBanner.setCurrentBannerItem(0);

//        viewPage监听
        mViewpageBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
//                在监听过程中，更改指示器种轮播图得当前位置，重绘指示器
                mIndicatorBanner.setCurrentBannerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        倒计时
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpage_Current_Pos+=1;
                Log.e("TAG","当前位置"+viewpage_Current_Pos%(data2.getData().getBanner_list().size()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mViewpageBanner.setCurrentItem(viewpage_Current_Pos%(data2.getData().getBanner_list().size()));
                    }
                });
            }
        };
        timer.schedule(timerTask,2000,2000);
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        JCVideoPlayer.releaseAllVideos();
        super.setUserVisibleHint(isVisibleToUser);
    }
}

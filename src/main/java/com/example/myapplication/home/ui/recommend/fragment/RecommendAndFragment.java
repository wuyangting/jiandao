package com.example.myapplication.home.ui.recommend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.base.BaseLayFragment;
import com.example.myapplication.home.ui.details.DetailsActivity;
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

public class RecommendAndFragment extends BaseLayFragment<RecommendPresenterFragment> implements RecommendAndContract.RecommendAndView {
    private RecyclerView mRecommendAdapterFragment;
    private RecommendAdapter adapter;

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
        mRecommendAdapterFragment = (RecyclerView) inflate.findViewById(R.id.fragment_recommend_adapter);
        mRecommendAdapterFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecommendAdapterFragment.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        List<RecommendRecBean.ResultData> resultData = new ArrayList<>();
        adapter = new RecommendAdapter(resultData,getActivity());
        mRecommendAdapterFragment.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<RecommendRecBean.ResultData> data = (List<RecommendRecBean.ResultData>) adapter.getData();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                if(data.get(position).getItemType()== RecommendRecBean.ResultData.TYPE_LIST|| data.get(position).getItemType()==RecommendRecBean.ResultData.TYPE_TE_XIE||data.get(position).getItemType()==RecommendRecBean.ResultData.TYPE_VIDEO){
                    RecommendRecBean.DataBean.ArticleListBean listBean= (RecommendRecBean.DataBean.ArticleListBean) data.get(position).data;
                    intent.putExtra("link",listBean.getLink());
                    intent.putExtra("id",listBean.getId());
                    Toast.makeText(getActivity(), listBean.getId(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend_and;
    }

    @Override
    public void setData(RecommendRecBean data2) {

//        initBanner(data2);
        ArrayList<RecommendRecBean.ResultData> resultData = new ArrayList<>();

        List<RecommendRecBean.DataBean.BannerListBean> banner_list = data2.getData().getBanner_list();
        RecommendRecBean.ResultData banner = new RecommendRecBean.ResultData();
        banner.type= RecommendRecBean.ResultData.TYPE_BANNER;
        banner.data=banner_list;
resultData.add(banner);

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


    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void isCurrentVisibleToUser(boolean b) {
        if(adapter!=null){
            adapter.isCurrentVisibleToUser(b);
        }

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

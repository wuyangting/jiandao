package com.example.myapplication.home.ui.recommend.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.details.DetailsActivity;
import com.example.myapplication.home.ui.recommend.fragment.Banner_Indicator;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.tamsiree.rxui.view.RxRunTextView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RecommendAdapter extends BaseMultiItemQuickAdapter<RecommendRecBean.ResultData, BaseViewHolder> {
    private final Activity activity;
    private int viewpage_Current_Pos;
    private int current_banner_item;
    private List<View> banner_views=new ArrayList<>();
    private List<RecommendRecBean.DataBean.BannerListBean> data=new ArrayList<>();
    private ViewPager mViewpageBanner;
    private Timer timer;
    private TimerTask timerTask;

    public RecommendAdapter(List<RecommendRecBean.ResultData> data, Activity activity) {
        super(data);
        this.activity = activity;
        addItemType(RecommendRecBean.ResultData.TYPE_BANNER,R.layout.banner);
        addItemType(RecommendRecBean.ResultData.TYPE_LIST,R.layout.special_list);
        addItemType(RecommendRecBean.ResultData.TYPE_TE_XIE,R.layout.texie);
        addItemType(RecommendRecBean.ResultData.TYPE_TEXT,R.layout.scroll_text);
        addItemType(RecommendRecBean.ResultData.TYPE_VIDEO,R.layout.video_movie);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
//
        int itemViewType = baseViewHolder.getItemViewType();
        if(itemViewType== RecommendRecBean.ResultData.TYPE_LIST ||itemViewType==RecommendRecBean.ResultData.TYPE_TE_XIE){
            list(baseViewHolder,resultData);
        }else if(itemViewType== RecommendRecBean.ResultData.TYPE_TEXT){
            scrollText(baseViewHolder,resultData);
        }else if(itemViewType== RecommendRecBean.ResultData.TYPE_VIDEO){
            video(baseViewHolder,resultData);
        }else if(itemViewType== RecommendRecBean.ResultData.TYPE_BANNER){
            banner(baseViewHolder,resultData);
        }
    }

    private void video(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        RecommendRecBean.DataBean.ArticleListBean data= (RecommendRecBean.DataBean.ArticleListBean) resultData.data;
        JCVideoPlayer video = baseViewHolder.getView(R.id.video);
        baseViewHolder.setText(R.id.theme,data.getTheme());

        baseViewHolder.setText(R.id.small,data.getColumn_name());

        /**
         * 参数1：视频路径
         * 参数2：播放器类型
         * 参数3：视频标题  可为空
         */

        JCVideoPlayer jCVideoPlayer =baseViewHolder.getView(R.id.video);
        jCVideoPlayer.setUp(data.getVideo_url(),data.getTheme());
        Glide.with(getContext()).load(data.getImage_url()).into(jCVideoPlayer.ivThumb);
    }

    private void scrollText(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        RxRunTextView textView = baseViewHolder.getView(R.id.scroll_text);
        String data= (String) resultData.data;
//        SpannableString spannableString = new SpannableString("转点软件技术工作室技术分享11111111111111111111111111111111111111111111111111111111");
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(activity, "被点了", Toast.LENGTH_SHORT).show();
//                textView.setHighlightColor(activity.getResources().getColor(android.R.color.transparent));
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(activity.getResources().getColor(android.R.color.holo_red_dark));
//                ds.setUnderlineText(false);
//                ds.clearShadowLayer();
//
//            }
//        };
//        spannableString.setSpan(clickableSpan,0,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//
//        SpannableString spannableString1 = new SpannableString("222222222222222222222222222222222222222222222");
//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(activity, "被点了", Toast.LENGTH_SHORT).show();
//                textView.setHighlightColor(activity.getResources().getColor(android.R.color.transparent));
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(activity.getResources().getColor(android.R.color.holo_red_dark));
//                ds.setUnderlineText(false);
//                ds.clearShadowLayer();
//
//            }
//        };
//        spannableString1.setSpan(clickableSpan1,0,spannableString1.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setMovementMethod(LinkMovementMethod.getInstance()); //为TextView设置完Span后，别忘了setMovementMetho//
//
//        baseViewHolder.setText(R.id.scroll_text,spannableString+""+spannableString1);
        if(data.isEmpty()){
            textView.setVisibility(View.INVISIBLE);
        }else {
            textView.setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.scroll_text,data);
        }
    }

    private void list(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        RecommendRecBean.DataBean.ArticleListBean data= (RecommendRecBean.DataBean.ArticleListBean) resultData.data;
        baseViewHolder.setText(R.id.theme,data.getTheme());
        baseViewHolder.setText(R.id.small,data.getColumn_name());
        Glide.with(getContext()).load(data.getImage_url()).into((ImageView) baseViewHolder.getView(R.id.image));
    }

    private void banner(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        mViewpageBanner = baseViewHolder.findView(R.id.banner_viewpage);
        Banner_Indicator mIndicatorBanner = baseViewHolder.findView(R.id.banner_indicator);
        data = (List<RecommendRecBean.DataBean.BannerListBean>) resultData.data;
        for (int i = 0; i < data.size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(getContext()).inflate(R.layout.news_banner_item, null, false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage = ban_view.findViewById(R.id.benner_image);
            bannerContent.setText(data.get(i).getDescription());
            Glide.with(getContext()).load(data.get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            int finalI = i;
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetailsActivity.class);
                    String link = data.get(finalI).getLink();
                    intent.putExtra("link",link);
                    intent.putExtra("id",data.get(finalI).getId());
                    activity.startActivity(intent);
                }
            });
        }
        ;

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        mViewpageBanner.setAdapter(bannerAdapter);

//        设置图片数量，总数
        mIndicatorBanner.setBannerImageSize(data.size());
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
            initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpage_Current_Pos += 1;
                Log.e("TAG", "当前位置" + viewpage_Current_Pos % (data.size()));

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mViewpageBanner.setCurrentItem(viewpage_Current_Pos % (data.size()));
                    }
                });
            }
        };
        timer.schedule(timerTask, 2000, 2000);
    }

    /**
     * Fragment 是否处于可见状态
     * @param isVisible
     */
    public  void isCurrentVisibleToUser(boolean isVisible){

        if (isVisible){    Log.e("TAG","当前Framgnt可见看见");
//            timer.schedule(timerTask,2000,2000);
            if(data.size()>0){
                initTimer();
            }

        }else {
            Log.e("TAG","当前Framgnt不可见");
            if(timer!=null){
                timer.cancel();
            }
        }

    }
}

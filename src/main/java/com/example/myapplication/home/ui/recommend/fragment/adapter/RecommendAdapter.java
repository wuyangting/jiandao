package com.example.myapplication.home.ui.recommend.fragment.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class RecommendAdapter extends BaseMultiItemQuickAdapter<RecommendRecBean.ResultData, BaseViewHolder> {
    public RecommendAdapter(List<RecommendRecBean.ResultData> data) {
        super(data);
//        addItemType(RecommendRecBean.ResultData.TYPE_BANNER,R.layout.banner);
        addItemType(RecommendRecBean.ResultData.TYPE_LIST,R.layout.special_list);
        addItemType(RecommendRecBean.ResultData.TYPE_TE_XIE,R.layout.texie);
        addItemType(RecommendRecBean.ResultData.TYPE_TEXT,R.layout.scroll_text);
        addItemType(RecommendRecBean.ResultData.TYPE_VIDEO,R.layout.video_movie);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
//        banner(baseViewHolder,resultData);
        int itemViewType = baseViewHolder.getItemViewType();
        if(itemViewType== RecommendRecBean.ResultData.TYPE_LIST ||itemViewType==RecommendRecBean.ResultData.TYPE_TE_XIE){
            list(baseViewHolder,resultData);
        }else if(itemViewType== RecommendRecBean.ResultData.TYPE_TEXT){
            scrollText(baseViewHolder,resultData);
        }else if(itemViewType== RecommendRecBean.ResultData.TYPE_VIDEO){
            video(baseViewHolder,resultData);
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
//        boolean up = video.setUp(data.getVideo_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, data.getTheme());
//        Log.d("视频路径", "video: "+data.getVideo_url());
//        if (up) {
//            //展示播放器图片并充满
//            //<iframe frameborder="0" src="https://v.qq.com/txp/iframe/player.html?vid=g0874bjg03a" allowFullScreen="true"></iframe>
////            video.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            Glide.with(getContext()).load(data.getImage_url()).into(video.thumbImageView);
//        }
    }

    private void scrollText(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        TextView view = baseViewHolder.getView(R.id.scroll_text);
        String data= (String) resultData.data;
        if(data.isEmpty()){
            view.setVisibility(View.INVISIBLE);
        }else {
            view.setVisibility(View.VISIBLE);
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
        Banner banner = baseViewHolder.getView(R.id.banner);
       List<RecommendRecBean.DataBean.BannerListBean> data= (List<RecommendRecBean.DataBean.BannerListBean>) resultData.data;
        banner.setImages(data).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                RecommendRecBean.DataBean.BannerListBean image= (RecommendRecBean.DataBean.BannerListBean) path;
                Glide
                        .with(context).load(image.getImage_url()).into(imageView);
            }
        }).start();
    }
}

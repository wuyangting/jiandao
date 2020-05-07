package com.example.myapplication.home.ui.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.movie.bean.MovieBean;
import com.scwang.smartrefresh.header.FlyRefreshHeader;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MovieRecAdapter extends RecyclerView.Adapter {
    private ArrayList<MovieBean.DataBean.ListBean> data=new ArrayList<>();
    private Context context;
    private FlyRefreshHeader videoController;

    public MovieRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_movie, parent, false);
        return new VideoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieBean.DataBean.ListBean listBean = data.get(position);
        VideoViewHolder videoViewHolder= (VideoViewHolder) holder;
        videoViewHolder.small.setText(listBean.getColumn_name());
        videoViewHolder.theme.setText(listBean.getTheme());

        JCVideoPlayer jCVideoPlayer = videoViewHolder.jcVideoPlayerStandard;
        jCVideoPlayer.setUp(listBean.getVideo_url(),listBean.getTheme());
        Glide.with(context).load(listBean.getImage_url()).into(jCVideoPlayer.ivThumb);
//        jCVideoPlayer.ivThumb.setThumbInCustomProject("视频/MP3缩略图地址");

        //之后版本
//        boolean up = videoViewHolder.jcVideoPlayerStandard.setUp(listBean.getVideo_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, listBean.getTheme());
//        Log.d("视频路径", "video: "+listBean.getVideo_url());
//        if (up) {
//            //展示播放器图片并充满
//            //<iframe frameborder="0" src="https://v.qq.com/txp/iframe/player.html?vid=g0874bjg03a" allowFullScreen="true"></iframe>
////            video.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                //加载图片缩略图
//            Glide.with(context).load(listBean.getImage_url()).into(videoViewHolder.jcVideoPlayerStandard.thumbImageView);
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void addData(List<MovieBean.DataBean.ListBean> data1){
        this.data.addAll(data1);
        notifyDataSetChanged();
    }
    class VideoViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayer jcVideoPlayerStandard;
        TextView theme;
        TextView small;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            jcVideoPlayerStandard=itemView.findViewById(R.id.video);
            theme=itemView.findViewById(R.id.theme);
            small=itemView.findViewById(R.id.small);
        }
    }
}

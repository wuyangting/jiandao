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
import com.example.myapplication.home.ui.special.adapter.SpecialAdapter;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(listBean.getId(),listBean.getLink());
            }
        });
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
    SpecialAdapter.onClick onClick;

    public void setOnClick(SpecialAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick{
        void click(String id,String link);
    }
}

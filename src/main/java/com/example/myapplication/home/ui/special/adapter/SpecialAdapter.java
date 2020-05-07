package com.example.myapplication.home.ui.special.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter {
private ArrayList<String> banner=new ArrayList<>();
private ArrayList<SpecialBean.DataBean.ListBean> list=new ArrayList<>();
private Context context;

    public SpecialAdapter(Context context) {
        this.context = context;
    }

    private int TYPE_BANNER=0;
private int TYPE_LIST=1;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if(viewType==TYPE_BANNER){
//            View inflate = LayoutInflater.from(context).inflate(R.layout.banner, parent, false);
//            return new BannerViewHolder(inflate);
//        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.special_list, parent, false);
            return new ListViewholder(inflate);
//        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(position==0){
//            return TYPE_BANNER;
//        }else {
//            return TYPE_LIST;
//        }
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
//        if(getItemViewType(position)==TYPE_BANNER){
//            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
//
//            bannerViewHolder.banner1.setImages(banner).setImageLoader(new ImageLoader() {
//                @Override
//                public void displayImage(Context context, Object path, ImageView imageView) {
//               String data= (String) path;
//               Glide.with(context).load(data).into(imageView);
//                }
//            }).start();
//        }else {
//            if(banner.size()>0){
//                position=position-1;
//            }
            SpecialBean.DataBean.ListBean listBean = list.get(position);
            ListViewholder listViewholder= (ListViewholder) holder;
            listViewholder.theme.setText(listBean.getTheme());
            listViewholder.text.setText(listBean.getColumn_name());
            Glide.with(context).load(listBean.getImage_url()).into(listViewholder.image);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
public void addData(List<String> banner, List<SpecialBean.DataBean.ListBean> list){
        this.banner.addAll(banner);
        this.list.addAll(list);
        notifyDataSetChanged();
}

    class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner1;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner1=itemView.findViewById(R.id.banner);
        }
    }
    class ListViewholder extends RecyclerView.ViewHolder {
        TextView theme;
        TextView text;
        ImageView image;

        public ListViewholder(@NonNull View itemView) {
            super(itemView);
            theme=itemView.findViewById(R.id.theme);
            text=itemView.findViewById(R.id.small);
            image=itemView.findViewById(R.id.image);
        }
    }
}

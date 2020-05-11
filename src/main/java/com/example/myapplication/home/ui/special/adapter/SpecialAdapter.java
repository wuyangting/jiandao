package com.example.myapplication.home.ui.special.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.details.DetailsActivity;
import com.example.myapplication.home.ui.recommend.fragment.Banner_Indicator;
import com.example.myapplication.home.ui.recommend.fragment.adapter.NewsBannerAdapter;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.tamsiree.rxkit.RxTool.getContext;

public class SpecialAdapter extends RecyclerView.Adapter {
private ArrayList<SpecialBean.DataBean.BannerListBean> banner=new ArrayList<>();
private ArrayList<SpecialBean.DataBean.ListBean> list=new ArrayList<>();
private Context context;
    private Activity activity;
    private int current_banner_item;
    private List<View> banner_views=new ArrayList<>();
    private int viewpage_Current_Pos;

    public SpecialAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    private int TYPE_BANNER=0;
private int TYPE_LIST=1;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_BANNER){
            View inflate = LayoutInflater.from(context).inflate(R.layout.banner, parent, false);
            return new BannerViewHolder(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.special_list, parent, false);
            return new ListViewholder(inflate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_BANNER;
        }else {
            return TYPE_LIST;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();

        if(getItemViewType(position)==TYPE_BANNER){
            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            setBanner(bannerViewHolder,position);

        }else {
            if(banner.size()>0){
                position=position-1;
            }
            SpecialBean.DataBean.ListBean listBean = list.get(position);
            ListViewholder listViewholder= (ListViewholder) holder;
            listViewholder.theme.setText(listBean.getTheme());
            listViewholder.text.setText(listBean.getColumn_name());
            Glide.with(context).load(listBean.getImage_url()).into(listViewholder.image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.click(listBean.getId(),listBean.getLink());
                }
            });
        }

    }

    private void setBanner(BannerViewHolder bannerViewHolder, int position) {
        for (int i = 0; i < banner.size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(context).inflate(R.layout.news_banner_item, null, false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage = ban_view.findViewById(R.id.benner_image);
            bannerContent.setText(banner.get(i).getDescription());
            Glide.with(getContext()).load(banner.get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            int finalI = i;
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetailsActivity.class);
                    String link = banner.get(finalI).getLink();
                    intent.putExtra("link",link);
                    intent.putExtra("id",banner.get(finalI).getId());
                    activity.startActivity(intent);
                }
            });
        }
        ;

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        bannerViewHolder.mViewpageBanner.setAdapter(bannerAdapter);

//        设置图片数量，总数
        bannerViewHolder.mIndicatorBanner.setBannerImageSize(banner.size());
//        设置当前轮播图位置，默认0
//        bannerViewHolder.mIndicatorBanner.setCurrentBannerItem(0);

//        viewPage监听
        bannerViewHolder.mViewpageBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
//                在监听过程中，更改指示器种轮播图得当前位置，重绘指示器
                bannerViewHolder.mIndicatorBanner.setCurrentBannerItem(position);
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
                viewpage_Current_Pos += 1;
                Log.e("TAG", "当前位置" + viewpage_Current_Pos % (banner.size()));

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bannerViewHolder.mViewpageBanner.setCurrentItem(viewpage_Current_Pos % (banner.size()));
                    }
                });
            }
        };
        timer.schedule(timerTask, 2000, 2000);
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }
public void addData(List<SpecialBean.DataBean.BannerListBean> banner, List<SpecialBean.DataBean.ListBean> list){
        this.banner.addAll(banner);
        this.list.addAll(list);
        notifyDataSetChanged();
}

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ViewPager mViewpageBanner;
        Banner_Indicator mIndicatorBanner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
             mViewpageBanner = itemView.findViewById(R.id.banner_viewpage);
             mIndicatorBanner = itemView.findViewById(R.id.banner_indicator);
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
    onClick onClick;

    public void setOnClick(SpecialAdapter.onClick onClick) {
        this.onClick = onClick;
    }

   public interface onClick{
        void click(String id,String link);
    }
}

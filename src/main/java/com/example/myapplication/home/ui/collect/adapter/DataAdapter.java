package com.example.myapplication.home.ui.collect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<CollectArticlDataBean.DataBean.ListBean> data=new ArrayList<>();

    public DataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.collect_data, parent, false);

        return new DataViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataViewHolder dataViewHolder= (DataViewHolder) holder;
        CollectArticlDataBean.DataBean.ListBean listBean = data.get(position);

        dataViewHolder.title.setText(listBean.getTheme());
        dataViewHolder.time.setText(listBean.getTime());
        dataViewHolder.read_count.setText("阅读"+listBean.getRead_count());
        Glide.with(context).load(listBean.getImage_url()).into(dataViewHolder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = listBean.getLink();
                onClick.click(link,listBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(List<CollectArticlDataBean.DataBean.ListBean> list) {
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void addDataRefresh(List<CollectArticlDataBean.DataBean.ListBean> list) {
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView time;
        TextView read_count;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.collect_img);
            time=itemView.findViewById(R.id.collect_time);
            read_count=itemView.findViewById(R.id.read_count);
            title=itemView.findViewById(R.id.collect_title);
        }
    }
    onClick onClick;

    public void setOnClick(DataAdapter.onClick onClick) {
        this.onClick = onClick;
    }

    public interface onClick{
        void click(String link,String id);
    }
}

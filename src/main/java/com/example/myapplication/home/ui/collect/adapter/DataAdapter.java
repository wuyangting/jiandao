package com.example.myapplication.home.ui.collect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.collect.CollectActivity;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.bean.EvenBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<CollectArticlDataBean.DataBean.ListBean> data=new ArrayList<>();
    private boolean isEdit=false;
    private boolean isAll=true;
private int TYPE;
    public DataAdapter(Context context,int type) {
        this.context = context;
        this.TYPE=type;
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
        //判断控件是否隐藏
        if(isEdit){
            dataViewHolder.isDeleteCheck.setVisibility(View.VISIBLE);
            //显示的时候要根据是否删除设置是否选中
            if(listBean.isDelete()){
                dataViewHolder.isDeleteCheck.setChecked(true);
            }else {
                dataViewHolder.isDeleteCheck.setChecked(false);
            }
        }else {
            dataViewHolder.isDeleteCheck.setVisibility(View.GONE);
        }
        //设置CheckBox监听
        dataViewHolder.isDeleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isPressed()){
                    listBean.setDelete(isChecked);
                   CollectActivity activity= (CollectActivity) context;
                    activity.notifiIsAllCheck(askIsAll());
                    //通知完要恢复适配器中的默认值
                    huifu();
                }
            }
        });
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

    public void setType(boolean isEdit) {
        this.isEdit=isEdit;
        //刷新适配器
        if(isEdit){
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setDelete(false);
            }
        }
        notifyDataSetChanged();
    }

    public void setAllOrCheck(boolean isChecked) {
        if(isChecked){
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setDelete(true);
            }
        }else {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setDelete(false);
            }
        }
        notifyDataSetChanged();
    }

    //询问适配器是否全部选中
    public boolean askIsAll() {
        for (int i = 0; i < data.size(); i++) {
            if(!data.get(i).isDelete()){
                isAll=false;
            }
        }
        return isAll;
    }

    public void huifu() {
        isAll=true;
    }

    public ArrayList<CollectArticlDataBean.DataBean.ListBean> getData() {
        return data;
    }

    public void deleteData(CollectArticlDataBean.DataBean.ListBean listBean) {
        this.data.remove(listBean);
        notifyDataSetChanged();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView time;
        TextView read_count;
        CheckBox isDeleteCheck;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.collect_img);
            time=itemView.findViewById(R.id.collect_time);
            read_count=itemView.findViewById(R.id.read_count);
            title=itemView.findViewById(R.id.collect_title);
            isDeleteCheck=itemView.findViewById(R.id.is_delete);
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

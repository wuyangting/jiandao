package com.example.myapplication.home.search.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.dao.SearchBean;
import com.example.myapplication.home.search.bean.SearchResultBean;

import java.util.List;

public class SearchRecAdapter extends BaseQuickAdapter<SearchResultBean.DataBean.ListBean, BaseViewHolder> {
    public SearchRecAdapter(int layoutResId, List<SearchResultBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchResultBean.DataBean.ListBean listBean) {
        String theme = listBean.getTheme();
//        TextView tv = baseViewHolder.getView(R.id.theme);
//        theme.
//        String str="默认颜色<font color='#FF0000'>红颜色</font>";
//        tv.setText(Html.fromHtml(str));
        baseViewHolder.setText(R.id.theme,listBean.getTheme());
        baseViewHolder.setText(R.id.small,listBean.getColumn_name());
        Glide.with(getContext()).load(listBean.getImage_url()).into((ImageView) baseViewHolder.getView(R.id.image));
    }
}

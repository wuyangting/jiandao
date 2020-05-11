package com.example.myapplication.home.ui.details.adapter;

import android.view.View;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.home.ui.details.LollipopFixedWebView;
import com.example.myapplication.home.ui.recommend.fragment.bean.RecommendRecBean;

import java.util.List;

public class Details_Rec_Adapter  extends BaseMultiItemQuickAdapter<RecommendRecBean.ResultData, BaseViewHolder>{
    public Details_Rec_Adapter(List<RecommendRecBean.ResultData> data) {
        super(data);
        addItemType(0, R.layout.web);
        addItemType(1, R.layout.share);
        addItemType(2, R.layout.rec_articl);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        switch (baseViewHolder.getItemViewType()){
            case 0:
                web(baseViewHolder,resultData);
                break;
            case 1:
                break;
            case 2:
                rec(baseViewHolder,resultData);
                break;
        }
    }

    private void web(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        LollipopFixedWebView web = baseViewHolder.findView(R.id.web);
     String link= (String) resultData.data;
        web.loadUrl(link);
        web.setWebViewClient(new WebViewClient());
    }

    private void rec(BaseViewHolder baseViewHolder, RecommendRecBean.ResultData resultData) {
        RecyclerView rec = baseViewHolder.findView(R.id.rec_articl);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        Rec_In_Adapter rec_in_adapter = new Rec_In_Adapter(getContext());
        rec.setAdapter(rec_in_adapter);
    }
}

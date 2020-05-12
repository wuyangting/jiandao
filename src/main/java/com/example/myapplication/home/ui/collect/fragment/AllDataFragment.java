package com.example.myapplication.home.ui.collect.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.collect.adapter.DataAdapter;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.home.ui.collect.presenter.CollectPreImpl;
import com.example.myapplication.home.ui.details.DetailsActivity;
import com.example.myapplication.utils.SpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

public class AllDataFragment extends BaseFragment<CollectPreImpl> implements CollectContract.CollectView {

    private RecyclerView mRecCollect;
    private int type;
    private String token;
    private String start = "0";
    private String point_time = "0";
    private DataAdapter adapter;
    private SmartRefreshLayout mSmart;
    private int is_More=1;

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(is_More==1){
                    initData();
                }else {
                    showToast("没有数据了");
                }


            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start="0";
                point_time="0";
                initData();
            }
        });
adapter.setOnClick(new DataAdapter.onClick() {
    @Override
    public void click(String link, String id) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("link",link);
        intent.putExtra("id",id);
        startActivity(intent);
    }
});
    }

    @Override
    protected void initData() {
        pre.getData(type, start, point_time, token);
    }

    @Override
    protected CollectPreImpl initPre() {
        return new CollectPreImpl();
    }

    @Override
    protected void initView(View inflate) {

        mRecCollect = (RecyclerView) inflate.findViewById(R.id.collect_rec);
        mRecCollect.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecCollect.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mSmart = (SmartRefreshLayout) inflate.findViewById(R.id.smart);
        type = getArguments().getInt("type");
        token = (String) SpUtil.getParam("token", "");
        adapter = new DataAdapter(getActivity());
        mRecCollect.setAdapter(adapter);

    }

    @Override
    protected int getLayout() {
        return R.layout.collectdata;
    }

    @Override
    public void setData(CollectArticlDataBean data) {
        List<CollectArticlDataBean.DataBean.ListBean> list = data.getData().getList();

        if(start.equals("0")){
            adapter.addDataRefresh(list);
        }else {
            adapter.addData(list);
        }
        is_More=data.getData().getMore();
        start=data.getData().getStart();
//        showToast(start);
        point_time=data.getData().getPoint_time()+"";

        mSmart.finishLoadmore();
        mSmart.finishRefresh();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        start="0";
        point_time="0";
        initData();
    }
}

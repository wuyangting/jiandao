package com.example.myapplication.home.ui.special;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.details.DetailsActivity;
import com.example.myapplication.home.ui.special.adapter.SpecialAdapter;
import com.example.myapplication.home.ui.special.bean.SpecialBean;
import com.example.myapplication.home.ui.special.contract.SpecialContract;
import com.example.myapplication.home.ui.special.presenter.SpecialPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SpecialFragment extends BaseFragment<SpecialPresenterImpl> implements SpecialContract.SpecialView {
    private RecyclerView mRecSpecial;
    private SpecialAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        pre.getData();
    }

    @Override
    protected SpecialPresenterImpl initPre() {
        return new SpecialPresenterImpl();
    }

    @Override
    protected void initView(View inflate) {

        mRecSpecial = (RecyclerView) inflate.findViewById(R.id.special_rec);
        mRecSpecial.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<SpecialBean.ResultSpecial> resultSpecials = new ArrayList<>();
        adapter = new SpecialAdapter(getActivity(),getActivity());
        mRecSpecial.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mRecSpecial.setAdapter(adapter);
        adapter.setOnClick(new SpecialAdapter.onClick() {
            @Override
            public void click(String id, String link) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    public void setData(SpecialBean specialBean) {
        List<SpecialBean.DataBean.BannerListBean> banner_list = specialBean.getData().getBanner_list();
        adapter.addData(banner_list,specialBean.getData().getList());
    }

    @Override
    public void showToast(String msg) {

    }
}
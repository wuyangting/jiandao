package com.example.myapplication.home.ui.special;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
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
        adapter = new SpecialAdapter(getActivity());
        mRecSpecial.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mRecSpecial.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_special;
    }

    @Override
    public void setData(SpecialBean specialBean) {
        ArrayList<String> bannerList = new ArrayList<>();
        for (int i = 0; i < specialBean.getData().getBanner_list().size(); i++) {
            SpecialBean.DataBean.BannerListBean bannerListBean = specialBean.getData().getBanner_list().get(i);
            bannerList.add(bannerListBean.getImage_url());
        }
        adapter.addData(bannerList,specialBean.getData().getList());
    }

    @Override
    public void showToast(String msg) {

    }
}
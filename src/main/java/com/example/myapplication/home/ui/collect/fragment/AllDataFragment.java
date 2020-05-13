package com.example.myapplication.home.ui.collect.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.collect.CollectActivity;
import com.example.myapplication.home.ui.collect.adapter.DataAdapter;
import com.example.myapplication.home.ui.collect.bean.CollectArticlDataBean;
import com.example.myapplication.home.ui.collect.bean.EvenBean;
import com.example.myapplication.home.ui.collect.contract.CollectContract;
import com.example.myapplication.home.ui.collect.presenter.CollectPreImpl;
import com.example.myapplication.home.ui.details.DetailsActivity;
import com.example.myapplication.utils.SpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AllDataFragment extends BaseFragment<CollectPreImpl> implements CollectContract.CollectView {

    private RecyclerView mRecCollect;
    private int type;
    private String token;
    private String start = "0";
    private String point_time = "0";
    private DataAdapter adapter;
    private SmartRefreshLayout mSmart;
    private int is_More = 1;
    private boolean isEdit=false;
    private List<CollectArticlDataBean.DataBean.ListBean> list;
    private boolean isVisible;

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (is_More == 1) {
                    initData();
                } else {
                    showToast("没有数据了");
                }


            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                start = "0";
                point_time = "0";
                initData();
            }
        });
        adapter.setOnClick(new DataAdapter.onClick() {
            @Override
            public void click(String link, String id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("link", link);
                intent.putExtra("id", id);
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
        adapter = new DataAdapter(getActivity(),type);
        mRecCollect.setAdapter(adapter);
    }




    @Override
    protected int getLayout() {
        return R.layout.collectdata;
    }

    @Override
    public void setData(CollectArticlDataBean data) {
        list = data.getData().getList();

        if (start.equals("0")) {
            adapter.addDataRefresh(list);
        } else {
            adapter.addData(list);
        }
        is_More = data.getData().getMore();
        start = data.getData().getStart();
//        showToast(start);
        point_time = data.getData().getPoint_time() + "";

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
        start = "0";
        point_time = "0";
        initData();
    }


    public void updateType(boolean isEdit){
            this.isEdit=isEdit;
            adapter.setType(isEdit);

    }

    public void setCheck(boolean isChecked) {
        if(isVisible){
            adapter.setAllOrCheck(isChecked);
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible=isVisibleToUser;
        if(adapter!=null){
           boolean isAll=adapter.askIsAll();
           //通知v层是否选中CheckBox
            CollectActivity activity = (CollectActivity) getActivity();
            activity.notifiIsAllCheck(isAll);
            //通知完要恢复适配器中的默认值
            adapter.huifu();
        }
    }

    public void notifiDelete() {
        if(getUserVisibleHint()){
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList<CollectArticlDataBean.DataBean.ListBean> data = adapter.getData();
          boolean  have_Delete=false;
            for (int i = 0; i < data.size(); i++) {
                if(data.get(i).isDelete()){
                    stringBuffer.append(data.get(i).getCollect_id()+",");
                    adapter.deleteData(data.get(i));
                    have_Delete=true;
                    i--;
                }
            }
            if(have_Delete){
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
                pre.deleteCollect(stringBuffer,token);
            }else {
                showToast("请先勾选删除的文章");
            }

        }
    }
}

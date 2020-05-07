package com.example.myapplication.home.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.app.MyApplication;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.dao.SearchBean;
import com.example.myapplication.greendaodemo.db.SearchBeanDao;
import com.example.myapplication.home.search.adapter.SearchRecAdapter;
import com.example.myapplication.home.search.bean.SearchResultBean;
import com.example.myapplication.home.search.contract.SearchContract;
import com.example.myapplication.home.search.presenter.SearchPresenterImpl;
import com.example.myapplication.widget.FlowLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenterImpl> implements SearchContract.SearchView, View.OnClickListener {


    private ImageView mSearIm;
    private EditText mSearchEt;
    private TextView mDelete;
    private View mView;
    private TextView mHistoryDelete;
    private FlowLayout mFlowHistory;
    private RecyclerView mResultRecSearch;
    private ConstraintLayout mListHistory;
    private ConstraintLayout mListSearch;
    private SearchBeanDao searchBeanDao;
    private int start_position = 0;
    private int point_time = 0;
    private SearchRecAdapter adapter;
    private SmartRefreshLayout mSmart;
    private boolean isLoadMore=false;

    @Override
    protected SearchPresenterImpl initPre() {
        return new SearchPresenterImpl();
    }

    @Override
    protected void initListener() {
        //输入框监听
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文字改变监听
                if (TextUtils.isEmpty(s)) {
                    mListHistory.setVisibility(View.VISIBLE);
                    mListSearch.setVisibility(View.GONE);
                } else {
                    mListHistory.setVisibility(View.GONE);
                    mListSearch.setVisibility(View.VISIBLE);
                    adapter.getData().clear();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //删除数据库
        mHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });

        //点击FlowLayout自动填入关键字
//        mFlowHistory.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                TextView view= (TextView) v;
//                Toast.makeText(SearchActivity.this, view.getText(), Toast.LENGTH_SHORT).show();
//                mSearchEt.setText(view.getText());
//            }
//        });

        //上拉刷新，下拉加载的监听
        mSmart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isLoadMore=true;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isLoadMore=false;
                start_position=0;
                point_time=0;
                initData();
            }
        });
    }

    private void deleteAll() {
        searchBeanDao.deleteAll();
        updateDataBase();
    }

    @Override
    protected void initData() {
        String search_Result = mSearchEt.getText().toString().trim();
        pre.getData(search_Result, point_time, start_position);
    }

    @Override
    protected void initView() {
        mSearIm = (ImageView) findViewById(R.id.im_sear);
        mSearIm.setOnClickListener(this);
        mSearchEt = (EditText) findViewById(R.id.et_search);
        mDelete = (TextView) findViewById(R.id.delete);
        mDelete.setOnClickListener(this);
        mListHistory = (ConstraintLayout) findViewById(R.id.history_list);
        mListSearch = (ConstraintLayout) findViewById(R.id.search_list);
        mSmart = (SmartRefreshLayout) findViewById(R.id.smart);
        mView = (View) findViewById(R.id.view);
        mHistoryDelete = (TextView) findViewById(R.id.delete_history);
        mFlowHistory = (FlowLayout) findViewById(R.id.history_flow);
        mResultRecSearch = (RecyclerView) findViewById(R.id.search_result_rec);
        searchBeanDao = MyApplication.getsInstance().getDaoSession().getSearchBeanDao();
        mResultRecSearch.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<SearchResultBean.DataBean.ListBean> listBeans = new ArrayList<>();
        adapter = new SearchRecAdapter(R.layout.special_list, listBeans);
        mResultRecSearch.setAdapter(adapter);
        updateDataBase();

    }

    //1.Banner  2.视屏页面不走onBindViewHolder 3.NavigationView中item横向展示  4.节操视屏播放器无法播放但是可以加载
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setData(SearchResultBean search_result) {
        SearchResultBean.DataBean data = search_result.getData();
        point_time = data.getPoint_time();
        start_position = data.getStart();
        if(isLoadMore){
            adapter.addData(search_result.getData().getList());
        }else {
            adapter.getData().clear();
            adapter.addData(search_result.getData().getList());
        }
        mSmart.finishRefresh();
        mSmart.finishLoadmore();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_sear:
                // TODO 20/04/29
                search();
                break;
            case R.id.delete:
                // TODO 20/04/29
                delete();
                break;
            default:
                break;
        }
    }

    private void search() {
        isLoadMore=false;
        start_position = 0;
        point_time = 0;
        String result = mSearchEt.getText().toString().trim();
        if (TextUtils.isEmpty(result)) {
            Toast.makeText(this, "请先输入搜索内容", Toast.LENGTH_SHORT).show();
        } else {
            SearchBean searchBean = new SearchBean();
            searchBean.setKeyWord(result);
            searchBean.setTime(System.currentTimeMillis());
            searchBeanDao.insertOrReplace(searchBean);
            updateDataBase();
            initData();
        }
    }

    private void updateDataBase() {
        List<SearchBean> searchBeans = searchBeanDao.loadAll();
        //排序
        mFlowHistory.removeAllViews();
        if (searchBeans != null && searchBeans.size() > 0) {
            Collections.sort(searchBeans, new Comparator<SearchBean>() {
                @Override
                public int compare(SearchBean o1, SearchBean o2) {
                    //升序 时间小的在前面 返回大于零的数 这里是升序
                    //降序 返回小于零的数
                    return (int) (o2.getTime() - o1.getTime());
                }
            });
            //界面展示
            for (int i = 0; i < searchBeans.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_label, null);
                textView.setText(searchBeans.get(i).getKeyWord());
                mFlowHistory.addView(textView);
            }
        }

    }

    private void delete() {
        finish();
    }
}

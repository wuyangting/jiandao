package com.example.myapplication.home.search.presenter;

import android.app.SearchManager;

import com.example.myapplication.base.BasePre;
import com.example.myapplication.home.search.bean.SearchResultBean;
import com.example.myapplication.home.search.contract.SearchContract;
import com.example.myapplication.home.search.model.SearchModel;
import com.example.myapplication.home.ui.movie.contract.MovieContract;
import com.example.myapplication.net.INetCallback;
import com.example.myapplication.utils.Constans;

public class SearchPresenterImpl extends BasePre<SearchContract.SearchView> implements SearchContract.SearchPre {

    private final SearchModel searchModel;

    public SearchPresenterImpl() {
        searchModel = new SearchModel();
    }

    @Override
    public void getData() {

    }

    @Override
    public void callSearchData(String string) {

    }

    public void getData(String search_result, int point_time, int start) {
        searchModel.getData(search_result,point_time,start, new INetCallback<SearchResultBean>() {
            @Override
            public void onSuccess(SearchResultBean searchResultBean) {
                if(searchResultBean.getCode()== Constans.SUCCESS_CODE){
                    view.setData(searchResultBean);
                }

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}

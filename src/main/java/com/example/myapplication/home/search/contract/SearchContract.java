package com.example.myapplication.home.search.contract;

import com.example.myapplication.base.BaseView;
import com.example.myapplication.home.search.bean.SearchResultBean;
import com.example.myapplication.net.INetCallback;

public class SearchContract {
    public interface SearchView extends BaseView {
        void setData(SearchResultBean search_result);
    }

    public interface SearchModel{
        <T> void getData(INetCallback<T> netCallback);
        <T> void getData(String keyWord,INetCallback<T> netCallback);
    }
    public interface SearchPre {
        void getData();
        void callSearchData(String string);
    }
}

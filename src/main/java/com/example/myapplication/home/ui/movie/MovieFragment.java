package com.example.myapplication.home.ui.movie;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.home.ui.movie.adapter.MovieRecAdapter;
import com.example.myapplication.home.ui.movie.bean.MovieBean;
import com.example.myapplication.home.ui.movie.contract.MovieContract;
import com.example.myapplication.home.ui.movie.presenter.MoviePresenterImpl;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MovieFragment extends BaseFragment<MoviePresenterImpl> implements MovieContract.MovieView {
    private RecyclerView mMovierec;
    private MovieRecAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        pre.getData();
    }

    @Override
    protected MoviePresenterImpl initPre() {
        return new MoviePresenterImpl();
    }

    @Override
    protected void initView(View inflate) {

        mMovierec = (RecyclerView) inflate.findViewById(R.id.movierec);
        adapter = new MovieRecAdapter(getActivity());
        mMovierec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovierec.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mMovierec.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void setData(MovieBean movieBean) {
        adapter.addData(movieBean.getData().getList());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        JCVideoPlayer.releaseAllVideos();
        super.setUserVisibleHint(isVisibleToUser);
    }
}

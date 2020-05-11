package com.example.myapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseLayFragment<P extends BasePre> extends Fragment implements BaseView{
    public P pre;
    private boolean IS_VIEW_CREATED=false;
    private boolean IS_DATA_LOAD=false;

    //    获取焦点
    @Override
    public void onResume() {
        super.onResume();
        isCurrentVisibleToUser(true);
    }

    protected abstract void isCurrentVisibleToUser(boolean b);

    //    暂停--失去焦点
    @Override
    public void onPause() {
        super.onPause();
        isCurrentVisibleToUser(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(getLayout(),null);
        initView(inflate);
        IS_VIEW_CREATED=true;
        pre=initPre();
        if(pre!=null){
            pre.bindView(this);
        }
        initListener();
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            lazyLoad();

        }
        isCurrentVisibleToUser(isVisibleToUser);
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract P initPre();

    protected abstract void initView(View inflate);

    protected abstract int getLayout();
    private void lazyLoad(){
        if(getUserVisibleHint() && IS_VIEW_CREATED &&!IS_DATA_LOAD){
            initData();
            IS_DATA_LOAD=true;
        }
    }
}

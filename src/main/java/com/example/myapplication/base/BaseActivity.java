package com.example.myapplication.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public abstract class BaseActivity<P extends BasePre> extends AppCompatActivity implements BaseView{
    public P pre;
    //子类没有方法是会问父类要，子类不重写这个方法，就会调用父类里的方法
    //相同的代码抽到基类里面去
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //拿到P层对象
        pre=initPre();
        if(pre!=null){
            pre.bindView(this);
        }

        initView();
        initData();
        initListener();
    }

    protected abstract P initPre();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
}
package com.example.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.greendaodemo.db.DaoMaster;
import com.example.myapplication.greendaodemo.db.DaoSession;
import com.example.myapplication.utils.SpUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tamsiree.rxkit.RxActivityTool;
import com.tamsiree.rxkit.RxPermissionsTool;
import com.tamsiree.rxkit.RxTool;

public class MyApplication extends Application {
    public static Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;

    public static boolean isLogin=false;
    private DaoSession mDaoSession;
    private static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
sInstance=this;
        RxTool.init(this);
        initLeakCanary();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        setDatabase();
        //设置App版本号
        setVersion();
        //判断用户是否登陆
        setIsLogin();
    }

    private void setIsLogin() {
        isLogin= (boolean) SpUtil.getParam("isLogin",false);
    }

    private void setVersion() {
        SpUtil.setParam("version","1.1.1");
    }


    private void setDatabase() {
        //通过DaoMaster内部类DevOpenHelper可以获取一个SQLiteOpenHelper 对象
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        // 此处MyDb表示数据库名称 可以任意填写
        mHelper = new DaoMaster.DevOpenHelper(this, "MyDb.db", null);    // MyDb是数据库的名字，更具自己的情况修改
        SQLiteDatabase db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    //lifeCycle  Activity生命周期的回调
    ActivityLifecycleCallbacks activityLifecycleCallbacks=new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            AppManager.addActivity(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
                AppManager.removeActivity(activity);
        }
    };
//安装内存泄漏检测工具
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // 这个线程是专门给LeakCanary做堆内存分析的
            // 在这里不要写app初始化代码
            return;
        }
        LeakCanary.install(this);
        // 在这里写你app的初始化代码
    }
}
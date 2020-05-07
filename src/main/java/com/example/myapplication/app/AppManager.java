package com.example.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.os.Process;

import androidx.fragment.app.Fragment;

import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack=new Stack<>();
    private static Stack<Fragment> fragmentStack=new Stack<>();
    private static volatile AppManager appManager;
    private AppManager() {
    }

    public static AppManager getInstance() {
        if(appManager==null){
            synchronized (Application.class){
                if(appManager==null){
                    appManager=new AppManager();
                }
            }
        }
        return appManager;
    }
    public static  Stack<Activity> getActivityStack(){
        return activityStack;
    }

    public static  Stack<Fragment> getFragmentStack(){
        return fragmentStack;
    }
    public static void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    public static void addFragment(Fragment fragment){
        if(fragmentStack==null){
            fragmentStack=new Stack<Fragment>();
        }
        fragmentStack.add(fragment);
    }

    public static void removeActivity(Activity activity){
        if(activity!=null){
            if(!activity.isFinishing()){
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }
    public static void removeFragment(Fragment fragment){
        if(fragment!=null){
            fragmentStack.remove(fragment);
        }
    }
    public void deleteActivity(){
        Activity activity = activityStack.lastElement();
        if(activity!=null&&!activity.isFinishing()){
            activity.finish();
        }
    }
    public void appExit(){
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            activity.finish();
        }
        activityStack.clear();

        //退出应用的两个api
        System.exit(0);//0表示正常退出
        Process.killProcess(Process.myPid());//每个进程都有一个pid码，关闭pid码对应的进程
    }
}

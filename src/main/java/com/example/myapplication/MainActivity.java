package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity{
    //继承和泛型
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        Cl cl = new Cl();
        Class aClass = cl.getClass();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Log.i(TAG, "获取c1的第 "+i+"个方法，方法名是"+methods[i].getName());
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);
            try {
                int anInt = declaredField.getInt(cl);
                Log.i(TAG, "获取c1的第"+i+"+个属性,int值为"+anInt);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        ICallBack<String,Integer> iCallBack = new ICallBack<String,Integer>() {
            @Override
            public void onSuccess(String s) {

            }
        };
        Class aClass1 = iCallBack.getClass();
        Type[] genericInterfaces = aClass1.getGenericInterfaces();
        for (int i = 0; i < genericInterfaces.length; i++) {
            ParameterizedType type= (ParameterizedType) genericInterfaces[i];
            Log.i(TAG, "形式化参数" + ": "+
                    type.toString());
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (int j = 0; j < actualTypeArguments.length; j++) {
                String s = actualTypeArguments[j].toString();
                Log.i(TAG, "实际化参数" + ": "+ s);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(String is){
        Toast.makeText(this, is, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(int is){
        Toast.makeText(this, is+"", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.example.myapplication.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.myapplication.app.MyApplication;
import com.example.myapplication.home.ui.recommend.bean.TabBean;
import com.example.myapplication.net.api.NetApi;
import com.example.myapplication.net.api.URLConstants;
import com.example.myapplication.utils.Constans;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils implements INetWork{

    private final Retrofit retrofit;
    private final NetApi netApi;

    private RetrofitUtils() {
        //设置本地缓存文件
        File cacheFile = new File(Constans.PATH_CACHE);
        //设置缓存文件大小
        //1 M = 1024Kb = 1024 * 1024 byte
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        //设置缓存文件的大小  1 M=1024 Kb=1024 * 1024 byte
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(addNetHeaderInterceptor())
//                .addInterceptor(addCacheInterceptor())
                //设置连接超时重连
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        netApi = retrofit.create(NetApi.class);
    }
private static volatile RetrofitUtils retorfitUtils;
    public static RetrofitUtils getInstance() {
        if(retorfitUtils==null){
            synchronized (RetrofitUtils.class){
                if(retorfitUtils==null){
                    retorfitUtils=new RetrofitUtils();
                }
            }
        }
        return retorfitUtils;
    }

private Interceptor addNetHeaderInterceptor(){
        Interceptor headInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
//                        .header("Cache-Control", "no-cache")
//                        .header("sigan", "");//签名
                Request build = requestBuilder.build();
                return  chain.proceed(build);
            }
        };
        return headInterceptor;
}
private Interceptor addCacheInterceptor(){
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

        if(isNetWorkValid()){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
         }
            Response response = chain.proceed(request);
        if(isNetWorkValid()){
            //有网情况下，不使用缓存
            response.newBuilder().header("Cache-Control","public,max-age="+0)
                    //清除头信息，如果服务器不支持
                    .removeHeader("Retrofit")
                    .build();
        }else {
            //无网情况下，使用缓存
            int max_statle=60*60;
            response.newBuilder()
                    .header("Cache-Control","public,only-if-cached,max-stale="+max_statle)
                    .removeHeader("")
                    .build();
        }
        return response;
        }
    };
    return interceptor;
}
private boolean isNetWorkValid(){

    ConnectivityManager manager= (ConnectivityManager) MyApplication.context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    if(manager==null) {
        return false;
    }
    NetworkInfo info = manager.getActiveNetworkInfo();
        if(info==null||!info.isAvailable()){
        return false;
    }
    return true;

}

    @Override
    public <T> void get(String url, INetCallback<T> callback) {
        netApi.get(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callback.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments =( (ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type=actualTypeArguments[0];
                            Gson gson=new Gson();
                            T o = (T) gson.fromJson(string, TabBean.class);
                            Log.d("网络数据", "onNext: "+string);
                            callback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void get(String url, HashMap<String, String> map, INetCallback<T> callback) {
        netApi.get(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callback.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments =( (ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type=actualTypeArguments[0];
                            Gson gson=new Gson();
                            T o = (T) gson.fromJson(string, type);
                            Log.d("网络数据", "onNext: "+string);
                            callback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void post(String url, INetCallback<T> callback) {
        netApi.post(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callback.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments =( (ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type=actualTypeArguments[0];
                            Gson gson=new Gson();
                            T o = gson.fromJson(string, type);
                            callback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public <T> void post(String url, HashMap<String, String> map, INetCallback<T> callback) {
        netApi.post(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d("网络数据", "onNext: "+string);
                            Type[] genericInterfaces = callback.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments =( (ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type type=actualTypeArguments[0];
                            Gson gson=new Gson();
                            T o = gson.fromJson(string, type);
                            callback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
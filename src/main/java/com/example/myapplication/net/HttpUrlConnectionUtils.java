package com.example.myapplication.net;

import java.util.HashMap;

public class HttpUrlConnectionUtils implements INetWork{
    private HttpUrlConnectionUtils() {
    }
    private static volatile HttpUrlConnectionUtils httpUrlConnectionUtils;
    public static HttpUrlConnectionUtils getInstance() {
        if(httpUrlConnectionUtils==null){
            synchronized (HttpUrlConnectionUtils.class){
                if(httpUrlConnectionUtils==null){
                    httpUrlConnectionUtils=new HttpUrlConnectionUtils();
                }
            }
        }
        return httpUrlConnectionUtils;
    }

    @Override
    public <T> void get(String url, INetCallback<T> callback) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> map, INetCallback<T> callback) {

    }

    @Override
    public <T> void post(String url, INetCallback<T> callback) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> map, INetCallback<T> callback) {

    }
}

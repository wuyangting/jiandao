package com.example.myapplication.net;

import java.util.HashMap;

public interface INetWork {
    //Get请求
  <T>  void get(String url,INetCallback<T> callback);
    <T> void get(String url, HashMap<String,String> map,INetCallback<T> callback);

    //Post请求
    <T> void post(String url,INetCallback<T> callback);
    <T> void post(String url, HashMap<String,String> map,INetCallback<T> callback);
}

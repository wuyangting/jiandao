package com.example.myapplication.net;

public interface INetCallback<T> {
    void onSuccess(T t);
    void onError(Throwable throwable);
}

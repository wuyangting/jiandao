package com.example.myapplication.utils;

import com.example.myapplication.app.MyApplication;

public class LoginUtil {
    public static boolean ifLogin(){
        if(MyApplication.isLogin){
            return true;
        }else {
            return  false;
        }
    }
}

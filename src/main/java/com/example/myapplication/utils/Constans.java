package com.example.myapplication.utils;

import com.example.myapplication.app.MyApplication;

import java.io.File;

public class Constans {

    public static final int SUCCESS_CODE = 1;
    public static final  int REGIST = 2;
    public static final int LEGON = 3;
    public static final int LEGON_PASS = 4;
    public static final int CONFRIM_REGIST = 5;
    //网络缓存的地址
   static String PATH_DATA = MyApplication.context.getCacheDir().getAbsolutePath() +
            File.separator + "data";

   public static String PATH_CACHE = PATH_DATA + "/NetCache";
   public static String TE_XIE="特写";
}

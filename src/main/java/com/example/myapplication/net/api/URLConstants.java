package com.example.myapplication.net.api;

public class URLConstants {
public static String BASE_URL="https://www.seetao.com/";
    //推荐列表
   public static String RECOMMEND_LIST= "app/v_1_3/article/recommendlist";


//  视频列表
    public static  String VEDIO_LIST = "app/v_1_3/article/videolist";

    //    手机验证码登录接口
    public static String LOGIN = "/api/user/smslogin";

    //密码登陆接口
    public static String LOGIN_PASS= "/api/user/login";


    //    获取验证码接口
    public static String SENDVERIFIED = "/api/sms/sendsms";

    //    验证验证码是否正确
    public static String CHECKSMSCODE= "/api/sms/checksmscode";


    //   忘记密码
    public static String FORGET_PASS= "/app/v_1_1/user/savepassword";


    //    注册接口
    public static String REGIST= "/api/user/register";
}

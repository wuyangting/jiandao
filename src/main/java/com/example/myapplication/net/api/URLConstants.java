package com.example.myapplication.net.api;

public class URLConstants {
    public static final String COLLECT_ARTICL = "/api/article/usercollect";
    public static final String LIKE_ARTICL = "/api/article/userevaluate";
    public static final String GETARTICL_INFO = "/api/article/articleattribute";


    public static final String ADDINTEGRAL = "/api/article/readarticleaddintegral";

    //积分详情
    public static final String JIFEN_INFO = "/app/v_1_1/user/myintegral";

    //每日签到
    public static final String QIANDAO = "/app/v_1_1/user/checkinaddintegral";

    //收藏数据
    public static final String COLLECT_DATA = "/api/user/collect";
    //删除收藏
    public static final String DELETE_COLLECT = "/app/v_1_1/user/deletecollect";
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

  //   文章列表中推荐的文章列表
    public static String RECOMMEND_ARTICAL= "/api/article/articleaccess";


    //    注册接口
    public static String REGIST= "/api/user/register";
}

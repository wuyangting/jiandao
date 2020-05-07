package com.example.myapplication.net;

public class NetWorkFactory {
    private static int NET_TYPE=1;
    private INetWork iNetWork;
    private NetWorkFactory() {
    }
    private static volatile NetWorkFactory netWorkFactory;
    public static NetWorkFactory getInstance() {
        if(netWorkFactory==null){
            synchronized (NetWorkFactory.class){
                if(netWorkFactory==null){
                    netWorkFactory=new NetWorkFactory();
                }
            }
        }
        return netWorkFactory;
    }


    public  INetWork getNetWork(){
        if(NET_TYPE==1){
            iNetWork=RetrofitUtils.getInstance();
        }else {
            iNetWork=HttpUrlConnectionUtils.getInstance();
        }
        return iNetWork;
    }
}

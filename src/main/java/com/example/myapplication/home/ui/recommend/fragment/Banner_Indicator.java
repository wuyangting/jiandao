package com.example.myapplication.home.ui.recommend.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * 轮播图下方指示器
 */
public class Banner_Indicator extends View {

//    画笔
    private Paint paint;
    private Context context;

    //    banner图片数量，表示将宽分几分
    private int bannerImageSize;

//    当前现实轮播图得位置
    int currentItemPosion = 0;

//矩形，右下点得X轴
    private int rectf_right_defult = 0;
//获取到得屏幕宽度
   private int wind_Width;

    public Banner_Indicator(Context context) {
        this(context,null);
    }

    public Banner_Indicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

//    构造方法
    public Banner_Indicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        paint = new Paint();
//设置画笔颜色
        paint.setColor(getResources().getColor(R.color.color_RED));
//获取屏幕宽度
        getWindWidth();
    }

//    设置轮播图总数，主要判断屏幕分几分
    public void setBannerImageSize(int bannerImageSize){
        this.bannerImageSize = bannerImageSize;
    }



    public void setCurrentBannerItem(int currentItemPosion){
        this.currentItemPosion = currentItemPosion;
//        因为位置从0开始，但是屏幕份数从1开始，所以没个加1
        currentItemPosion = currentItemPosion+1;
//        屏幕宽度除以轮播图总数，乘以 当前轮播图位置，得到当前应占用屏幕多少宽度
        rectf_right_defult = wind_Width/bannerImageSize*currentItemPosion;
//        重新绘制  当前View
        invalidate();

    }

    public void getWindWidth(){
//        获取窗口管理对象
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wind_Width = wm.getDefaultDisplay().getWidth();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        设置一个矩形
        RectF rectF = new RectF();
        rectF.left = 0;
        rectF.top = 0;
//        动态获取右下点得X轴坐标
        rectF.right = rectf_right_defult;
        rectF.bottom = 10;
//        两个点确定一个矩形
//        绘制
        canvas.drawRect(rectF,paint);
    }


}

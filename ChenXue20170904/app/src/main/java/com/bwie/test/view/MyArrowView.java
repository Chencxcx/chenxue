package com.bwie.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/9/4
 */

public class MyArrowView extends View{

    private Paint paint;

    public MyArrowView(Context context) {
        super(context);
        initPaint();
    }

    public MyArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, 200, paint);
        invalidate();
    }

    public void initPaint() {
        paint = new Paint();
        //设置画笔样式
        paint.setStyle(Paint.Style.STROKE);
        //消除锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(Color.BLACK);
    }
}

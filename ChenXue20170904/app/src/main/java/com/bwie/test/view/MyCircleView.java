package com.bwie.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bwie.test.R;

/**
 * 1:类的用途   自定义控件  实现圆环加箭头
 * 2：@author Dell
 * 3：@date 2017/9/4
 */

public class MyCircleView extends View {

    private Paint paint;

    public MyCircleView(Context context) {
        super(context);
        initPaint();
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCircleView);
        float width = typedArray.getDimension(R.styleable.MyCircleView_circleWidth,0);
        int color = typedArray.getColor(R.styleable.MyCircleView_circleColor,0);
        setBackgroundColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        //画箭头
        drawAl(canvas,10, 10, center-110, center-110);
    }

    //初始化画笔
    public void initPaint() {
        paint = new Paint();
        //设置画笔样式
        paint.setStyle(Paint.Style.STROKE);
        //消除锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(Color.BLACK);
    }

    //画箭头的方法
    private void drawAl(Canvas canvas,int fx, int fy, int sx, int sy) {
        double h = 60;//箭头宽
        double l = 30;
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(l / h);//箭头角度
        double arraow_len = Math.sqrt(l * l + h * h);
        double[] arrxy_1 = rotatevec(sx - fx, sy - fy, awrad, true, arraow_len);
        double[] arrxy_2 = rotatevec(sx - fx, sy - fy, -awrad, true, arraow_len);
        double x_3 = sx - arrxy_1[0];
        double y_3 = sy - arrxy_1[1];
        double x_4 = sx - arrxy_2[0];
        double y_4 = sy - arrxy_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        //画圆
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, 200, paint);
        Path triangle = new Path();
        paint.setStyle(Paint.Style.FILL);
        triangle.moveTo(sx,sy);
        triangle.lineTo(x3,y3);
        triangle.lineTo(x4,y4);
        triangle.close();
        canvas.drawPath(triangle,paint);
    }

    //计算
    public double[] rotatevec(int px, int py, double ang, boolean isChlen, double newLen) {
        double mathstr[] = new double[2];
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = py * Math.cos(ang) + px * Math.sin(ang);
        if (isChlen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }
}

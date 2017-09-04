package com.bwie.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.bwie.test.R;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/9/4
 */

public class MyView extends RelativeLayout{

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.circle,null);
        View view1 = View.inflate(context, R.layout.arrow, null);
        addView(view);
        addView(view1);
    }

}

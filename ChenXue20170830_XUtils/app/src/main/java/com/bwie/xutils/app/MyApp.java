package com.bwie.xutils.app;

import android.app.Application;

import org.xutils.x;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/8/30
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化XUtils
        x.Ext.init(this);
    }
}

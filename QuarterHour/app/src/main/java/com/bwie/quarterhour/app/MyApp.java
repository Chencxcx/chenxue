package com.bwie.quarterhour.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/10/31
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}

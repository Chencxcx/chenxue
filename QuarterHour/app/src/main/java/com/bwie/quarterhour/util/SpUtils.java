package com.bwie.quarterhour.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/2
 */

public class SpUtils {
    private static SharedPreferences sharedPreferences;

    public SpUtils(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}

package com.bwie.quarterhour.util;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 1. 类的用途  单例 建造者 泛型  Retrofit(封装OK 动态代理，反射)
 * 2. @author forever
 * 3. @date 2017/11/1 09:00
 */


public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private RetrofitUtils(){

    }
    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }
    //得到Retrofit对象
     private static Retrofit retrofit;
    public static synchronized Retrofit getRetrofit(String url){
        //添加拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        //添加日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建OK
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5000, TimeUnit.SECONDS).readTimeout(5000,TimeUnit.SECONDS).retryOnConnectionFailure(false).build();
        if(retrofit==null){
         //创建Retrofit
            retrofit = new Retrofit.Builder().baseUrl(url).client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
    return retrofit;
    }
  //得到网络接口对象 不固定
    public static  <T>T getApiService(String url,Class<T> cl){
        Retrofit retrofit = getRetrofit(url);//得到retrofit

        return retrofit.create(cl);//返回的就是网络接口对象

    }
}

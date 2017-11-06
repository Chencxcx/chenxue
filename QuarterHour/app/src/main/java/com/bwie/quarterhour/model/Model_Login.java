package com.bwie.quarterhour.model;

import com.bwie.quarterhour.api.API;
import com.bwie.quarterhour.bean.LoginBean;
import com.bwie.quarterhour.bean.RegBean;
import com.bwie.quarterhour.inter.ApiServer;
import com.bwie.quarterhour.util.RetrofitUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/6
 */

public class Model_Login implements IModel_Login{

    @Override
    public Flowable postLogin(String userPassword, String userPhone) {
        ApiServer apiService = RetrofitUtils.getInstance().getApiService(API.BASEURI, ApiServer.class);
        Flowable<LoginBean> observable = apiService.postLogin(userPassword, userPhone);
        return observable;
    }

    @Override
    public Flowable postReg(String userPassword, String userPhone) {
        ApiServer apiService = RetrofitUtils.getInstance().getApiService(API.BASEURI, ApiServer.class);
        Flowable<RegBean> observable = apiService.postReg(userPassword, userPhone, "小毕毕", '男');
        return observable;
    }
}

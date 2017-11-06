package com.bwie.quarterhour.model;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/6
 */

public interface IModel_Login {
    Flowable postLogin(String userPassword, String userPhone);
    Flowable postReg(String userPassword, String userPhone);
}

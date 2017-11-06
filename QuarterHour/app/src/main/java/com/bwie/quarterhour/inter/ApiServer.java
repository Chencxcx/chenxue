package com.bwie.quarterhour.inter;

import com.bwie.quarterhour.bean.LoginBean;
import com.bwie.quarterhour.bean.RegBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/6
 */

public interface ApiServer {
    /*
    login :http://192.168.1.100/quarter/user/addLogin
     */
    @POST("quarter/user/addLogin")
    @FormUrlEncoded
    Flowable<LoginBean> postLogin(@Field("userPassword") String userPassword , @Field("userPhone") String userPhone);

    /*
    reg :http://192.168.1.100/quarter/user/addUser
     */
    @POST("quarter/user/addUser")
    @FormUrlEncoded
    Flowable<RegBean> postReg(@Field("userPassword") String userPassword , @Field("userPhone") String userPhone,@Field("userName") String userName,@Field("userSex") char userSex);
}

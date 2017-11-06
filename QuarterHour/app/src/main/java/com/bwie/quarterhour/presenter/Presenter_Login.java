package com.bwie.quarterhour.presenter;

import android.content.Context;

import com.bwie.quarterhour.bean.LoginBean;
import com.bwie.quarterhour.bean.RegBean;
import com.bwie.quarterhour.model.IModel_Login;
import com.bwie.quarterhour.model.Model_Login;
import com.bwie.quarterhour.util.SpUtils;
import com.bwie.quarterhour.view.IViewLogin;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/6
 */

public class Presenter_Login extends IPresenter<IViewLogin> {
    private IModel_Login model;

    public Presenter_Login() {
        model = new Model_Login();
    }

    public void postLogin(String userPassword, String userPhone, final Context context){
        Flowable<LoginBean> observable = model.postLogin(userPassword, userPhone);
        DisposableSubscriber<LoginBean> disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("500")) {
                            getView().error();
                        } else {
                            getView().loginSucc();
                            //保存成功的UID
                            SpUtils.getInstance(context).edit().putString("uid", loginBean.getUser().getUserId() + "").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addCompositeDisposable(disposable);
    }

    public void postReg(String userPassword,String userPhone){
        Flowable<RegBean> observable = model.postReg(userPassword, userPhone);
        DisposableSubscriber<RegBean> disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegBean>() {
                    @Override
                    public void onNext(RegBean regBean) {
                        if (regBean.getCode().equals("500")) {
                            getView().error();
                        } else {
                            getView().loginSucc();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addCompositeDisposable(disposable);
    }
}

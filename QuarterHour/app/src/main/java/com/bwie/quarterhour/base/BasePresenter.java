package com.bwie.quarterhour.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/5
 */

public class BasePresenter<V> {
    //弱引用
    private WeakReference<V> viewRef;
    //订阅管理器
    private CompositeDisposable compositeDisposable;

    //view与presenter的绑定
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    //view与presenter取消绑定
    public void detachView() {
        if (isAttach()) {//V与P绑定
            viewRef.clear();//清空
            viewRef = null;//置空
        }
    }

    //得到view实例
    public V getView() {
        //判断V与P是否绑定，绑定返回view，没有绑定返回null
        return isAttach() ? viewRef.get() : null;
    }

    //判断V与P是否绑定
    public boolean isAttach() {
        return viewRef != null && viewRef.get() != null;
    }

    //添加订阅管理器
    public void addCompositeDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    //取消订阅
    public void deleteCompositeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}

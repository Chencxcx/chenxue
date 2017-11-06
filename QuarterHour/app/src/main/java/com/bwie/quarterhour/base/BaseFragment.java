package com.bwie.quarterhour.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.quarterhour.presenter.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/10/31
 */

public abstract class BaseFragment<V,T extends IPresenter> extends Fragment {
    protected T mPresenter;
    protected View view;
    protected Context context;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null)
            mPresenter.attachView((V)this);
        initView();
        initData();
        onClick();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if ( mPresenter!= null){
            mPresenter.deleteCompositeDisposable();
            mPresenter.detachView();
        }
        bind.unbind();
    }

    protected abstract T getPresenter();

    protected abstract int getLayoutId();

    protected abstract void initData();
    protected abstract void initView();

    protected abstract void onClick();


    protected void toaskMsg(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

//    protected void showLoadingDialog() {
//        if (loadingDialog == null) {
//            loadingDialog = new LoadingDialog(context);
//        }
//        loadingDialog.show();
//    }

//    protected void dismissLoadingDialog() {
//        if (loadingDialog != null && loadingDialog.isShow()) {
//            loadingDialog.close();
//        }
//    }
}

package com.bwie.quarterhour.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/10/31
 */

public abstract class BaseActivity<V,P extends BasePresenter> extends AppCompatActivity{
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //得到Presenter的实例
        mPresenter = getPresenter();
        if (mPresenter != null){
            //绑定V与P
            mPresenter.attachView((V)this);
        }
        initView();
        initData();
        //沉浸状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑v与p
        if ( mPresenter!= null){
            mPresenter.deleteCompositeDisposable();
            mPresenter.detachView();
        }
    }
    //得到P层实例
    protected abstract P getPresenter();

    protected abstract int getLayoutId();

    public void initData() {

    }

    public void initView() {

    }

    public void click() {

    }

    public void intent() {

    }
}

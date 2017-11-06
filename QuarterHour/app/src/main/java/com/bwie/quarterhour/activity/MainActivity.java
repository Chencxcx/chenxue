package com.bwie.quarterhour.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.bwie.quarterhour.R;
import com.bwie.quarterhour.base.BaseActivity;
import com.bwie.quarterhour.base.BasePresenter;
import com.bwie.quarterhour.fragment.AnEpisodeFragment;
import com.bwie.quarterhour.fragment.RecommendFragment;
import com.bwie.quarterhour.fragment.VideoFragment;
import com.bwie.quarterhour.presenter.IPresenter;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent();
    }
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void intent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}

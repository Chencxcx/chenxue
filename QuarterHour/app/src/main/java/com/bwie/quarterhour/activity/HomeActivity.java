package com.bwie.quarterhour.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.quarterhour.R;
import com.bwie.quarterhour.base.BaseActivity;
import com.bwie.quarterhour.base.BasePresenter;
import com.bwie.quarterhour.fragment.AnEpisodeFragment;
import com.bwie.quarterhour.fragment.RecommendFragment;
import com.bwie.quarterhour.fragment.VideoFragment;
import com.bwie.quarterhour.presenter.IPresenter;
import com.bwie.quarterhour.util.SpUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

import cn.forward.androids.views.ShapeImageView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tj_title)
    TextView tjTitle;
    @BindView(R.id.editor)
    ImageView editor;
    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.nview)
    NavigationView nview;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.header)
    SimpleDraweeView header;
    private FragmentTabHost mTabHost;
    //定义一个布局
    private LayoutInflater layoutInflater;
    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {RecommendFragment.class, AnEpisodeFragment.class, VideoFragment.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_tj_btn, R.drawable.tab_dz_btn, R.drawable.tab_video_btn};
    //Tab选项卡的文字
    private String mTextviewArray[] = {"推荐", "段子", "视频"};
    private SimpleDraweeView head_pic;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //点击监听
        click();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    public void initView() {
        if (isLogin()){

        }else {

        }
        header.setImageURI("res://com.bwie.quarterhour/"+R.mipmap.head);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = nview.getLayoutParams();
        params.width = width * 4 / 5;
        nview.setLayoutParams(params);
        //设置tabhost
        setTabHost();
        //侧滑主布局随着移动
        dl.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                main.setX(slideOffset * drawerView.getWidth());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //侧滑头部
        setHeadView();
        //侧滑的条目点击
        setMenu();
    }

    //侧滑的条目点击
    private void setMenu() {
        nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_me:
                        Toast.makeText(HomeActivity.this, "关注", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friend:
                        Toast.makeText(HomeActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notification:
                        Toast.makeText(HomeActivity.this, "朋友", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_message:
                        Toast.makeText(HomeActivity.this, "消息", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    //设置tabhost
    private void setTabHost() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.i11);
        }

        //去掉分隔的竖线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("推荐")) {
                    tjTitle.setText("推荐");
                } else if (tabId.equals("段子")) {
                    tjTitle.setText("段子");
                } else if (tabId.equals("视频")) {
                    tjTitle.setText("视频");
                }
            }
        });
    }

    //侧滑头部
    private void setHeadView() {
        View headerView = nview.getHeaderView(0);
        head_pic = (SimpleDraweeView) headerView.findViewById(R.id.header_pic);
        head_pic.setImageURI("res://com.bwie.quarterhour/"+R.mipmap.head);
    }

    public void click() {
        header.setOnClickListener(this);
        head_pic.setOnClickListener(this);
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(mTextviewArray[index]);
        imageView.setImageResource(mImageViewArray[index]);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header:
                //侧滑滑出
                dl.openDrawer(nview);
                break;
            case R.id.header_pic:
                //判断是否登录
                if (isLogin()){

                }else {
                    intent();
                }
                break;
        }
    }

    //判断是否登录
    private boolean isLogin() {
        sharedPreferences = SpUtils.getInstance(this);
        boolean islogin = sharedPreferences.getBoolean("islogin", false);
        return islogin;
    }

    public void intent() {
        super.intent();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

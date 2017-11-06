package com.bwie.quarterhour.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.quarterhour.R;
import com.bwie.quarterhour.adapter.Adapter_RecommendVp;
import com.bwie.quarterhour.base.BaseFragment;
import com.bwie.quarterhour.presenter.IPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/10/31
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.r_tablayout)
    TabLayout rTablayout;
    @BindView(R.id.r_vp)
    ViewPager rVp;
    Unbinder unbinder;
    private List<String> titles;

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_recommend;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTabLayout();
        setViewPager();
    }

    @Override
    protected void onClick() {

    }
    //设置viewPager
    private void setViewPager() {
        List<Fragment> flist = new ArrayList<>();
        flist.add(new HotFragment());
        flist.add(new FollowFragment());
        rVp.setAdapter(new Adapter_RecommendVp(getFragmentManager(),flist,titles));
    }
    //设置tabLayout
    private void setTabLayout() {
        rTablayout.setTabMode(TabLayout.MODE_FIXED);
        titles = new ArrayList<>();
        titles.add("热门");
        titles.add("关注");
        for (int i = 0; i < titles.size(); i++) {
            rTablayout.addTab(rTablayout.newTab().setText(titles.get(i)));
        }
        rTablayout.setupWithViewPager(rVp);
    }
}

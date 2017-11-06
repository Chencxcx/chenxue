package com.bwie.quarterhour.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 1:类的用途
 * 2：@author Dell
 * 3：@date 2017/11/2
 */

public class Adapter_RecommendVp extends FragmentPagerAdapter{
    private List<Fragment> list;
    private List<String> titles;

    public Adapter_RecommendVp(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public Adapter_RecommendVp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

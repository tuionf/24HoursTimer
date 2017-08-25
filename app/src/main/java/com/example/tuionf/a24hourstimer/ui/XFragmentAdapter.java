package com.example.tuionf.a24hourstimer.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class XFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private String[] titles;

    public XFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);
    }

    public XFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);
        this.titles = titles;
    }

    public CharSequence getPageTitle(int position) {
        return (CharSequence)(this.titles != null && this.titles.length > position?this.titles[position]:super.getPageTitle(position));
    }

    public Fragment getItem(int position) {
        return (Fragment)this.fragmentList.get(position);
    }

    public int getCount() {
        return this.fragmentList.size();
    }
}


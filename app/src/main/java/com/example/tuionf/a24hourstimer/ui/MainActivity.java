package com.example.tuionf.a24hourstimer.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.tuionf.a24hourstimer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends XActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager1)
    ViewPager viewPager1;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    String[] titles = {"自动","番茄钟","手动"};
    XFragmentAdapter adapter = null;
    private static final String TAG = "MainActivity";

    @Override
    public void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        fragmentList.clear();
        fragmentList.add(AutoFragment.newInstance());
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(RecordFragment.newInstance());

        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }

        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(3);

        tabLayout.setupWithViewPager(viewPager1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Log.e(TAG, "onTabSelected: "+tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}

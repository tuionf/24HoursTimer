/*
 * *
 *  * *******************************************************
 *  *
 *  * @文件名称：HomeFragment.java
 *  * @文件作者：ouyangshengduo Copyright(c) 2017. All rights reserved.
 *  * @创建时间：17-3-21 下午10:11
 *  * @文件描述：
 *  * @修改历史：Last modified 17-3-21 下午10:11 ********************************************************
 *
 */

package com.example.tuionf.a24hourstimer.ui;

import android.os.Bundle;

import com.example.tuionf.a24hourstimer.R;

/**
 * HomeFragment
 */
public class RecordFragment extends XFragment {


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_record;
    }

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }
}

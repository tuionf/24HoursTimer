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
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tuionf.a24hourstimer.R;
import com.example.tuionf.a24hourstimer.util.Constant;
import com.example.tuionf.a24hourstimer.util.DateUtil;
import com.example.tuionf.a24hourstimer.util.RxJavaPomodoro;

import butterknife.BindView;


/**
 * HomeFragment
 */
public class HomeFragment extends XFragment implements View.OnClickListener{

    @BindView(R.id.count_btn) Button count_btn;

    private int countSec = Constant.countMin * 60;//5分钟
    private static final String TAG = "hhp";

    @Override
    public void initData(Bundle savedInstanceState) {
        Constant.isWork = true;
        Log.e(TAG, "initData: "+DateUtil.getSysDate());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setListener() {
        super.setListener();
        count_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.count_btn:
                if (Constant.isWork){
                    RxJavaPomodoro.count2(context,countSec,count_btn);
                }
                break;
            default:
                break;
        }
    }


}

package com.example.tuionf.a24hourstimer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tuionf.a24hourstimer.HistoryAdapter;
import com.example.tuionf.a24hourstimer.Pomodoro;
import com.example.tuionf.a24hourstimer.PomodoroHistory;
import com.example.tuionf.a24hourstimer.R;
import com.example.tuionf.a24hourstimer.util.DateUtil;

import java.util.ArrayList;

public class PomodoroHistoryActivity extends XActivity {

    private RecyclerView historyRecycler;
    private ArrayList<PomodoroHistory> mDataList;
    private BaseQuickAdapter historyAdapter = null;

    @Override
    public void initData(Bundle savedInstanceState) {
        initPomodoroHistoryData();
        initView();

        updateData();
    }

    private void updateData() {
        Intent intent = getIntent();
        Pomodoro pomodoro = (Pomodoro) intent.getSerializableExtra("pomodoro");

        for (int i = 0; i < mDataList.size(); i++) {

            if (DateUtil.getSysTimeDay().equals(mDataList.get(i).getDay())) {
                if (i+1 < mDataList.size() && mDataList.get(i+1).getPomodoro().isHasContent()){
                    //说明有内容
                    mDataList.add(new PomodoroHistory(pomodoro));
                }else {
                    mDataList.remove(i+1);
                    mDataList.add(new PomodoroHistory(pomodoro));
                }
            }
        }

        historyAdapter.notifyDataSetChanged();
    }

    private void initPomodoroHistoryData() {

//        PomodoroHistory pomodoroHistory = new PomodoroHistory(pomodoro, DateUtil.getSysTimeDay());

        mDataList = new ArrayList<>();
        mDataList.add(new PomodoroHistory(true,"一","08月18日","星期五"));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava1")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava11")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava111")));

        mDataList.add(new PomodoroHistory(true,"一一","08月19日","星期六"));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava2")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava12")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava112")));

        mDataList.add(new PomodoroHistory(true,"一一一","08月20日","星期天"));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava23")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava123")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava1123")));

        mDataList.add(new PomodoroHistory(true,"一","08月21日","星期一"));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava1")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava11")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava111")));

        mDataList.add(new PomodoroHistory(true,"一一","08月22日","星期二"));
        mDataList.add(new PomodoroHistory(new Pomodoro(false)));

        mDataList.add(new PomodoroHistory(true,"一一一","08月23日","星期三"));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Java","Rxjava23")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava123")));
        mDataList.add(new PomodoroHistory(new Pomodoro("06:00","07:00","Android","Rxjava1123")));

        mDataList.add(new PomodoroHistory(true,"一一","08月24日","星期四"));
        mDataList.add(new PomodoroHistory(new Pomodoro(false)));

        mDataList.add(new PomodoroHistory(true,"一一","08月25日","星期五"));
        mDataList.add(new PomodoroHistory(new Pomodoro(false)));

    }

    private void initView() {
        historyRecycler = (RecyclerView) findViewById(R.id.history_recycler);
        //设置布局管理器
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(R.layout.history_item_content,R.layout.history_item_font,mDataList);
        historyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //设置adapter
        historyRecycler.setAdapter(historyAdapter);
        //设置Item增加、移除动画
        historyRecycler.setItemAnimator(new DefaultItemAnimator());

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Pomodoro pomodoro = (Pomodoro) intent.getSerializableExtra("pomodoro");

                for (int i = 0; i < mDataList.size(); i++) {

                    if (DateUtil.getSysTimeDay().equals(mDataList.get(i).getDay())) {
                        if (i+1 < mDataList.size() && mDataList.get(i+1).getPomodoro().isHasContent()){
                            //说明有内容
                            mDataList.add(new PomodoroHistory(pomodoro));
                        }else {
                            mDataList.remove(i+1);
                            mDataList.add(new PomodoroHistory(pomodoro));
                        }
                    }
                }

                historyAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.pomodoro_history;
    }
}

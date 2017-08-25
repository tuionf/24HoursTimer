package com.example.tuionf.a24hourstimer;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by tuion on 2017/8/24.
 */

public class HistoryAdapter extends BaseSectionQuickAdapter<PomodoroHistory, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public HistoryAdapter(int layoutResId, int sectionHeadResId, List<PomodoroHistory> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PomodoroHistory item) {
        Pomodoro pomodoro = item.getPomodoro();

        if (item.isHeader) {
            helper.setText(R.id.day,item.getDay());
            helper.setText(R.id.weekDay,item.getWeekDay());
//            helper.setVisible(R.id.divide_line_ll,false);
        }else {
            if (!pomodoro.isHasContent()) {
                helper.setText(R.id.content,"暂无数据");
                helper.setVisible(R.id.start_end_time_ll,false);
                helper.setVisible(R.id.tag,false);
//                helper.setVisible(R.id.divide_line_ll,false);
            }else {
//                helper.setVisible(R.id.divide_line_ll,true);
                helper.setVisible(R.id.start_end_time_ll,true);
                helper.setVisible(R.id.tag,true);
                helper.setText(R.id.startTime,pomodoro.getStartTime());
                helper.setText(R.id.endTime,pomodoro.getEndTime());
                helper.setText(R.id.content,pomodoro.getContent());
                helper.setText(R.id.tag,pomodoro.getTag());
            }
        }
    }

    @Override
    protected void convertHead(BaseViewHolder helper, PomodoroHistory item) {
        helper.setText(R.id.day,item.getDay());
        helper.setText(R.id.weekDay,item.getWeekDay());
    }
}

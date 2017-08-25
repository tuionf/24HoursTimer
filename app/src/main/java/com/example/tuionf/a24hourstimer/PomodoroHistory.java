package com.example.tuionf.a24hourstimer;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by tuion on 2017/8/24.
 */

public class PomodoroHistory extends SectionEntity<Pomodoro> {

    private String day;
    private String weekDay;
    private Pomodoro pomodoro;


    public PomodoroHistory(boolean isHeader, String header, String day, String weekDay) {
        super(isHeader, header);
        this.day = day;
        this.weekDay = weekDay;
    }

    public PomodoroHistory(Pomodoro pomodoro) {
        super(pomodoro);
        setPomodoro(pomodoro);
    }

    public PomodoroHistory(Pomodoro pomodoro, String day) {
        super(pomodoro);
        this.day = day;
        this.pomodoro = pomodoro;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Pomodoro getPomodoro() {
        return t;
    }

    public void setPomodoro(Pomodoro pomodoro) {
        this.pomodoro = pomodoro;
    }
}

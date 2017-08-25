package com.example.tuionf.a24hourstimer;

import java.io.Serializable;

/**
 * Created by tuion on 2017/8/24.
 */

public class Pomodoro implements Serializable{

    private String startTime;
    private String endTime;
    private String tag;
    private String content;
    private boolean isHasContent = true;

    public Pomodoro(boolean isHasContent) {
        this.isHasContent = isHasContent;
    }

    public Pomodoro(String startTime, String endTime, String tag, String content) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tag = tag;
        this.content = content;
        this.isHasContent = true;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }
}

package com.example.tuionf.a24hourstimer.ui;

import android.os.Bundle;

/**
 * Created by wanglei on 2016/12/1.
 */

public interface UiCallback {
    void initData(Bundle savedInstanceState);

    void setListener();

    int getLayoutId();

    boolean useEventBus();
}

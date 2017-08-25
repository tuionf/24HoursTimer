package com.example.tuionf.a24hourstimer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tuionf.a24hourstimer.Pomodoro;
import com.example.tuionf.a24hourstimer.R;
import com.example.tuionf.a24hourstimer.util.Constant;

public class PomodoroSubmitActivity extends XActivity implements View.OnClickListener{

    private EditText pomodoro_name_et;
    private Button pomodoro_submit_btn;
    private static final String TAG = "PomodoroSubmitActivity";

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.pomodoro_submit_btn:
                submitPomodoro();
                break;
            default:
                break;
        }
    }

    private void submitPomodoro() {
        getTagAndContent(pomodoro_name_et.getText().toString());
        //跳转到历史记录页面
        Intent intent = new Intent(PomodoroSubmitActivity.this,PomodoroHistoryActivity.class);
        Pomodoro pomodoro = new Pomodoro(Constant.startPomoDate,Constant.endPomoDate,Constant.tag,Constant.content);
        intent.putExtra("pomodoro",pomodoro);
        startActivity(intent);
    }

    private void getTagAndContent(String pomodoro_str){
        int index = pomodoro_str.lastIndexOf("#");

        if (-1 != index) {
            Constant.tag = pomodoro_str.substring(index,pomodoro_str.length());
            Constant.content = pomodoro_str.substring(0,index);
        }else {
            Constant.tag = "";
            Constant.content = pomodoro_str;
        }

        Log.e(TAG, "getTagAndContent: "+Constant.tag +"---"+Constant.content );
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        pomodoro_submit_btn = (Button) findViewById(R.id.pomodoro_submit_btn);
        pomodoro_name_et = (EditText) findViewById(R.id.pomodoro_name_et);

        pomodoro_submit_btn.setOnClickListener(this);
        pomodoro_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    pomodoro_submit_btn.setBackgroundResource(R.mipmap.pomodoro_submit_false);
                }else {
                    pomodoro_submit_btn.setBackgroundResource(R.mipmap.pomodoro_submit_true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pomodoro_submit;
    }
}

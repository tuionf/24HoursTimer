package com.example.tuionf.a24hourstimer.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tuionf.a24hourstimer.ui.PomodoroSubmitActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.materialdialog.MaterialDialog;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;
import static com.example.tuionf.a24hourstimer.util.DateUtil.countToTime;

/**
 * Created by tuion on 2017/8/25.
 */

public class RxJavaPomodoro {

    public static Subscription mSubscription;
    public static MaterialDialog mMaterialDialog = null;

    public static void count2(final Activity context, final int countSec, final Button count_btn){
        Constant.isWork = false;
        Constant.startPomoDate = DateUtil.getSysDate1();
        Log.e(TAG, "count2: " + Constant.startPomoDate );
        Flowable<String> flowable = Flowable.interval(0,1, TimeUnit.SECONDS)
                .onBackpressureLatest()
                .take(countSec + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return new SimpleDateFormat("mm:ss").format((countSec - aLong) * 1000);
                    }
                });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                s.request(countSec + 1);  //注意这句代码
            }

            @Override
            public void onNext(String aLong) {
                Log.e(TAG, "onNext: "+aLong );
                count_btn.setText(aLong);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Constant.endPomoDate = DateUtil.getSysDate1();
                Log.e(TAG, "onComplete: " + Constant.endPomoDate+"---");
                count_btn.setText(countToTime());
                timeDowmDialog(context,0);
                mSubscription.cancel();
                Constant.isWork = true ;
            }
        };

        flowable.subscribe(subscriber);

    }

    public static void timeDowmDialog(final Activity context, int type) {
        String msg = "恭喜您完成一个番茄钟，确认提交？";
        String rightBtn = "提交";
        String leftBtn = "放弃";

        if (0 == type) {
            //0 表示番茄完成
            msg = "恭喜您完成一个番茄钟，确认提交？";
            rightBtn = "提交";
            leftBtn = "放弃";
        }else if (1 == type){
            //1 表示打算放弃番茄
            msg = "您正在进行一个番茄钟，确认放弃？";
            rightBtn = "放弃";
            leftBtn = "取消";
        }

        mMaterialDialog = new MaterialDialog(context)
//                .setTitle("提示")
                .setMessage(msg)
                .setPositiveButton(rightBtn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "rightBtn", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,PomodoroSubmitActivity.class);
                        context.startActivity(intent);
                        mMaterialDialog.dismiss();

                    }
                })
                .setNegativeButton(leftBtn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "leftBtn", Toast.LENGTH_SHORT).show();
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }
}

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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tuionf.a24hourstimer.R;
import com.example.tuionf.a24hourstimer.util.Constant;
import com.example.tuionf.a24hourstimer.util.DateUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.drakeet.materialdialog.MaterialDialog;


/**
 * HomeFragment
 */
public class HomeFragment extends XFragment implements View.OnClickListener{

    @BindView(R.id.count_btn) Button count_btn;
    private MaterialDialog mMaterialDialog = null;
    private int countMin = 1 ;//5分钟
    private int countSec = countMin * 60;//5分钟
    private boolean isClick = true;
    private static final String TAG = "hhp";
    private Subscription mSubscription;
    private Disposable disposable;
//    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void initData(Bundle savedInstanceState) {
//        count2();
//        count3();
        Log.e(TAG, "initData: "+DateUtil.getSysDate());
    }

    private void count3() {
        // create a flowable
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        // create
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        flowable.subscribe(subscriber);
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
                if (isClick){
                    count2();
                }
                break;
            default:
                break;
        }
    }

    private void count2(){
        isClick = false;
        Constant.startPomoDate = DateUtil.getSysDate1();
        Log.e(TAG, "count2: " + Constant.startPomoDate );
        Flowable<String> flowable = Flowable.interval(0,1,TimeUnit.SECONDS)
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
                timeDowmDialog();
                mSubscription.cancel();
                isClick = true ;
            }
        };

        flowable.subscribe(subscriber);

    }

    private void timeDowmDialog() {
        mMaterialDialog = new MaterialDialog(context)
//                .setTitle("提示")
                .setMessage("恭喜您完成一个番茄钟，确认提交？")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,PomodoroSubmitActivity.class);
                        startActivity(intent);
                        mMaterialDialog.dismiss();

                    }
                })
                .setNegativeButton("放弃", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "放弃", Toast.LENGTH_SHORT).show();
                        mMaterialDialog.dismiss();
                    }
                });

        mMaterialDialog.show();
    }

    private String countToTime(){
        String str = "";
        if (countMin > 0 && countMin < 10) {
            str = "0"+countMin+":00";
        }else if (countMin < 59 && countMin > 10){
            str = countMin+":00";
        }else {
            str = "59:00";
        }
        return str;
    }
}

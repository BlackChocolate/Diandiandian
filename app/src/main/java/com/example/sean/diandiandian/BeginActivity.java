package com.example.sean.diandiandian;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.sean.diandiandian.collector.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sean on 2015/5/21.
 */
public class BeginActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);setContentView(R.layout.begin_layout);
        final Intent it = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it);
            }
        };
        timer.schedule(task, 1000 * 4);
    }
}

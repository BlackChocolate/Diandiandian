package com.example.sean.diandiandian;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.sean.diandiandian.collector.BaseActivity;

/**
 * Created by sean on 2015/5/22.
 */
public class ThankActivity extends BaseActivity {
    private MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceStack){
        super.onCreate(savedInstanceStack);
        setContentView(R.layout.thank_layout);
        mediaPlayer=MediaPlayer.create(ThankActivity.this,R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
    }
}

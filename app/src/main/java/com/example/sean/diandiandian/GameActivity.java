package com.example.sean.diandiandian;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sean.diandiandian.collector.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sean on 2015/5/21.
 */
public class GameActivity extends BaseActivity {
    private TextView timetext;      //ʱ�䵹��ʱ�ı�
    private TextView scoretext;         //����
    private TextView highestscoretext;  //��ߵ÷�
    private int highestfenshu=0;

    private Button clickbutton;
    private String titletext;
    private MediaPlayer mediaPlayer;

    private int i=0;

    private int timecontrol=30*1000;

    private int fen=0;
    private int recLen = 30;
    Timer timer = new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.game_layout);

        mediaPlayer = MediaPlayer.create(this,R.raw.success);

        timetext = (TextView)findViewById(R.id.time_text);
        scoretext=(TextView)findViewById(R.id.score_text);
        highestscoretext=(TextView)findViewById(R.id.highest_score_text);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        highestfenshu = pref.getInt("highestfenshu", 0);
        highestscoretext.setText("Highest score: " + highestfenshu);


        timer.schedule(task, 1000, 1 * 1 * 50);

        clickbutton=(Button)findViewById(R.id.click_button);
        clickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fen++;
            }
        });

            //���ݶ�ȡ

    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timecontrol -= 50;
                    scoretext.setText("Score:" + fen);
                    if (timecontrol % 1000 == 0) {
                        recLen--;
                        timetext.setText("Time remaining:" + recLen + "s");
                    }
                    if (recLen < 1) {
                        timer.cancel();
                        mediaPlayer.start();
                        if (fen > highestfenshu) {
                            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                            editor.putInt("highestfenshu", fen);
                            editor.commit();
                            Toast.makeText(GameActivity.this, "You create the highest score!",
                                    Toast.LENGTH_SHORT).show();

                       }


                        if (fen < 150) {
                            titletext = "good";
                        }
                        if (fen > 200) {
                            titletext = "prefect";
                        } else {
                            titletext = "well done";
                        }
                        AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
                        dialog.setTitle(titletext);
                        dialog.setMessage("Your score is: " + fen);
                        dialog.setCancelable(false);
                        //����setCancelable(true)ʱ�����ProgressDialog
                        // ����������ʱ��ProgressDialog�ͻ�رգ���֮����setCancelable(false)ʱ��
                        // ���ProgressDialog��������򲻻�ر�ProgressDialog
                        dialog.setPositiveButton("share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_SUBJECT, "share");
                                intent.putExtra(Intent.EXTRA_TEXT,"hi,Are you OK? The game Diandiandian is so interesting.My score is "+fen+" ,Do you want to fight with me?Click here to download:http://7xiu5t.com1.z0.glb.clouddn.com/Diandiandian.apk");
                                startActivity(Intent.createChooser(intent, "share to"));//һ����������
                            }
                        });
                        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Deprecated
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        dialog.show();
                    }
                }
            });
        }
    };

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        timer.cancel();
    }

}

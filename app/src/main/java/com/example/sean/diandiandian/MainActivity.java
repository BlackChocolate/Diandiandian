package com.example.sean.diandiandian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.sean.diandiandian.collector.ActivityCollector;
import com.example.sean.diandiandian.collector.BaseActivity;


public class MainActivity extends BaseActivity {
    private Button startbutton;
    private Button thankbutton;
    private Button exitbutton;

    public static String[] data={"0","0","0","0","0","0","0","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);setContentView(R.layout.activity_main);


        startbutton =(Button)findViewById(R.id.start_button);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        thankbutton=(Button)findViewById(R.id.thank_button);
        thankbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThankActivity.class);
                startActivity(intent);
            }
        });


        exitbutton=(Button)findViewById(R.id.exit_button);
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        ActivityCollector.finishAll();
        finish();
    }
}

package com.example.sean.diandiandian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.sean.diandiandian.collector.ActivityCollector;
import com.example.sean.diandiandian.collector.BaseActivity;

import java.io.File;
import java.io.IOException;


public class MainActivity extends BaseActivity {
    private Button startbutton;
    private Button picbutton;
    private Button exitbutton;
    private Uri imageUri;


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

        picbutton=(Button)findViewById(R.id.picture_button);
        picbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage=new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                imageUri= Uri.fromFile(outputImage);
                Intent intent=new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("crop", true);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
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

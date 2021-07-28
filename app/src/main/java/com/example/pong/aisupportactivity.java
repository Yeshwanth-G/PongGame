package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class aisupportactivity extends AppCompatActivity {
com.example.pong.Aipaddle c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c1=new com.example.pong.Aipaddle(this);
        //setContentView(c1);
        int ar[]=new int[2];
        ar =this.getIntent().getIntArrayExtra("passed");
        setContentView(c1);
        c1.level=ar[1];
        if(ar[0]==0)
            c1.sound=false;
        else c1.sound=true;

    }
    @Override
    protected void onPause(){
        super.onPause();
        c1.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        c1.resume();
    }
}
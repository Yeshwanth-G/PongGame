package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class supportactivity extends AppCompatActivity {
canvaclass c;
public boolean soundact;
int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c=new canvaclass(this);
        setContentView(c);//soundact
               int ar[]=new int[2];
               ar =this.getIntent().getIntArrayExtra("passed");
        //a=getIntent().getIntExtra("level",1);
        c.level=ar[1];
        if(ar[0]==0)
            c.sound=false;
        else c.sound=true;
       /* if(c.newact==true)
        {
            Intent it=new Intent(supportactivity.this,finalact.class);
            it.putExtra("score",c.score);
            startActivity(it);
        }*/
    }
    @Override
    protected void onPause(){
        super.onPause();
        c.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        c.resume();
    }
}
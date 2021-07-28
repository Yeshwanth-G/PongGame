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
        //c1=new com.example.pong.Aipaddle(this);
        c= new canvaclass(this);
        //soundact
               int ar[]=new int[2];
               ar =this.getIntent().getIntArrayExtra("passed");
        setContentView(c);
        //a=getIntent().getIntExtra("level",1);

       /* if(c.newact==true)
        {
            Intent it=new Intent(supportactivity.this,finalact.class);
            it.putExtra("score",c.score);
            startActivity(it);
        }*/
            c.level=ar[1];
            if(ar[0]==0)
                c.sound=false;
            else c.sound=true;

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
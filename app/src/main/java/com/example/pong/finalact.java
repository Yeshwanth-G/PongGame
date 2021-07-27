package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalact extends AppCompatActivity {
TextView t,ts;
Button b;
int highscore,score;
public static final String save="save";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalact);
         score=getIntent().getIntExtra("score",0);
        t=(TextView)findViewById(R.id.scorer);
        ts=(TextView)findViewById(R.id.highscore) ;
        b=(Button)findViewById(R.id.retry) ;
        t.setText("Your Score is:"+score);
        load();
        /*if(score>highscore) highscore=score;
        store(highscore);
        ts.setText("HighScore:"+highscore);*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(finalact.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent it=new Intent(finalact.this,MainActivity.class);
        startActivity(it);
        finish();
    }

    private void store(int highscore) {
        SharedPreferences sharedPreferences=getSharedPreferences(save,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(save,highscore);
        editor.apply();
    }
    public void load(){
        SharedPreferences sharedPreferences=getSharedPreferences(save,MODE_PRIVATE);
        highscore=sharedPreferences.getInt(save,score);
        update();
    }
    public void update(){
        if(score>=highscore) highscore=score;
        ts.setText("HighScore:"+highscore);
        store(highscore);
    }
}
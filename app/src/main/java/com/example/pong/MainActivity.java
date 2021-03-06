package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Button b;
String text,text1;
ImageButton sound;
boolean Sound=true;
Spinner s,s1;
int a,a1;
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button) findViewById(R.id.button);
        sound=(ImageButton) findViewById(R.id.imageButton);
        s=(Spinner) findViewById(R.id.spinner);
        s1=(Spinner)findViewById(R.id.spinnermode) ;
      // t=(TextView)findViewById(R.id.textView3) ;
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Mode, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(this);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Sound==true)
                    //sound.setBackgroundResource(getResources().getDrawable(R.drawable.ic_action_name))
                {
                    //(getResources().getDrawable(R.drawable.ic_action_name));
                    Sound=false;
                    sound.setBackground(getResources().getDrawable(R.drawable.ic_action_name));
                }
                else
                {
                    sound.setBackground(getResources().getDrawable(R.drawable.soundon));
                    Sound=true;
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it;
                if(a1==1)
                { it=new Intent(MainActivity.this,supportactivity.class);
                //it.putExtra("sound",Sound);
                int s[]=new int[2];
                if(Sound==true)
                s[0]=1;
                else s[0]=0;
                    s[1]=a;
                it.putExtra("passed",s);
                startActivity(it);}
                if(a1==2){
                    it=new Intent(MainActivity.this,aisupportactivity.class);
                //it.putExtra("sound",Sound);
                int s[]=new int[2];
                if(Sound==true)
                    s[0]=1;
                else s[0]=0;
                s[1]=a;
                it.putExtra("passed",s);
                startActivity(it);}

            }
        });
    }
    @Override
    public  void  onBackPressed()
    {
        finishAffinity();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinner)
        {
            text = parent.getItemAtPosition(position).toString();
            if (text.equals("Hard"))
                a = 2;
            if (text.equals("Easy")) a = 1;
            // t.setText(text+a);
        }
        if(parent.getId()==R.id.spinnermode){
            text1 = parent.getItemAtPosition(position).toString();
            if (text1.equals("Computer Mode"))
                a1 = 2;
            if (text1.equals("Normal Mode")) a1 = 1;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
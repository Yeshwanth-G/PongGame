
package com.example.pong;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

import com.example.pong.R;

import static android.content.Intent.getIntent;
import static android.os.SystemClock.sleep;

public class Aipaddle extends SurfaceView implements Runnable {
    Thread t=null;
    Context context;
    boolean CanDraw=false;
    Paint whitestroke,greenstroke,blackstroke,redstroke,greenstroke1;
    Bitmap b,bt;
    Path pad;
    Path padai;
    int aix,aispeedp=15,aispeedn=15;
    int aiy,status;
    public boolean sound;
    Canvas canvas;
    int circlex,circley,circler,speedx=1,speedy=1,padx,pady,score=0,i,level;
    float tx=200,ty=200;
    MediaPlayer m=new MediaPlayer(),m1=new MediaPlayer();
    SurfaceHolder surfaceholder;
    public Aipaddle(Context context) {
        super(context);
        this.context=context;
        surfaceholder=getHolder();
        b= BitmapFactory.decodeResource(getResources(),R.drawable.nytback);

        preppaint();
        circlex=200;circley=200;
        circler=25;
        score=0;
        padx=200;
        aix=200;
        aiy=100;
        m=MediaPlayer.create(context,R.raw.audio2);
        m1=MediaPlayer.create(context,R.raw.gameover);
   }

    @Override
    public void run() {
        while(CanDraw){
            if(!surfaceholder.getSurface().isValid())
                continue;
            canvas= surfaceholder.lockCanvas();
            bt=Bitmap.createScaledBitmap(b,getWidth(),getHeight()-100,true);
            canvas.drawColor(Color.YELLOW);
            canvas.drawBitmap(bt,0,100,null);
            motioncircle(15);
            pady=getHeight()-40;
            motionpad();
            movepadai();
            drawpad(padx,pady);//ircley
            darwpadai(aix,aiy);
            canvas.drawCircle(circlex,circley,circler,whitestroke);
            if(i==0)
            {score=0;
            }
            canvas.drawText("Score: "+score,getWidth()/2-100,60,blackstroke);
            surfaceholder.unlockCanvasAndPost(canvas);
            i++;
        }
    }

    private void movepadai() {
        if (score >= 5 && score <= 7) aispeedn -= 5;
        if (score >= 20 && score <= 24) aispeedn -= 5;
        if ((score!=0)&&(score % 10 == 0 || score % 15 == 0)) aispeedn -=5;
        if (circlex > aix+130) {
            aix += aispeedp;
            /*if ((aix + 130) + aispeedp > getWidth())
                aix = aix - aispeedp;*/
        }
        if (circlex < aix-20)
        { aix -= aispeedn;
       /* if (((aix - 20) - aispeedn < 0))
            aix = aix + aispeedn;*/
    }
        if((circlex>=aix-20)&&(circlex<=aix+130));



    }


    public void motionpad() {
        if(tx>padx+150)
        {
            padx+=15;
        }
        if(tx<padx)
            padx=padx-15;
        if((tx>=padx)&&tx<=(padx+150));
    }



    private void motioncircle(int speed) {
        // if((level==2)&&(score>2))
        //  speed=speed*level;
        if((circlex+speedx>getWidth()-circler)||circlex+speedx<circler)
        {
            speedx=-speedx;
            if(sound==true)
            {
                m.setLooping(false);
                m.start();}
            // sleep(500);// m.stop();
        }
        if((circley+speedy>pady-circler)&&(circlex>=padx)&&(circlex<=padx+150)&&(circley<getHeight()-circler))
        {
            speedy=-speedy;
     score++;
            if(sound==true)
            {m.setLooping(false);
                m.start();}
           /* {if(m.isPlaying()) m.stop();
            m.setLooping(false);
            m.start();}*/
        }
        if(circley>getHeight())
        {
            canvas.drawText("You Lost!!",getWidth()/2-130,getHeight()/2-20,redstroke);
            status=0;
            if(sound==true)
            { m.stop();
                m1.start();
                sleep(800);
                m1.stop();}
            int [] s={status,score};
            Intent it=new Intent(context,finalactc.class);
            it.putExtra("score",s);
            context.startActivity(it);
            ((aisupportactivity)context).finish();

        }
        if(circley<100)
        {
            canvas.drawText("YOU WON!!",getWidth()/2-130,getHeight()/2-20,greenstroke1);
            status=1;
            if(sound==true)
            { m.stop();
                m1.start();
                sleep(800);
                m1.stop();}
            Intent it=new Intent(context,finalactc.class);
            int [] s={status,score};
            it.putExtra("score",s);
            context.startActivity(it);
            ((aisupportactivity)context).finish();}
        if((circley+speedy<circler+100)&&(circlex>=aix-20)&&(circlex<=aix+130)&&(circley+100>circler))
        {
            //score++;
            speedy=-speedy;
            if(sound==true)
            {m.setLooping(false);
                m.start();}
            /*{ if(m.isPlaying()) m.stop();
            m.start();}*/
            // sleep(500); m.stop();
        }
        if(score>10)speed=speed*level;
        circlex+=(speedx)*speed;
        circley+=(speedy)*speed;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                tx=event.getX();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        return true;
    }
    public void pause() {
        CanDraw=false;
        while(true){
            try {
                t.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=null;
        //m.release();
        //m1.release();
    }

    public void resume() {
        CanDraw=true;
        t=new Thread(this);
        t.start();
    }
    public void preppaint(){
        Typeface t= ResourcesCompat.getFont(context, R.font.fontruss);
        whitestroke=new Paint();
        whitestroke.setColor(Color.WHITE);
        whitestroke.setStyle(Paint.Style.FILL);
        blackstroke=new Paint();
        blackstroke.setColor(Color.BLACK);
        blackstroke.setStyle(Paint.Style.FILL_AND_STROKE);
        blackstroke.setTextSize(50);
        redstroke=new Paint();
        redstroke.setColor(Color.RED);
        redstroke.setStyle(Paint.Style.FILL_AND_STROKE);
        redstroke.setTextSize(50);
        blackstroke.setTypeface(t);
        greenstroke=new Paint();
        greenstroke.setColor(Color.GREEN);
        greenstroke.setStyle(Paint.Style.FILL);
        greenstroke1=new Paint();
        greenstroke1.setColor(Color.GREEN);
        greenstroke1.setStyle(Paint.Style.STROKE);
    }
    private void darwpadai(int x1, int y1) {
        padai=new Path();
        padai.moveTo(x1,y1);
        padai.lineTo(x1+110,y1);
        padai.lineTo(x1+130,y1+20);
        padai.lineTo(x1-20,y1+20);
        padai.lineTo(x1,y1);
        canvas.drawPath(padai,greenstroke);
    }
    public void drawpad(int x1,int y1) {
        pad=new Path();
        pad.moveTo(x1,y1);
        pad.lineTo(x1+150,y1);
        pad.lineTo(x1+130,y1+20);
        pad.lineTo(x1+20,y1+20);
        pad.lineTo(x1,y1);
        this.canvas.drawPath(pad,greenstroke);
    }

}


package com.pathak.rajat.pictlibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapFrontView extends AppCompatActivity
{

    LinearLayout g1,g2,g3,g4,g5;
    HorizontalScrollView horizontalScrollView  ;
    TextView Text;
    TextView textView1,textView2,textView3,textView4,textView5;
    String bookname;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_front_view);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.hsv);
        Text = (TextView)findViewById(R.id.textView2);


        Bundle bundle = getIntent().getExtras();
        int shelf = bundle.getInt("shelf");
        int stack = bundle.getInt("stack");
        bookname = bundle.getString("bookname");
        int shelfDisplay = shelf%5;

      Text.setText("YOUR BOOK: "+bookname+" IS AT STACK:"+stack+" AND ON SHELF:"+shelf);

        g1 = (LinearLayout)findViewById(R.id.col1);
        g2 = (LinearLayout)findViewById(R.id.col2);
        g3 = (LinearLayout)findViewById(R.id.col3);
        g4 = (LinearLayout)findViewById(R.id.col4);
        g5 = (LinearLayout)findViewById(R.id.col5);

        textView1 = (TextView)findViewById(R.id.T1);
        textView2 = (TextView)findViewById(R.id.T2);
        textView3 = (TextView)findViewById(R.id.T3);
        textView4 = (TextView)findViewById(R.id.T4);
        textView5 = (TextView)findViewById(R.id.T5);

        int i = stack*10 - 9;
        int b;
        if(shelf> (stack*10)-5)
            b = i + 5;
        else
            b =i;

        //Toast.makeText(MapFrontView.this,b,Toast.LENGTH_LONG).show();
      textView1.setText(b+"");
        b++;
       textView2.setText(b+"");
        b++;
      textView3.setText(b+"");
        b++;
        textView4.setText(b+"");
        b++;
        textView5.setText(b+"");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Drawable d = getDrawable(R.drawable.border);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(shelfDisplay == 1)
                {
                    g1.setForeground(d);
                }
                else if(shelfDisplay == 2)
                {
                    g2.setForeground(d);
                }
                else if(shelfDisplay == 3)
                {
                    g3.setForeground(d);
                }
                else if(shelfDisplay == 4)
                {
                    g4.setForeground(d);

                }
                else if(shelfDisplay == 0)
                {
                    g5.setForeground(d);

                }
            }
        }


        AlphaAnimation blinkanimation= new AlphaAnimation(1,(float) 0.4); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(1000); // duration - half a second
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkanimation.setRepeatCount(15000); // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE);

        if(shelfDisplay == 1)
        {
            g1.setAnimation(blinkanimation);
        }
        else if(shelfDisplay == 2)
        {
            g2.setAnimation(blinkanimation);
        }
        else if(shelfDisplay == 3)
        {
            g3.setAnimation(blinkanimation);
        }
        else if(shelfDisplay == 4)
        {
            g4.setAnimation(blinkanimation);
            g4.setAnimation(blinkanimation);
            horizontalScrollView.post(new Runnable() {
                @Override
                public void run() {
                    horizontalScrollView.fullScroll(View.FOCUS_RIGHT);

                }
            });

        }
        else if(shelfDisplay == 0)
        {
            g5.setAnimation(blinkanimation);
            g5.setAnimation(blinkanimation);
            horizontalScrollView.post(new Runnable() {
                @Override
                public void run() {
                    horizontalScrollView.fullScroll(View.FOCUS_RIGHT);
                }
            });



        }



    }

    public void gotoinfo(View v)
    {
        Intent i = new  Intent(MapFrontView.this,BOOK_DESC.class);
        i.putExtra("bookname",bookname);
        startActivity(i);


    }

}

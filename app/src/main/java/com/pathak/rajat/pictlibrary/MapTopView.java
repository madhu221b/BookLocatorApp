package com.pathak.rajat.pictlibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MapTopView extends AppCompatActivity
{
    int mode = 0, stack, shelf, stackHalf,paint6a = 100, paint6Mode = 0, stackShelf;
    myView view;
    float x,y;
    String bookname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new myView(this);
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        bookname = bundle.getString("bookname");
        stack = bundle.getInt("stack");
        shelf = bundle.getInt("shelf");

        stackHalf = stack;

        stackShelf = (shelf) - ((stack-1)*10);
    }

    @Override
    public void onBackPressed()
    {
        if(mode != 0 )
        {
            mode = 0;
            view.invalidate();
        }
        else
        {
            super.onBackPressed();
        }
    }

    public class myView extends View
    {
        Paint paint;
        float xLeft,xRight,yLeft,yRight;
        float XLc,RLc,YLc,YRc;
        public myView(Context context)
        {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {

            super.onDraw(canvas);


            x = getWidth();
            y = getHeight();

            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            //          canvas.drawPaint(paint);
            //   paint.setColor(Color.argb(255, 200, 200, 200));
            paint.setColor(Color.argb(255,0, 26, 77));
            Paint paint1 = new Paint();
            paint1.setStyle(Paint.Style.FILL);
            //  paint1.setColor(Color.argb(255, 240, 240, 240));
            paint1.setColor(Color.argb(255, 255, 223, 128));
            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.FILL);
            //  paint2.setColor(Color.argb(255, 222, 184, 135));
            paint2.setColor(Color.argb(255, 179, 134, 0));

            Paint paint3 = new Paint();
            paint3.setStyle(Paint.Style.FILL);
            paint3.setColor(Color.argb(255, 0, 255, 0));

            Paint paint4 = new Paint();
            paint4.setStyle(Paint.Style.FILL);
            paint4.setColor(Color.argb(255, 255, 0, 0));

            Paint paint5 = new Paint();
            paint5.setStyle(Paint.Style.FILL);
            paint5.setColor(Color.BLACK);

            Paint paint6 = new Paint();
            paint6.setStyle(Paint.Style.FILL);
            paint6.setColor(Color.argb(paint6a,0,255,0));


            if(mode == 0)
            {

                //GRID
                float xGrid = x / 10;
                float yGrid = y / 10;
                float xTemp = xGrid;
                float yTemp = yGrid;
                /*for (int i = 0; i < 10; i++)
                {
                    canvas.drawLine(xTemp, 0, xTemp, y, paint);
                    canvas.drawLine(0, yTemp, x, yTemp, paint);
                    xTemp += xGrid;
                    yTemp += yGrid;
                }*/

                //TABLES 1-9
                float yDec = y / 10;
                xRight = x - (x / 25);
                xLeft = (x / 2) + (x / 6);
                float yLine = 85 * y / 100;
                yLeft = (float) (82.5 * y / 100);
                yRight = (float) (87.5 * y / 100);
                int i;
                for (i = 1; i < stackHalf; i++)
                {
                    canvas.drawRect(xLeft, yLeft, xRight, yRight, paint);

                    canvas.drawLine(xLeft, yLine, xRight, yLine, paint2);
                    canvas.drawLine(xLeft, yLine + 1, xRight, yLine + 1, paint2);
                    canvas.drawLine(xLeft, yLine - 1, xRight, yLine - 1, paint2);
                    yLeft -= yDec;
                    yRight -= yDec;
                    yLine -= yDec;
                }

                XLc = xLeft; RLc = xRight; YLc = yLeft;YRc = yRight;
                canvas.drawRect(xLeft, yLeft, xRight, yRight, paint3);

                canvas.drawLine(xLeft, yLine, xRight, yLine, paint1);
                canvas.drawLine(xLeft, yLine + 1, xRight, yLine + 1, paint1);
                canvas.drawLine(xLeft, yLine - 1, xRight, yLine - 1, paint1);

                Path path1 = new Path();
                path1.setFillType(Path.FillType.EVEN_ODD);

                if(stackShelf <= 5)
                {
                    canvas.drawRect(xLeft, yLeft, xRight, yLine - 2, paint);


                    path1.moveTo(xLeft, yRight);
                    path1.lineTo(xRight, yRight);
                    path1.lineTo((float) (x * 97.5 / 100), (float) (yRight + 2.5*y/100));
                    path1.lineTo(x * 65 / 100, (float) (yRight + 2.5*y/100));
                    path1.lineTo(xLeft, yRight);
                }
                else
                {

                    canvas.drawRect(xLeft, yLine + 2, xRight, yRight, paint);
                    path1.moveTo(xLeft, yLeft);
                    path1.lineTo(xRight, yLeft);
                    path1.lineTo((float) (x * 97.5 / 100), (float) (yLeft - 2.5*y/100));
                    path1.lineTo(x * 65 / 100, (float) (yLeft - 2.5*y/100));
                    path1.lineTo(xLeft, yLeft);
                }

                path1.close();
                canvas.drawPath(path1, paint6);
                if(paint6Mode == 0)
                {
                    paint6a--;
                    if(paint6a==20)
                        paint6Mode = 1;
                }
                else
                {
                    paint6a++;
                    if(paint6a == 100)
                        paint6Mode = 0;
                }
                invalidate();

                yLeft -= yDec;
                yRight -= yDec;
                yLine -= yDec;


                for(i = stackHalf + 1; i<10; i++)
                {
                    canvas.drawRect(xLeft, yLeft, xRight, yRight, paint);
                    canvas.drawLine(xLeft, yLine, xRight, yLine, paint1);
                    canvas.drawLine(xLeft, yLine + 1, xRight, yLine + 1, paint1);
                    canvas.drawLine(xLeft, yLine - 1, xRight, yLine - 1, paint1);

                    yLeft -= yDec;
                    yRight -= yDec;
                    yLine -= yDec;
                }

                //TABLE 10
                canvas.drawRect((x / 2) - (x / 35), (float) 38.5 * y / 100, (x / 2) + (x / 35), (float) 41.5 * y / 100, paint);

                //TABLE 11
                canvas.drawRect((x / 2) - (x / 35), (float) 20 * y / 100, (x / 2) + (x / 35), (float) 32.5 * y / 100, paint);
                canvas.drawLine((x / 2), 20 * y / 100, (x / 2), (float) 32.5 * y / 100, paint1);
                canvas.drawLine((x / 2) + 1, 20 * y / 100, (x / 2) + 1, (float) 32.5 * y / 100, paint1);
                canvas.drawLine((x / 2) - 1, 20 * y / 100, (x / 2) - 1, (float) 32.5 * y / 100, paint1);

                //TABLES 12-15 IN ANTICLOCKWISE FORM TOP
                canvas.drawRect(x / 100, y / 100, (float) 32.5 * x / 100, (float) 4 * y / 100, paint);
                canvas.drawRect(x / 100, 6 * y / 100, 5 * x / 100, 53 * y / 100, paint);
                canvas.drawRect(8 * x / 100, 52 * y / 100, 3 * x / 10, 55 * y / 100, paint);
                canvas.drawRect(32 * x / 100, 6 * y / 100, 36 * x / 100, (float) 47.5 * y / 100, paint);

                //TABLE 15
                canvas.drawRect(6 * x / 100, 56 * y / 100, 40 * x / 100, 60 * y / 100, paint2);

                //TABLE 16
                canvas.drawRect(2 * x / 100, 62 * y / 100, 7 * x / 100, 88 * y / 100, paint2);

                //FRONT DESK 17
                canvas.drawRect(40 * x / 100, 98 * y / 100, 60 * x / 100, y, paint2);
                canvas.drawRect(37 * x / 100, 94 * y / 100, 40 * x / 100, y, paint2);
                canvas.drawRect(60 * x / 100, 94 * y / 100, 63 * x / 100, y, paint2);

                //ARROWS 18, 19
                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo(x * 68 / 100, y * 91 / 100);
                path.lineTo(x * 72 / 100, y * 94 / 100);
                path.lineTo(x * 64 / 100, y * 94 / 100);
                path.lineTo(x * 68 / 100, y * 91 / 100);
                path.close();
                canvas.drawPath(path, paint3);
                canvas.drawRect(66 * x / 100, 94 * y / 100, 70 * x / 100, y, paint3);
                Path path2 = new Path();
                path2.setFillType(Path.FillType.EVEN_ODD);
                path2.moveTo(32 * x / 100, y);
                path2.lineTo(36 * x / 100, 97 * y / 100);
                path2.lineTo(28 * x / 100, 97 * y / 100);
                path2.lineTo(32 * x / 100, y);
                path2.close();
                canvas.drawPath(path2, paint4);
                canvas.drawRect(30 * x / 100, 91 * y / 100, 34 * x / 100, 97 * y / 100, paint4);
                paint3.setTextSize(70);
                canvas.drawText("Entry", x * 73 / 100, (float) ((float) y * 97.5 / 100), paint3);
                paint4.setTextSize(70);
                canvas.drawText("Exit", 12 * x / 100, (float) ((float) y * 97.5 / 100), paint4);
            }
            else
            {
                /*
            canvas.drawRect(x*5/100, y*30/100, x*95/100, y*70/100, paint);

                float temp = y*30/100 + 40*y/600;
                for(int i=0;i<5;i++)
                {
                  canvas.drawLine(x*5/100, temp, x*95/100, temp, paint5);

                    temp += 40*y/600;
                }
                float temp2 = x*5/100 + 90*x/500;
                for(int i=0;i<4;i++)
                {
                canvas.drawLine(temp2, y*30/100, temp2, y*70/100, paint5);

                    temp2 += 90*x/500;
                }
                float tempY = 30*y/100;
                for(int i=1;i<bay;i++)
                {
                    tempY += 40*y/600;
                }
                float tempX = x*5/100;
                for(int i=1;i<shelf;i++)
                {
                    tempX += 90*x/500;
                }
                canvas.drawRect(tempX, tempY, tempX + 90*x/500, tempY + 40*y/600, paint6);
*/

                /*Intent i = new Intent(Main2Activity.this,Main3Activity.class);
                i.putExtra("bay", bay);
                i.putExtra("shelf", shelf);
                startActivity(i);
                mode=0;*/
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            float xTouch = event.getX();
            float yTouch = event.getY();
            int action = event.getAction();

            if(xTouch>=XLc && xTouch<=RLc )
                if(yTouch>=YLc && yTouch<=YRc)

                    if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_BUTTON_PRESS)
                    {
                        //mode++;
                        Intent i = new Intent(MapTopView.this, MapFrontView.class);
                        i.putExtra("bookname",bookname);
                        i.putExtra("stack",stack);
                        i.putExtra("shelf", shelf);
                        startActivity(i);
                    }
            //invalidate();


            return true;
        }


    }
}

package com.example.faza1_baicuandrei;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class PieChart extends View {

    ArrayList<Review> reviewList;
    public PieChart(Context context, ArrayList listF) {
        super(context);
        this.reviewList= listF;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int max = 255;
        int min = 1;
        int range = max - min + 1;
        Paint pensula = new Paint();
        pensula.setStyle(Paint.Style.FILL);
        float totalSum;
        float sum1 = 0;
        float sum2 = 0;
        float sum3 = 0;

        for(int i = 0; i < reviewList.size(); i ++ ) {
           sum1 = sum1 + reviewList.get(i).getDesign();
           sum2 = sum2 + reviewList.get(i).getInformation();
           sum3 = sum3 + reviewList.get(i).getIntuity();
        }
        sum1 = sum1/reviewList.size();
        sum2 = sum2/reviewList.size();
        sum3 = sum3/reviewList.size();

        totalSum = sum1 + sum2 + sum3;
        float[] sume = {sum1,sum2,sum3};

        float completed = 0;
        pensula.setStyle(Paint.Style.FILL);
        float sweep;
        int completedRect = 100;
        int bottomRect = 130;

        for(float f: sume){
            sweep = 360*f/totalSum;
            pensula.setColor(Color.rgb((int)(Math.random()*range)%256,(int)(Math.random()*range)%256,(int)(Math.random()*range)%256));
            canvas.drawArc(500,50,850,400,completed,sweep,true,pensula);
            canvas.drawRect(70,completedRect,100,bottomRect,pensula);

            completed = completed + sweep;
            bottomRect = bottomRect +130 ;
            completedRect = bottomRect - 30;
        }
        pensula.setColor(Color.WHITE);
        canvas.drawCircle(675,230,130, pensula);

    }
}


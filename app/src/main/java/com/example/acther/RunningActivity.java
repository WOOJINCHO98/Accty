package com.example.acther;

import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunningActivity extends AppCompatActivity
{

    TextView title,test,test1,test2,test3,test4,test5,running_good;

    ImageView running_image;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);



        title = findViewById(R.id.title);
        test = (TextView) findViewById(R.id.test);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        test3 = (TextView) findViewById(R.id.test3);
        test4 = (TextView) findViewById(R.id.test4);
        test5 = (TextView) findViewById(R.id.test5);
        running_good = (TextView) findViewById(R.id.running_good);
        running_image = (ImageView) findViewById(R.id.running_image);




        //CharSequence address = a.get(0).toString();

        //test.setText(address); // 주소정보

        SplashActivity flagMaker = new SplashActivity();


        test5.setText(flagMaker.hMap.get("address"));


        if (Integer.parseInt(flagMaker.hMap.get("Cycle"))==1){

            running_good.setText("러닝하기 좋은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#00D1FF"));
            running_good.setTextColor(Color.parseColor("#00D1FF"));
            running_image.setColorFilter(Color.parseColor("#00D1FF"));

        }
        else{
            running_good.setText("러닝하기 좋지 않은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#FF3203"));
            running_good.setTextColor(Color.parseColor("#FF3203"));
            running_image.setColorFilter(Color.parseColor("#FF3203"));


            //running_image.setImageTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForegroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setBackgroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForeground(getResources().getDrawable(R.drawable.good));




        }

        Double temp = Double.parseDouble(flagMaker.hMap.get("TempAverage"));
        Integer intTemp = temp.intValue();

        test.setText(intTemp.toString()); // 평균온도
        test1.setText(flagMaker.hMap.get("Rain1hAverage")); // 평균강수량
        test4.setText(flagMaker.hMap.get("O3Grade")); // 평균풍속
        test3.setText(flagMaker.hMap.get("pm10Grade")); // 평균강수량



    }

}


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

public class SurfingActivity extends AppCompatActivity
{

    TextView title,is_good,temperature,rainfall,wind,dust,wave,address;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfing);



        title = findViewById(R.id.title);
        is_good = (TextView) findViewById(R.id.is_good);
        temperature = (TextView) findViewById(R.id.temperature);
        rainfall = (TextView) findViewById(R.id.rainfall);
        wind = (TextView) findViewById(R.id.wind);
        dust = (TextView) findViewById(R.id.dust);
        wave = (TextView) findViewById(R.id.wave);
        address = (TextView) findViewById(R.id.address);
        img = (ImageView) findViewById(R.id.img);




        //CharSequence address = a.get(0).toString();

        //test.setText(address); // 주소정보

        SplashActivity flagMaker = new SplashActivity();


        address.setText(flagMaker.hMap.get("address"));


        if (Integer.parseInt(flagMaker.hMap.get("Surfing"))==1){

            is_good.setText("서핑하기 좋은 날씨"); // 서핑 플래그
            title.setTextColor(Color.parseColor("#00D1FF"));
            is_good.setTextColor(Color.parseColor("#00D1FF"));
            img.setColorFilter(Color.parseColor("#00D1FF"));

        }
        else{
            is_good.setText("서핑하기 좋지 않은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#FF3203"));
            is_good.setTextColor(Color.parseColor("#FF3203"));
            img.setColorFilter(Color.parseColor("#FF3203"));


            //running_image.setImageTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForegroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setBackgroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForeground(getResources().getDrawable(R.drawable.good));




        }

        Double temp = Double.parseDouble(flagMaker.hMap.get("TempAverage"));
        Integer intTemp = temp.intValue();

        temperature.setText(intTemp.toString()); // 평균온도
        rainfall.setText(flagMaker.hMap.get("Rain1hAverage")); // 평균강수량
        //test4.setText(flagMaker.hMap.get("O3Grade")); // 평균풍속
        dust.setText(flagMaker.hMap.get("pm10Grade")); // 평균강수량
        wind.setText(flagMaker.hMap.get("WindSpeedAverage")); // 평균강수량

        wave.setText(flagMaker.hMap.get("waveAverage")); // 평균파고


    }

}


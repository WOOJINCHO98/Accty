package com.example.acther;

import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SurfingActivity extends AppCompatActivity
{

    TextView title,is_good,temperature,snowfall,wind,dust,address,ozone,msg;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfing);



        title = findViewById(R.id.title);
        is_good = (TextView) findViewById(R.id.is_good);
        temperature = (TextView) findViewById(R.id.temperature);
        snowfall = (TextView) findViewById(R.id.snowfall);
        wind = (TextView) findViewById(R.id.wind);
        dust = (TextView) findViewById(R.id.dust);
        address = (TextView) findViewById(R.id.address);
        img = (ImageView) findViewById(R.id.img);
        ozone = (TextView) findViewById(R.id.ozone);
        msg = (TextView) findViewById(R.id.msg);


        SplashActivity flagMaker = new SplashActivity();


        address.setText(flagMaker.hMap.get("address2"));


        if (Integer.parseInt(flagMaker.hMap.get("Surfing"))==1){

            is_good.setText("서핑하기 좋은 날씨"); // 서핑 플래그
            title.setTextColor(Color.parseColor("#00D1FF"));
            is_good.setTextColor(Color.parseColor("#00D1FF"));
            img.setImageResource(R.drawable.surf_b);
        }
        else{
            is_good.setText("서핑하기 좋지 않은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#FF3203"));
            is_good.setTextColor(Color.parseColor("#FF3203"));
            img.setImageResource(R.drawable.surf_o);


            String msgString = flagMaker.hMap.get("temp0Msg2");
            System.out.println("msgString : "+msgString);

            if (StringUtils.isEmpty(flagMaker.hMap.get("rainMsg"))==false){
                msgString = flagMaker.hMap.get("rainMsg");
                System.out.println("msgString : "+msgString);
            }
            if (StringUtils.isEmpty(flagMaker.hMap.get("snowMsg"))==false){
                msgString = flagMaker.hMap.get("snowMsg");
                System.out.println("msgString : "+msgString);

            }
            if (StringUtils.isEmpty(flagMaker.hMap.get("temp0Msg"))==false){
                msgString = flagMaker.hMap.get("temp0Msg");
                System.out.println("msgString : "+msgString);

            }
            if (StringUtils.isEmpty(flagMaker.hMap.get("temp30Msg"))==false){
                msgString = flagMaker.hMap.get("temp30Msg");
                System.out.println("msgString : "+msgString);

            }

            System.out.println(msgString);
            msg.setText(msgString);
            msg.setVisibility(View.VISIBLE);


        }

        Double temp = Double.parseDouble(flagMaker.hMap.get("TempAverage2"));
        Integer intTemp = temp.intValue();


        Double windAverage = Double.parseDouble(flagMaker.hMap.get("WindSpeedAverage2"));
        Integer intWindAverage = windAverage.intValue();

        //System.out.println(intWindAverage);

        temperature.setText(intTemp.toString()); // 평균온도
        snowfall.setText(flagMaker.hMap.get("Snow1hAverage2")); // 평균강수량
        //test4.setText(flagMaker.hMap.get("O3Grade")); // 평균풍속
        dust.setText(flagMaker.hMap.get("pm10Grade")); // 평균강수량
        wind.setText(intWindAverage.toString()); // 평균강수량

        ozone.setText(flagMaker.hMap.get("O3Grade")); // 오존등급


    }

}


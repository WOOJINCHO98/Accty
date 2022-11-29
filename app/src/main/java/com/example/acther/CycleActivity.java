package com.example.acther;

import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CycleActivity extends AppCompatActivity
{

    TextView title,is_good,temperature,rainfall,wind,dust,wave,address,ozone,msg;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);



        title = findViewById(R.id.title);
        is_good = (TextView) findViewById(R.id.is_good);
        temperature = (TextView) findViewById(R.id.temperature);
        rainfall = (TextView) findViewById(R.id.rainfall);
        wind = (TextView) findViewById(R.id.wind);
        dust = (TextView) findViewById(R.id.dust);
        wave = (TextView) findViewById(R.id.wave);
        address = (TextView) findViewById(R.id.address);
        img = (ImageView) findViewById(R.id.img);
        ozone = (TextView) findViewById(R.id.ozone);
        msg = (TextView) findViewById(R.id.msg);



        //CharSequence address = a.get(0).toString();

        //test.setText(address); // 주소정보

        SplashActivity flagMaker = new SplashActivity();


        address.setText(flagMaker.hMap.get("address"));


        if (Integer.parseInt(flagMaker.hMap.get("Cycle"))==1){

            is_good.setText("라이딩하기 좋은 날씨"); // 서핑 플래그
            title.setTextColor(Color.parseColor("#00D1FF"));
            is_good.setTextColor(Color.parseColor("#00D1FF"));
            img.setImageResource(R.drawable.cycle_b);
            msg.setVisibility(View.INVISIBLE);
        }
        else{
            is_good.setText("라이딩하기 좋지 않은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#FF3203"));
            is_good.setTextColor(Color.parseColor("#FF3203"));
            img.setImageResource(R.drawable.cycle_o);

            String msgString = flagMaker.hMap.get("tempGapMsg");


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


            //running_image.setImageTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForegroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setBackgroundTintBlendMode(BlendMode.MULTIPLY);
            //running_image.setForeground(getResources().getDrawable(R.drawable.good));




        }

        Double temp = Double.parseDouble(flagMaker.hMap.get("TempAverage"));
        Integer intTemp = temp.intValue();


        Double windAverage = Double.parseDouble(flagMaker.hMap.get("WindSpeedAverage"));
        Integer intWindAverage = windAverage.intValue();

        temperature.setText(intTemp.toString()); // 평균온도
        rainfall.setText(flagMaker.hMap.get("Rain1hAverage")); // 평균강수량
        //test4.setText(flagMaker.hMap.get("O3Grade")); // 평균
        dust.setText(flagMaker.hMap.get("pm10Grade")); // 미세먼지
        wind.setText(intWindAverage.toString()+"m/s"); // 평균풍속

        ozone.setText(flagMaker.hMap.get("O3Grade")); // 오존등급


    }

}


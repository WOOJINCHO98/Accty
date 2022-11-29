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

public class GolfActivity extends AppCompatActivity
{

    TextView title,is_good,temperature,snowfall,wind,dust,address,ozone,msg;

    ImageView img,wind_direction1,wind_direction2,wind_direction3,wind_direction4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golf);



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


        wind_direction1 = (ImageView) findViewById(R.id.wind_direction1);
        wind_direction2 = (ImageView) findViewById(R.id.wind_direction2);
        wind_direction3 = (ImageView) findViewById(R.id.wind_direction3);
        wind_direction4 = (ImageView) findViewById(R.id.wind_direction4);

        SplashActivity flagMaker = new SplashActivity();


        address.setText(flagMaker.hMap.get("address2"));


        if (flagMaker.hMap.get("windDirection10").equals("NW-N")){
            wind_direction1.setImageResource(R.drawable.arrow_n);
        }
        else if(flagMaker.hMap.get("windDirection10").equals("W-NW")){
            wind_direction1.setImageResource(R.drawable.arrow_nw);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("SW-W")){
            wind_direction1.setImageResource(R.drawable.arrow_w);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("S-SW")){
            wind_direction1.setImageResource(R.drawable.arrow_sw);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("SE-S")){
            wind_direction1.setImageResource(R.drawable.arrow_s);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("E-SE")){
            wind_direction1.setImageResource(R.drawable.arrow_se);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("NE-E")){
            wind_direction1.setImageResource(R.drawable.arrow_e);


        }
        else if(flagMaker.hMap.get("windDirection10").equals("N-NE")){
            wind_direction1.setImageResource(R.drawable.arrow_ne);


        }

        if (flagMaker.hMap.get("windDirection12").equals("NW-N")){
            wind_direction2.setImageResource(R.drawable.arrow_n);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("W-NW")) {
            wind_direction2.setImageResource(R.drawable.arrow_nw);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("SW-W")) {
            wind_direction2.setImageResource(R.drawable.arrow_w);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("S-SW")) {
            wind_direction2.setImageResource(R.drawable.arrow_sw);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("SE-S")) {
            wind_direction2.setImageResource(R.drawable.arrow_s);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("E-SE")) {
            wind_direction2.setImageResource(R.drawable.arrow_se);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("NE-E")) {
            wind_direction2.setImageResource(R.drawable.arrow_e);
        }
        else if(flagMaker.hMap.get("windDirection12").equals("N-NE")) {
            wind_direction2.setImageResource(R.drawable.arrow_ne);
        }




        if (flagMaker.hMap.get("windDirection14").equals("NW-N")){
            wind_direction3.setImageResource(R.drawable.arrow_n);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("W-NW")) {
            wind_direction3.setImageResource(R.drawable.arrow_nw);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("SW-W")) {
            wind_direction3.setImageResource(R.drawable.arrow_w);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("S-SW")) {
            wind_direction3.setImageResource(R.drawable.arrow_sw);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("SE-S")) {
            wind_direction3.setImageResource(R.drawable.arrow_s);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("E-SE")) {
            wind_direction3.setImageResource(R.drawable.arrow_se);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("NE-E")) {
            wind_direction3.setImageResource(R.drawable.arrow_e);
        }
        else if (flagMaker.hMap.get("windDirection14").equals("N-NE")) {
            wind_direction3.setImageResource(R.drawable.arrow_ne);
        }


        if (flagMaker.hMap.get("windDirection16").equals("NW-N")){
            wind_direction4.setImageResource(R.drawable.arrow_n);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("W-NW")) {
            wind_direction4.setImageResource(R.drawable.arrow_nw);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("SW-W")) {
            wind_direction4.setImageResource(R.drawable.arrow_w);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("S-SW")) {
            wind_direction4.setImageResource(R.drawable.arrow_sw);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("SE-S")) {
            wind_direction4.setImageResource(R.drawable.arrow_s);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("E-SE")) {
            wind_direction4.setImageResource(R.drawable.arrow_se);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("NE-E")) {
            wind_direction4.setImageResource(R.drawable.arrow_e);
        }
        else if (flagMaker.hMap.get("windDirection16").equals("N-NE")) {
            wind_direction4.setImageResource(R.drawable.arrow_ne);
        }
        if (Integer.parseInt(flagMaker.hMap.get("Golf"))==1){

            is_good.setText("골프하기 좋은 날씨"); // 서핑 플래그
            title.setTextColor(Color.parseColor("#00D1FF"));
            is_good.setTextColor(Color.parseColor("#00D1FF"));
            img.setImageResource(R.drawable.golf_b);
        }
        else{
            is_good.setText("골프하기 좋지 않은 날씨"); // 러닝 플래그
            title.setTextColor(Color.parseColor("#FF3203"));
            is_good.setTextColor(Color.parseColor("#FF3203"));
            img.setImageResource(R.drawable.golf_o);


            String msgString = flagMaker.hMap.get("tempGapMsg");
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


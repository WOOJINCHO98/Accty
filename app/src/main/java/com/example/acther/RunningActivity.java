package com.example.acther;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunningActivity extends AppCompatActivity
{

    TextView test,test1,test2,test3,test4,test5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);


        test = (TextView) findViewById(R.id.test);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        test3 = (TextView) findViewById(R.id.test3);
        test4 = (TextView) findViewById(R.id.test4);
        test5 = (TextView) findViewById(R.id.test5);


        List a = ((SplashActivity)SplashActivity.mContext).makeFlag();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        CharSequence address = a.get(0).toString();

        test.setText(address); // 주소정보


        if (Integer.parseInt(a.get(1).toString())==1){

            test1.setText("러닝하기 좋은 날 입니다!"); // 러닝 플래그
        }
        else{
            test1.setText("러닝하기 좋지 않은 날 입니다!"); // 러닝 플래그
        }

        test2.setText(a.get(7).toString()); // 평균온도
        test3.setText(a.get(8).toString()); // 평균강수량



    }

}


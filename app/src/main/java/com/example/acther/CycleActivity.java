package com.example.acther;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CycleActivity extends AppCompatActivity
{

    FlagMaker flagMaker = new FlagMaker();


    TextView test,test1,test2,test3,test4,test5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle2);




        //test = (TextView) findViewById(R.id.test);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        test3 = (TextView) findViewById(R.id.test3);
        test4 = (TextView) findViewById(R.id.test4);
        test5 = (TextView) findViewById(R.id.test5);



        List a = ((SplashActivity)SplashActivity.mContext).makeFlag();

        SplashActivity flagMaker = new SplashActivity();

        try {
            TimeUnit.SECONDS.sleep(2);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //CharSequence address = a.get(0).toString();


        System.out.println("#########################\n\n####################\n\n"+flagMaker.hMap.get("address"));
        test1.setText(flagMaker.hMap.get("address"));
        //test1.setText(String.valueOf(address)); // 주소정보

        //test1.setText(flagMaker.address); // 주소정보


        if (flagMaker.hMap.get("Cycle").equals("1")){

            test2.setText("자전거 타기 좋은 날 입니다!"); // 싸이클 플래그
        }
        else{
            test2.setText("자전거 타기 좋지 않은 날 입니다!"); // 싸이클 플래그
        }

        test3.setText(a.get(7).toString()); // 평균온도
        test4.setText(a.get(8).toString()); // 평균강수량
        if(flagMaker.hMap.get("Rain1hAverage").equals("0.0")){
            test4.setText("강수 없음"); // 1시간 평균 강수량
        }
        else{
            test4.setText("비가 옵니다."); // 1시간 평균 강수량
        }
        //test4.setText(a.get(10).toString()); // 평균 풍속
        test5.setText(flagMaker.hMap.get("MaxWindSpeed")); // 최대 풍속



    }
}

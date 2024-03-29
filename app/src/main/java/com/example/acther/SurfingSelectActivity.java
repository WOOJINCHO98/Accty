package com.example.acther;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

public class SurfingSelectActivity extends AppCompatActivity {


    SQLiteDatabase sqlDb;
    SurfingDb SurfingDb;
    Cursor cursor;
    TextView Name1,Name2,Name3,Name4;
    TextView phone1,phone2,phone3,phone4;
    TextView locationX1,locationX2,locationX3,locationX4,locationX5,locationX6;
    TextView locationY1,locationY2,locationY3,locationY4,locationY5,locationY6;

    FrameLayout frameBtn1,frameBtn2,frameBtn3,frameBtn4;
    MainActivity MA = (MainActivity) MainActivity.activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfing_select);



        SurfingDb=new SurfingDb(this);
        Name1=(TextView)findViewById(R.id.Name1);
        Name2=(TextView)findViewById(R.id.Name2);
        Name3=(TextView)findViewById(R.id.Name3);
        Name4=(TextView)findViewById(R.id.Name4);

        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);
        phone3=(TextView)findViewById(R.id.phone3);
        phone4=(TextView)findViewById(R.id.phone4);

        frameBtn1= (FrameLayout) findViewById(R.id.frameBtn1);
        frameBtn2= (FrameLayout) findViewById(R.id.frameBtn2);
        frameBtn3= (FrameLayout) findViewById(R.id.frameBtn3);
        frameBtn4= (FrameLayout) findViewById(R.id.frameBtn4);



        /*
        locationX1=(TextView)findViewById(R.id.locationX1);
        locationX2=(TextView)findViewById(R.id.locationX2);
        locationX3=(TextView)findViewById(R.id.locationX3);
        locationX4=(TextView)findViewById(R.id.locationX4);
        locationX5=(TextView)findViewById(R.id.locationX5);
        locationX6=(TextView)findViewById(R.id.locationX6);

        locationY1=(TextView)findViewById(R.id.locationY1);
        locationY2=(TextView)findViewById(R.id.locationY2);
        locationY3=(TextView)findViewById(R.id.locationY3);
        locationY4=(TextView)findViewById(R.id.locationY4);
        locationY5=(TextView)findViewById(R.id.locationY5);
        locationY6=(TextView)findViewById(R.id.locationY6);
*/



        String strName[]=new String[4];                    //낚시터 이름 넣을 변수
        String strLocationX[]=new String[4];               //낚시터 X좌표 넣을 변수
        String strLocationY[]=new String[4];               //낚시터 Y좌표 넣을 변수
        String addressName[]=new String[4];
        String phone[]=new String[4];
        String placeUrl[]=new String[4];

        sqlDb=SurfingDb.getReadableDatabase();
        SurfingDb.onUpgrade(sqlDb,1,2);
        cursor = sqlDb.rawQuery("SELECT * FROM SurfingTable;",null);

        //아까선언한 변수에 스키장 이름,X좌표,y좌표값 넣기
        for(int i=0; i<4;i++){
            cursor.moveToNext();

            strName[i]=cursor.getString(0);
            strLocationX[i]=cursor.getString(1);
            strLocationY[i]=cursor.getString(2);
            addressName[i]=cursor.getString(3);
            phone[i]=cursor.getString(4);
            placeUrl[i]=cursor.getString(5);





        }
        cursor.close();                                 //커서닫기
        sqlDb.close();                         //db닫기

        Name1.setText(strName[0]);
        Name2.setText(strName[1]);
        Name3.setText(strName[2]);
        Name4.setText(strName[3]);

        phone1.setText(phone[0]);
        phone2.setText(phone[1]);
        phone3.setText(phone[2]);
        phone4.setText(phone[3]);


        /*
        locationX1.setText(strLocationX[0]);
        locationX2.setText(strLocationX[1]);
        locationX3.setText(strLocationX[2]);
        locationX4.setText(strLocationX[3]);
        locationX5.setText(strLocationX[4]);
        locationX6.setText(strLocationX[5]);

        locationY1.setText(strLocationX[0]);
        locationY2.setText(strLocationX[1]);
        locationY3.setText(strLocationX[2]);
        locationY4.setText(strLocationX[3]);
        locationY5.setText(strLocationX[4]);
        locationY6.setText(strLocationX[5]);

         */







        frameBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("클릭");
                System.out.println(strName[0]+ strLocationX[0]+ strLocationY[0]);


                try {
                    Integer a = ((SplashActivity)SplashActivity.mContext).makeFlag_DB(strName[0], strLocationX[0], strLocationY[0]);


                }catch (NullPointerException e){
                    e.printStackTrace();
                }


                Intent intent = new Intent(SurfingSelectActivity.this, SurfingActivity.class);
                startActivity(intent);
            }



        });

        frameBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("클릭");
                System.out.println(strName[1]+ strLocationX[1]+ strLocationY[1]);


                try {
                    Integer a = ((SplashActivity)SplashActivity.mContext).makeFlag_DB(strName[1], strLocationX[1], strLocationY[1]);


                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(SurfingSelectActivity.this, SurfingActivity.class);
                startActivity(intent);
            }



        });

        frameBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("클릭");
                System.out.println(strName[2]+ strLocationX[2]+ strLocationY[2]);


                try {
                    Integer a = ((SplashActivity)SplashActivity.mContext).makeFlag_DB(strName[2], strLocationX[2], strLocationY[2]);


                }catch (NullPointerException e){
                    e.printStackTrace();
                }


                Intent intent = new Intent(SurfingSelectActivity.this, SurfingActivity.class);
                startActivity(intent);
            }



        });

        frameBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("클릭");
                System.out.println(strName[0]+ strLocationX[0]+ strLocationY[0]);


                try {
                    Integer a = ((SplashActivity)SplashActivity.mContext).makeFlag_DB(strName[3], strLocationX[3], strLocationY[3]);


                }catch (NullPointerException e){
                    e.printStackTrace();
                }


                Intent intent = new Intent(SurfingSelectActivity.this, SurfingActivity.class);
                startActivity(intent);
            }



        });
    }
}


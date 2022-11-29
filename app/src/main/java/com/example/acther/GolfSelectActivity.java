package com.example.acther;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GolfSelectActivity extends AppCompatActivity
{
    SQLiteDatabase sqlDb;
    GolfDb golfDb;
    Cursor cursor;
    TextView name1,name2,name3,name4,name5,name6;
    TextView phone1,phone2,phone3,phone4,phone5,phone6;
    TextView locationX1,locationX2,locationX3,locationX4,locationX5,locationX6;
    TextView locationY1,locationY2,locationY3,locationY4,locationY5,locationY6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golf_select);

        golfDb=new GolfDb(this);

        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        name3=(TextView)findViewById(R.id.name3);
        name4=(TextView)findViewById(R.id.name4);
        name5=(TextView)findViewById(R.id.name5);
        name6=(TextView)findViewById(R.id.name6);

        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);
        phone3=(TextView)findViewById(R.id.phone3);
        phone4=(TextView)findViewById(R.id.phone4);
        phone5=(TextView)findViewById(R.id.phone5);
        phone6=(TextView)findViewById(R.id.phone6);

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


        String strName[]=new String[6];                    //골프장 이름 넣을 변수
        String strLocationX[]=new String[6];               //골프장 X좌표 넣을 변수
        String strLocationY[]=new String[6];               //골프장 Y좌표 넣을 변수
        String phone[]=new String[6];
        String placeUrl[]=new String[6];
        String addressName[]=new String[6];

        //테이블조회
        sqlDb=golfDb.getReadableDatabase();
        golfDb.onUpgrade(sqlDb,1,2);

        cursor = sqlDb.rawQuery("SELECT * FROM GolfTable;",null);



        //아까선언한 변수에 스키장 이름,X좌표,y좌표값 넣기
        for(int i=0; i<6;i++){
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

        name1.setText(strName[0]);
        name2.setText(strName[1]);
        name3.setText(strName[2]);
        name4.setText(strName[3]);
        name5.setText(strName[4]);
        name6.setText(strName[5]);

        phone1.setText(phone[0]);
        phone2.setText(phone[1]);
        phone3.setText(phone[2]);
        phone4.setText(phone[3]);
        phone5.setText(phone[4]);
        phone6.setText(phone[5]);

        locationX1.setText(strLocationX[0]);
        locationX2.setText(strLocationX[1]);
        locationX3.setText(strLocationX[2]);
        locationX4.setText(strLocationX[3]);
        locationX5.setText(strLocationX[4]);
        locationX6.setText(strLocationX[5]);

        locationY1.setText(strLocationY[0]);
        locationY2.setText(strLocationY[1]);
        locationY3.setText(strLocationY[2]);
        locationY4.setText(strLocationY[3]);
        locationY5.setText(strLocationY[4]);
        locationY6.setText(strLocationY[5]);







    }

}

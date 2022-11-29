package com.example.acther;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SurfingSelectActivity extends AppCompatActivity
{
    SQLiteDatabase sqlDb;
    SurfingDb surfingDb;
    Cursor cursor;
    TextView name1,name2,name3,name4;
    TextView phone1,phone2,phone3,phone4;
    TextView locationX1,locationX2,locationX3,locationX4;
    TextView locationY1,locationY2,locationY3,locationY4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfing_select);



        String strName[]=new String[4];
        String strLocationX[]=new String[4];
        String strLocationY[]=new String[4];
        String phone[]=new String[4];
        String addressName[]=new String[4];
        String placeUrl[]=new String[4];

        surfingDb=new SurfingDb(this);

        name1=(TextView)findViewById(R.id.name1);
        name2=(TextView)findViewById(R.id.name2);
        name3=(TextView)findViewById(R.id.name3);
        name4=(TextView)findViewById(R.id.name4);

        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);
        phone3=(TextView)findViewById(R.id.phone3);
        phone4=(TextView)findViewById(R.id.phone4);

        locationX1=(TextView)findViewById(R.id.locationX1);
        locationX2=(TextView)findViewById(R.id.locationX2);
        locationX3=(TextView)findViewById(R.id.locationX3);
        locationX4=(TextView)findViewById(R.id.locationX4);

        locationY1=(TextView)findViewById(R.id.locationY1);
        locationY2=(TextView)findViewById(R.id.locationY2);
        locationY3=(TextView)findViewById(R.id.locationY3);
        locationY4=(TextView)findViewById(R.id.locationY4);

        sqlDb=surfingDb.getReadableDatabase();
        surfingDb.onUpgrade(sqlDb,1,2);
        cursor = sqlDb.rawQuery("SELECT * FROM SurfingTable;",null);

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


        name1.setText(strName[0]);
        name2.setText(strName[1]);
        name3.setText(strName[2]);
        name4.setText(strName[3]);


        phone1.setText(phone[0]);
        phone2.setText(phone[1]);
        phone3.setText(phone[2]);
        phone4.setText(phone[3]);


        locationX1.setText(strLocationX[0]);
        locationX2.setText(strLocationX[1]);
        locationX3.setText(strLocationX[2]);
        locationX4.setText(strLocationX[3]);

        locationY1.setText(strLocationY[0]);
        locationY2.setText(strLocationY[1]);
        locationY3.setText(strLocationY[2]);
        locationY4.setText(strLocationY[3]);





















    }

}

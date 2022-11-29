package com.example.acther;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SkiSelectActivity extends AppCompatActivity {


    SQLiteDatabase sqlDb;
    SkiDb skiDb;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ski_select);

        skiDb=new SkiDb(this);


        String strName[]=new String[6];                    //낚시터 이름 넣을 변수
        String strLocationX[]=new String[6];               //낚시터 X좌표 넣을 변수
        String strLocationY[]=new String[6];               //낚시터 Y좌표 넣을 변수

        sqlDb=skiDb.getReadableDatabase();
        skiDb.onUpgrade(sqlDb,1,2);
        cursor = sqlDb.rawQuery("SELECT * FROM SkiTable;",null);



        //아까선언한 변수에 스키장 이름,X좌표,y좌표값 넣기
        for(int i=0; i<6;i++){
            cursor.moveToNext();

            strName[i]=cursor.getString(0);
            strLocationX[i]=cursor.getString(1);
            strLocationY[i]=cursor.getString(2);

        }
        cursor.close();                                 //커서닫기
        sqlDb.close();                         //db닫기







    }
}


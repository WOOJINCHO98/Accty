package com.example.acther;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GlidingSelectActivity extends AppCompatActivity
{

    SQLiteDatabase sqlDb;
    ParaDb paraDb;
    Cursor cursor;
    EditText paraName;   //확인용도 삭제하면됨
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gliding_select);

        paraDb=new ParaDb(this);
        paraName=(EditText) findViewById(R.id.paraName);

        String strName[]=new String[4];                    //낚시터 이름 넣을 변수
        String strLocationX[]=new String[4];               //낚시터 X좌표 넣을 변수
        String strLocationY[]=new String[4];               //낚시터 Y좌표 넣을 변수

        sqlDb=paraDb.getReadableDatabase();
        paraDb.onUpgrade(sqlDb,1,2);
        cursor = sqlDb.rawQuery("SELECT * FROM ParaTable;",null);



        //아까선언한 변수에 스키장 이름,X좌표,y좌표값 넣기
        for(int i=0; i<4;i++){
            cursor.moveToNext();

            strName[i]=cursor.getString(0);
            strLocationX[i]=cursor.getString(1);
            strLocationY[i]=cursor.getString(2);

        }
        cursor.close();                                 //커서닫기
        sqlDb.close();                         //db닫기
        paraName.setText(strName[3]);           //저장 확인용도,
    }

}

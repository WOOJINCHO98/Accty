package com.example.acther;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GolfSelectActivity extends AppCompatActivity
{
    SQLiteDatabase sqlDb;
    SkiDb skiDb;
    Cursor cursor;
    EditText skiname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golf_select);

        skiname=(EditText) findViewById(R.id.skiname);

        String strName[]=new String[6];
        String strLocationX[]=new String[6];
        String strLocationY[]=new String[6];
        skiname.setText("안녕");

        skiDb=new SkiDb(this);

        sqlDb=skiDb.getReadableDatabase();
        skiDb.onUpgrade(sqlDb,1,2);

        cursor = sqlDb.rawQuery("SELECT * FROM skiTable;",null);


        //아까선언한 변수에 스키장 이름,X좌표,y좌표값 넣기
        for(int i=0; i<4;i++){
            cursor.moveToNext();

            strName[i]=cursor.getString(0);
            strLocationX[i]=cursor.getString(1);
            strLocationY[i]=cursor.getString(2);

        }


        skiname.setText(strName[3]);           //다른 변수에 넣을 수도 있다. 나중에 실제 활용


        cursor.close();                                 //커서닫기


        sqlDb.close();                         //db닫기












    }

}

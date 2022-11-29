package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SkiDb extends SQLiteOpenHelper{
    public SkiDb(Context context) {
        super(context, "SkiDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String skiLocationX[] = new String[6];                  //스키장 좌표 x값 입력
        skiLocationX[0]="128.671107441769";                 //알펜시아리조트 x좌표
        skiLocationX[1]="127.68202103965271";               //소노벨비발디파크 x좌표
        skiLocationX[2]="128.68049940814703";                              //용평리조트 x좌표
        skiLocationX[3]="128.82597024482814";               //하이원리조트 X좌표
        skiLocationX[4]="128.32745403546068";               //휘닉스 평창 X좌표
        skiLocationX[5]="127.29409505765865";               //곤지암 리조트 X좌표

        String skiLocationY[] = new String[6];                 //스키장 좌표 y값 입력
        skiLocationY[0]="37.6542853020104";                     //알펜시아리조트 y좌표
        skiLocationY[1]="37.64508331765885";                    //소노벨비발디파크 y좌표
        skiLocationY[2]="37.6457736234671";                     //용평리조트 Y좌표
        skiLocationY[3]="37.20814465709975";                    //하이원리조트 y좌표
        skiLocationY[4]="37.58132759335204";                    //휘닉스 평창 Y좌표
        skiLocationY[5]="37.33798433777639";                    //곤지암 리조트 Y좌표

        sqLiteDatabase.execSQL("CREATE TABLE SkiTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100));");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "알펜시아" + "',"+skiLocationX[0]+","+skiLocationY[0]+")");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "소노벨비발디파크" + "',"+skiLocationX[1]+","+skiLocationY[1]+")");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "용평리조트" + "',"+skiLocationX[2]+","+skiLocationY[2]+")");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "하이원리조트" + "',"+skiLocationX[3]+","+skiLocationY[3]+")");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "휘닉스평창" + "',"+skiLocationX[4]+","+skiLocationY[4]+")");
        sqLiteDatabase.execSQL("INSERT INTO SkiTable VALUES('"+ "곤지암리조트" + "',"+skiLocationX[5]+","+skiLocationY[5]+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SkiTable");
        onCreate(sqLiteDatabase);

    }
}



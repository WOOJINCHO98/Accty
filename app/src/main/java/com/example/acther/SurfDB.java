package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SurfDB extends SQLiteOpenHelper{
    public SurfDB(Context context) {
        super(context, "SurfDB", null, 1);
    }

    /*
    *       "address_name": "강원 양양군 강현면 전진리",
      "category_group_code": "AT4",
      "category_group_name": "관광명소",
      "category_name": "여행 > 관광,명소 > 해수욕장,해변",
      "distance": "",
      "id": "8229967",
      "phone": "033-670-2518",
      "place_name": "낙산해변",
      "place_url": "http://place.map.kakao.com/8229967",
      "road_address_name": "",
      "x": "128.635755922233",
      "y": "38.115871436438"
    },
    {*/


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String skiLocationX[] = new String[6];                  //스키장 좌표 x값 입력
        skiLocationX[0]="128.635755922233";                 //낙산해변 x좌표
        skiLocationX[1]="127.68202103965271";               //소노벨비발디파크 x좌표
        skiLocationX[2]="128.68049940814703";                              //용평리조트 x좌표
        skiLocationX[3]="128.82597024482814";               //하이원리조트 X좌표
        skiLocationX[4]="128.32745403546068";               //휘닉스 평창 X좌표
        skiLocationX[5]="127.29409505765865";               //곤지암 리조트 X좌표

        String skiLocationY[] = new String[6];                 //스키장 좌표 y값 입력
        skiLocationY[0]="38.115871436438";                     //낙산해변 y좌표
        skiLocationY[1]="37.64508331765885";                    //소노벨비발디파크 y좌표
        skiLocationY[2]="37.6457736234671";                     //용평리조트 Y좌표
        skiLocationY[3]="37.20814465709975";                    //하이원리조트 y좌표
        skiLocationY[4]="37.58132759335204";                    //휘닉스 평창 Y좌표
        skiLocationY[5]="37.33798433777639";                    //곤지암 리조트 Y좌표

        String surfPhone[] = new String[6];                 // 전화번호 입력

        surfPhone[0]="033-670-2518";                 //낙산해변 전화번호

        String surfUrl[]  = new String[6];                 //url 입력
        surfUrl[0]="http://place.map.kakao.com/8229967";                 //낙산해변 url

        String address[] = new String[6];                 //주소 입력
        address[0]="강원 양양군 강현면 전진리";                 //낙산해변 주소


        sqLiteDatabase.execSQL("CREATE TABLE SkiTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100),"+"phone CHAR(100),"+"url CHAR(100),"+"address CHAR(100))");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "낙산해변" + "',"+skiLocationX[0]+","+skiLocationY[0]+","+surfPhone[0]+","+surfUrl[0]+","+address[0]+")");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "소노벨비발디파크" + "',"+skiLocationX[1]+","+skiLocationY[1]+")");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "용평리조트" + "',"+skiLocationX[2]+","+skiLocationY[2]+")");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "하이원리조트" + "',"+skiLocationX[3]+","+skiLocationY[3]+")");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "휘닉스평창" + "',"+skiLocationX[4]+","+skiLocationY[4]+")");
        sqLiteDatabase.execSQL("INSERT INTO skiTable VALUES('"+ "곤지암리조트" + "',"+skiLocationX[5]+","+skiLocationY[5]+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS skiTable");
        onCreate(sqLiteDatabase);

    }
}



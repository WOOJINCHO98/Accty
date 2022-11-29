package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SurfingDb extends SQLiteOpenHelper{
    public SurfingDb(Context context) {
        super(context, "SurfingDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String surfingLocationX[] = new String[4];
        surfingLocationX[0]="128.71728231189653";            //양양 서피비치 X좌표
        surfingLocationX[1]="126.411498674889";            //제주 중문색달해변 x좌표
        surfingLocationX[2]="129.199700145244";          //부산 송정해수욕장 X좌표
        surfingLocationX[3]="128.8330927476365";          //포항 신항만 X좌표


        String surfingLocationY[] = new String[4];
        surfingLocationY[0]="38.027834441415756";            //양양 서피비치 Y좌표
        surfingLocationY[1]="33.2450381505136";            //제주 중문색달해변 Y좌표
        surfingLocationY[2]="35.1785969384698";          //부산 송정해수욕장 Y좌표
        surfingLocationY[3]="35.078096189228205";           //포항 신항만 Y좌표



        sqLiteDatabase.execSQL("CREATE TABLE SurfingTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100),"+"addressName CHAR(100),"+"phone CHAR(100),"+"placeUrl CAHR(100))");

        sqLiteDatabase.execSQL("INSERT INTO SurfingTable VALUES('"+ "양양 서피비치" + "',"+surfingLocationX[0]+","+surfingLocationY[0]+"," +
                "'"+"강원 양양군 현북면 중광정리 508"+"','"+"1522-2729"+"','"+"http://place.map.kakao.com/26911134"+"')");
        sqLiteDatabase.execSQL("INSERT INTO SurfingTable VALUES('"+ "제주 중문색달해변" + "',"+surfingLocationX[1]+","+surfingLocationY[1]+"," +
                "'"+"제주특별자치도 서귀포시 색달동 3039"+"','"+"064-739-4993"+"','"+"http://place.map.kakao.com/8069764"+"')");
        sqLiteDatabase.execSQL("INSERT INTO SurfingTable VALUES('"+ "부산 송정해수욕장" + "',"+surfingLocationX[2]+","+surfingLocationY[2]+"," +
                "'"+"부산 해운대구 송정동 438-11"+"','"+"051-749-7612"+"','"+"http://place.map.kakao.com/20541735"+"')");
        sqLiteDatabase.execSQL("INSERT INTO SurfingTable VALUES('"+ "포항 신항만" + "',"+surfingLocationX[3]+","+surfingLocationY[3]+"," +
                "'"+"경북 포항시 북구 흥해읍 용한리 853"+"','"+"054-250-8000"+"','"+"http://place.map.kakao.com/779408191"+"')");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SurfingTable");
        onCreate(sqLiteDatabase);

    }
}



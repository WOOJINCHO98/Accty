package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class ParaDb extends SQLiteOpenHelper{
    public ParaDb(Context context) {
        super(context, "ParaDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String paraLocationX[] = new String[4];
        paraLocationX[0]="128.394355757571";            //단양 패러에반하다 X좌표
        paraLocationX[1]="127.463712458009";            //양평 패러러브 x좌표
        paraLocationX[2]="127.46964859025132";          //구례 지리산 패러글라이딩 X좌표
        paraLocationX[3]="126.53142302810325";          //제주 하늘을날다 X좌표


        String paraLocationY[] = new String[4];
        paraLocationY[0]="36.9946772072196";            //단양 패러에반하다 Y좌표
        paraLocationY[1]="37.5593868849475";            //양평 패러러브 Y좌표
        paraLocationY[2]="35.182717560530584";          //구례 지리산 패러글라이딩 Y좌표
        paraLocationY[3]="33.49112152644723";           //제주 하늘을날다 Y좌표



        sqLiteDatabase.execSQL("CREATE TABLE ParaTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100));");
        sqLiteDatabase.execSQL("INSERT INTO ParaTable VALUES('"+ "단양 패러에반하다" + "',"+paraLocationX[0]+","+paraLocationY[0]+")");
        sqLiteDatabase.execSQL("INSERT INTO ParaTable VALUES('"+ "양평 패러러브" + "',"+paraLocationX[1]+","+paraLocationY[1]+")");
        sqLiteDatabase.execSQL("INSERT INTO ParaTable VALUES('"+ "구례 지리산 패러글라이딩" + "',"+paraLocationX[2]+","+paraLocationY[2]+")");
        sqLiteDatabase.execSQL("INSERT INTO ParaTable VALUES('"+ "제주 하늘을날다" + "',"+paraLocationX[3]+","+paraLocationY[3]+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ParaTable");
        onCreate(sqLiteDatabase);

    }
}



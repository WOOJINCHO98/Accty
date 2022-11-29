package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FishingDb extends SQLiteOpenHelper{
    public FishingDb(Context context) {
        super(context, "FishingDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String fishingLocationX[] = new String[6];
        fishingLocationX[0]="127.399405023335";         //진천 백곡저수지 X좌표
        fishingLocationX[1]="126.904330348492";         //파주 발랑저수지 x좌표
        fishingLocationX[2]="126.80802161791";          //예산 예당저수지 X좌표
        fishingLocationX[3]="126.916658767227";         //화성 어천저수지 X좌표
        fishingLocationX[4]="127.750823918294";         //괴산 문광저수지 X좌표
        fishingLocationX[5]="126.969389914673";         //양주 효촌저수지 X좌표


        String fishingLocationY[] = new String[6];
        fishingLocationY[0]="36.8644483767213";         //진천 백곡저수지 Y좌표
        fishingLocationY[1]="37.8076708184058";         //파주 발랑저수지 Y좌표
        fishingLocationY[2]="36.5973889041746";         //예산 예당저수지 Y좌표
        fishingLocationY[3]="37.2539429004408";         //화정 어천저수지 Y좌표
        fishingLocationY[4]="36.7679318269534";         //괴산 문광저수지 Y좌표
        fishingLocationY[5]="37.8727005094843";         //양주 효촌저수지 Y좌표

        sqLiteDatabase.execSQL("CREATE TABLE FishingTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100));");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "진천 백곡저수지" + "',"+fishingLocationX[0]+","+fishingLocationY[0]+")");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "파주 발랑저수지" + "',"+fishingLocationX[1]+","+fishingLocationY[1]+")");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "예산 예당저수지" + "',"+fishingLocationX[2]+","+fishingLocationY[2]+")");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "화성 어천저수지" + "',"+fishingLocationX[3]+","+fishingLocationY[3]+")");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "괴산 문광저수지" + "',"+fishingLocationX[4]+","+fishingLocationY[4]+")");
        sqLiteDatabase.execSQL("INSERT INTO FishingTable VALUES('"+ "양주 효촌저수지" + "',"+fishingLocationX[5]+","+fishingLocationY[5]+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FishingTable");
        onCreate(sqLiteDatabase);

    }
}



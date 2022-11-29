package com.example.acther;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class GolfDb extends SQLiteOpenHelper{
    public GolfDb(Context context) {
        super(context, "GolfDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String golfLocationX[] = new String[6];
        golfLocationX[0]="128.89838394990886";          //가야CC x좌표
        golfLocationX[1]="127.62746754370224";          //남여주GC X좌표
        golfLocationX[2]="128.811172655447";            //대구CC X좌표
        golfLocationX[3]="126.90032431162174";          //파주cc x좌표
        golfLocationX[4]="127.5717471367287";           //자유cc x좌표
        golfLocationX[5]="129.0401779338762";           //양산cc x좌표

//

        String golfLocationY[] = new String[6];
        golfLocationY[0]="35.27397158170206";           //가야CC y좌표
        golfLocationY[1]="37.22695192606714";           //남여주GC y좌표
        golfLocationY[2]="35.8710762892894";         //대구gc y좌표
        golfLocationY[3]="37.84632300168206";           //파주cc y좌표
        golfLocationY[4]="37.208650141881655";          //자유cc Y좌표
        golfLocationY[5]="35.40228331789821";           //양산cc y좌표

        sqLiteDatabase.execSQL("CREATE TABLE GolfTable(placeName CHAR(40) PRIMARY KEY," +
                "locationX CHAR(100),"+"locationY CHAR(100),"+"addressName CHAR(100),"+"phone CHAR(100),"+"placeUrl CAHR(100))");

        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "가야CC" + "',"+golfLocationX[0]+","+golfLocationY[0]+"," +
                "'"+"경남 김해시 삼방동 산 1"+"','"+"055-337-8611"+"','"+"http://place.map.kakao.com/25039206"+"')");
        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "남여주GC" + "',"+golfLocationX[1]+","+golfLocationY[1]+"," +
                "'"+"경기 여주시 하거동 산 40"+"','"+"031-880-6700"+"','"+"http://place.map.kakao.com/25021687"+"')");
        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "대구GC" + "',"+golfLocationX[2]+","+golfLocationY[2]+"," +
                "'"+"경북 경산시 진량읍 선화리 67-2"+"','"+"053-854-0002"+"','"+"http://place.map.kakao.com/10261081"+"')");
        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "파주CC" + "',"+golfLocationX[3]+","+golfLocationY[3]+"," +
                "'"+"경기 파주시 법원읍 갈곡리 360"+"','"+"031-959-9999"+"','"+"http://place.map.kakao.com/25070559"+"')");
        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "자유CC" + "',"+golfLocationX[4]+","+golfLocationY[4]+"," +
                "'"+"경기 여주시 가남읍 삼군리 468-4"+"','"+"031-887-7700"+"','"+"http://place.map.kakao.com/8539802"+"')");
        sqLiteDatabase.execSQL("INSERT INTO GolfTable VALUES('"+ "양산CC" + "',"+golfLocationX[5]+","+golfLocationY[5]+"," +
                "'"+"경기 남양주시 화도읍 금남리 300"+"','"+"031-592-6060"+"','"+"http://place.map.kakao.com/12857261"+"')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS GolfTable");
        onCreate(sqLiteDatabase);
    }
}



package com.example.acther;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    FlagMaker flagMaker = new FlagMaker();

    private static final String TAG = "MainActivity";

    SQLiteDatabase sqlDb;
    SkiDb skiDb;
    ImageView settingBtn;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,settingBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flagMaker.makeFlag();

/*
        //권한 물어보기
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.INTERNET"},
                0);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.ACCESS_FINE_LOCATION"},
                0);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.ACCESS_COURCE_LOCATION"},
                0);

        */

        // splash 액티비티의 make flag 실행하기
        List a = ((SplashActivity)SplashActivity.mContext).makeFlag();




        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){ //포그라운드 위치 권한 확인

            //위치 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }


        skiDb=new SkiDb(this);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        settingBtn = (ImageView) findViewById(R.id.settingBtn);

        /*
        //스키장에 필요한 정보
        String skiName[] = new String[6];                        //스키장 실제 이름값 입력
        skiName[0] ="알펜시아2";
        skiName[1] ="소노벨비발디파크";
        skiName[2] ="용평리조트";
        skiName[3]="하이원리조트";
        skiName[4]="휘닉스 평창";
        skiName[5]="곤지암 리조트";

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




        sqlDb=skiDb.getReadableDatabase();

        skiDb.onUpgrade(sqlDb,1,2);
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "알펜시아" + "',"+skiLocationX[0]+","+skiLocationY[0]+")");
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "소노벨비발디파크" + "',"+skiLocationX[1]+","+skiLocationY[1]+")");
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "용평리조트" + "',"+skiLocationX[2]+","+skiLocationY[2]+")");
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "하이원리조트" + "',"+skiLocationX[3]+","+skiLocationY[3]+")");
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "휘닉스평창" + "',"+skiLocationX[4]+","+skiLocationY[4]+")");
        sqlDb.execSQL("INSERT INTO skiTable VALUES('"+ "곤지암리조트" + "',"+skiLocationX[5]+","+skiLocationY[5]+")");

        sqlDb.close();


*/


        Intent fcm = new Intent(getApplicationContext(), MyFirebaseMessaging.class);
        startService(fcm);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RunningActivity.class);
                startActivity(intent);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GolfSelectActivity.class);
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CycleActivity.class);
                startActivity(intent);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GlidingSelectActivity.class);
                startActivity(intent);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FishingSelectActivity.class);
                startActivity(intent);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SurfingSelectActivity.class);
                startActivity(intent);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SkiSelectActivity.class);
                startActivity(intent);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HikingActivity.class);
                startActivity(intent);

            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);


            }
        });





        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        System.out.println(msg);
                    }
                });




    }




}


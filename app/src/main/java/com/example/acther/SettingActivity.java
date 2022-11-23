package com.example.acther;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingActivity extends AppCompatActivity {

    Button setTime,notification_btn;

    static CheckBox running;
    static CheckBox cycling;
    static CheckBox hiking;
    static CheckBox gliding;
    static CheckBox surfing;
    static CheckBox fishing;
    static CheckBox golf;
    static CheckBox ski;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Notifications permission granted",Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(this, "FCM can't post notifications without POST_NOTIFICATIONS permission",
                            Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        // -----------
        notification_btn = (Button) findViewById(R.id.notification_btn);
        setTime = (Button) findViewById(R.id.setTime);
        //-------------
        running = (CheckBox) findViewById(R.id.running);
        cycling = (CheckBox) findViewById(R.id.cycling);
        hiking = (CheckBox) findViewById(R.id.hiking);
        gliding = (CheckBox) findViewById(R.id.gliding);
        surfing = (CheckBox) findViewById(R.id.surfing);
        fishing = (CheckBox) findViewById(R.id.fishing);
        golf = (CheckBox) findViewById(R.id.golf);
        ski = (CheckBox) findViewById(R.id.ski);
        //--------------------------------
/*
        //알림(Notification)을 관리하는 관리자 객체를 운영체제(Context)로부터 소환하기
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification 객체를 생성해주는 건축가객체 생성(AlertDialog 와 비슷)
        NotificationCompat.Builder builder= null;

        //Oreo 버전(API26 버전)이상에서는 알림시에 NotificationChannel 이라는 개념이 필수 구성요소가 됨.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            String channelID="channel_01"; //알림채널 식별자
            String channelName="MyChannel01"; //알림채널의 이름(별명)

            //알림채널 객체 만들기
            NotificationChannel channel= new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

            //알림매니저에게 채널 객체의 생성을 요청
            notificationManager.createNotificationChannel(channel);

            //알림건축가 객체 생성
            builder=new NotificationCompat.Builder(this, channelID);


        }else{
            //알림 건축가 객체 생성
            builder= new NotificationCompat.Builder(this, (Notification) null);
        }

        //건축가에게 원하는 알림의 설정작업
        builder.setSmallIcon(android.R.drawable.ic_menu_view);

        //상태바를 드래그하여 아래로 내리면 보이는
        //알림창(확장 상태바)의 설정
        builder.setContentTitle("Title");//알림창 제목
        builder.setContentText("Messages....");//알림창 내용
        //알림창의 큰 이미지
        Bitmap bm= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
        builder.setLargeIcon(bm);//매개변수가 Bitmap을 줘야한다.

        //건축가에게 알림 객체 생성하도록
        Notification notification=builder.build();



        //알림매니저에게 알림(Notify) 요청
        notificationManager.notify(1, notification);

        //알림 요청시에 사용한 번호를 알림제거 할 수 있음.
        //notificationManager.cancel(1);

        askNotificationPermission();


*/

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), A_Alarm.class);
                startActivity(intent);
            }
        });
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        getIsChecked();
    }


    private void askNotificationPermission() {
        // This is only necessary for API Level > 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    public static Integer getIsChecked() {
        Integer isCheckedRunning = 0;
        Integer isCheckedGolf = 0;
        Integer isCheckedCycle = 0;
        Integer isCheckedGild = 0;
        Integer isCheckedFish = 0;
        Integer isCheckedSurf = 0;
        Integer isCheckedSki = 0;
        Integer isCheckedHiking = 0;

        if (running.isChecked()) {
            isCheckedRunning = 1;
            System.out.println("runningFlag : " + isCheckedRunning);
        }
        else {
            isCheckedRunning = 0;
            System.out.println("runningFlag : " + isCheckedRunning);
        }
        if (golf.isChecked()) {
            isCheckedGolf = 1;
            System.out.println("golfFlag : " + isCheckedGolf);
        }
        else {
            isCheckedGolf = 0;
            System.out.println("golfFlag : " + isCheckedGolf);
        }
        if (cycling.isChecked()) {
            isCheckedCycle = 1;
            System.out.println("cycleFlag : " + isCheckedCycle);
        }
        else {
            isCheckedCycle = 0;
            System.out.println("cycleFlag : " + isCheckedCycle);
        }
        if (gliding.isChecked()) {
            isCheckedGild = 1;
            System.out.println("gildFlag : " + isCheckedGild);
        }
        else {
            isCheckedGild = 0;
            System.out.println("gildFlag : " + isCheckedGild);
        }
        if (fishing.isChecked()) {
            isCheckedFish = 1;
            System.out.println("fishFlag : " + isCheckedFish);
        }
        else {
            isCheckedFish = 0;
            System.out.println("fishFlag : " + isCheckedFish);
        }
        if (surfing.isChecked()) {
            isCheckedSurf = 1;
            System.out.println("surfFlag : " + isCheckedSurf);
        }
        else {
            isCheckedSurf = 0;
            System.out.println("surfFlag : " + isCheckedSurf);
        }
        if (ski.isChecked()) {
            isCheckedSki = 1;
            System.out.println("skiFlag : " + isCheckedSki);
        }
        else {
            isCheckedSki = 0;
            System.out.println("skiFlag : " + isCheckedSki);
        }
        if (hiking.isChecked()) {
            isCheckedHiking = 1;
            System.out.println("hikingFlag : " + isCheckedHiking);
        }
        else {
            isCheckedHiking = 0;
            System.out.println("hikingFlag : " + isCheckedHiking);
        }

        return isCheckedCycle;
    }




}

package com.example.acther;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
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

    ImageView btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.INTERNET"},
                0);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.ACCESS_FINE_LOCATION"},
                0);

        btn1 = (ImageView) findViewById(R.id.btn1);
        btn2 = (ImageView) findViewById(R.id.btn2);
        btn3 = (ImageView) findViewById(R.id.btn3);

        Intent fcm = new Intent(getApplicationContext(), MyFirebaseMessaging.class);
        startService(fcm);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CycleActivity.class);
                startActivity(intent);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FishingActivity.class);
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GlidingActivity.class);
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
}


package com.example.acther;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingActivity extends AppCompatActivity {

    Button setTime;

    static ImageButton running;
    static ImageButton cycling;
    static ImageButton hiking;
    static ImageButton gliding;
    static ImageButton surfing;
    static ImageButton fishing;
    static ImageButton golf;
    static ImageButton ski;

    public String isRunningChecked;
    public String isCyclingChecked;
    public String isHikingChecked;
    public String isGlidingChecked;
    public String isSurfingChecked;
    public String isFishingChecked;
    public String isGolfChecked;
    public String isSkiChecked;




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
        setTime = (Button) findViewById(R.id.setTime);
        //-------------
        running = (ImageButton) findViewById(R.id.running);
        cycling = (ImageButton) findViewById(R.id.cycling);
        hiking = (ImageButton) findViewById(R.id.hiking);
        gliding = (ImageButton) findViewById(R.id.gliding);
        surfing = (ImageButton) findViewById(R.id.surfing);
        fishing = (ImageButton) findViewById(R.id.fishing);
        golf = (ImageButton) findViewById(R.id.golf);
        ski = (ImageButton) findViewById(R.id.ski);
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

        //프리퍼런스 불러오기]
        isRunningChecked = S_Preference.getString(getApplication(), "isRunningChecked");
        isCyclingChecked = S_Preference.getString(getApplication(), "isCyclingChecked");
        isHikingChecked = S_Preference.getString(getApplication(), "isHikingChecked");
        isGlidingChecked = S_Preference.getString(getApplication(), "isGlidingChecked");
        isSurfingChecked = S_Preference.getString(getApplication(), "isSurfingChecked");
        isFishingChecked = S_Preference.getString(getApplication(), "isFishingChecked");
        isGolfChecked = S_Preference.getString(getApplication(), "isGolfChecked");
        isSkiChecked = S_Preference.getString(getApplication(), "isSkiChecked");



        if (isRunningChecked.equals("false")) {
            running.setImageResource(R.drawable.running90);
        } else {
            running.setImageResource(R.drawable.run_b);
        }
        if (isCyclingChecked.equals("false")) {
            cycling.setImageResource(R.drawable.cycling_icon);
        } else {
            cycling.setImageResource(R.drawable.cycle_b);
        }
        if (isHikingChecked.equals("false")) {
            hiking.setImageResource(R.drawable.trekking_icon);
        } else {
            hiking.setImageResource(R.drawable.hiking_b);
        }
        if (isGlidingChecked.equals("false")) {
            gliding.setImageResource(R.drawable.paragliding_icon);
        } else {
            gliding.setImageResource(R.drawable.glide_b);
        }
        if (isSurfingChecked.equals("false")) {
            surfing.setImageResource(R.drawable.surf90);
        } else {
            surfing.setImageResource(R.drawable.surf_b);
        }
        if (isFishingChecked.equals("false")) {
            fishing.setImageResource(R.drawable.fishing_icon);
        } else {
            fishing.setImageResource(R.drawable.fish_b);
        }
        if (isGolfChecked.equals("false")) {
            golf.setImageResource(R.drawable.golf_icon);
        } else {
            golf.setImageResource(R.drawable.golf_b);
        }
        if (isSkiChecked.equals("false")) {
            ski.setImageResource(R.drawable.skking_icon);
        } else {
            ski.setImageResource(R.drawable.ski_b);
        }





        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), A_Alarm.class);
                startActivity(intent);
            }
        });




        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isRunningChecked = S_Preference.getString(getApplication(), "isRunningChecked");

                System.out.println("isRunningChecked : " + isRunningChecked);

                if (isRunningChecked.equals("true")) {
                    running.setImageResource(R.drawable.running90);
                    S_Preference.setString(getApplication(), "isRunningChecked", "false");
                    Toast.makeText(getApplicationContext(), "러닝 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    running.setImageResource(R.drawable.run_b);
                    S_Preference.setString(getApplication(), "isRunningChecked", "true");
                    Toast.makeText(getApplicationContext(), "러닝 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //running.setSelected(!running.isSelected());

            }
        });


        cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isCyclingChecked = S_Preference.getString(getApplication(), "isCyclingChecked");

                System.out.println("isCyclingChecked : " + isCyclingChecked);

                if (isCyclingChecked.equals("true")) {
                    //cycling.setImageResource(R.drawable.cycling_icon);
                    cycling.setImageResource(R.drawable.cycling_icon);
                    S_Preference.setString(getApplication(), "isCyclingChecked", "false");
                    Toast.makeText(getApplicationContext(), "싸이클 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    //cycling.setImageResource(R.drawable.ic_launcher_background);
                    cycling.setImageResource(R.drawable.cycle_b);
                    S_Preference.setString(getApplication(), "isCyclingChecked", "true");
                    Toast.makeText(getApplicationContext(), "싸이클 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });


        hiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isHikingChecked = S_Preference.getString(getApplication(), "isHikingChecked");

                System.out.println("isHikingChecked : " + isHikingChecked);

                if (isHikingChecked.equals("true")) {
                    hiking.setImageResource(R.drawable.trekking_icon);
                    S_Preference.setString(getApplication(), "isHikingChecked", "false");
                    Toast.makeText(getApplicationContext(), "하이킹 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    hiking.setImageResource(R.drawable.hiking_b);
                    S_Preference.setString(getApplication(), "isHikingChecked", "true");
                    Toast.makeText(getApplicationContext(), "하이킹 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });

        gliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isGlidingChecked = S_Preference.getString(getApplication(), "isGlidingChecked");

                System.out.println("isGlidingChecked : " + isGlidingChecked);

                if (isGlidingChecked.equals("true")) {
                    gliding.setImageResource(R.drawable.paragliding_icon);
                    S_Preference.setString(getApplication(), "isGlidingChecked", "false");
                    Toast.makeText(getApplicationContext(), "글라이딩 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    gliding.setImageResource(R.drawable.glide_b);
                    S_Preference.setString(getApplication(), "isGlidingChecked", "true");
                    Toast.makeText(getApplicationContext(), "글라이딩 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });

        surfing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isSurfingChecked = S_Preference.getString(getApplication(), "isSurfingChecked");

                System.out.println("isSurfingChecked : " + isSurfingChecked);

                if (isSurfingChecked.equals("true")) {
                    surfing.setImageResource(R.drawable.surf90);
                    S_Preference.setString(getApplication(), "isSurfingChecked", "false");
                    Toast.makeText(getApplicationContext(), "서핑 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    surfing.setImageResource(R.drawable.surf_b);
                    S_Preference.setString(getApplication(), "isSurfingChecked", "true");
                    Toast.makeText(getApplicationContext(), "서핑 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });

        fishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFishingChecked = S_Preference.getString(getApplication(), "isFishingChecked");

                System.out.println("isFishingChecked : " + isFishingChecked);

                if (isFishingChecked.equals("true")) {
                    fishing.setImageResource(R.drawable.fishing_icon);
                    S_Preference.setString(getApplication(), "isFishingChecked", "false");
                    Toast.makeText(getApplicationContext(), "낚시 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    fishing.setImageResource(R.drawable.fish_b);
                    S_Preference.setString(getApplication(), "isFishingChecked", "true");
                    Toast.makeText(getApplicationContext(), "낚시 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });

        golf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isGolfChecked = S_Preference.getString(getApplication(), "isGolfChecked");

                System.out.println("isGolfChecked : " + isGolfChecked);

                if (isGolfChecked.equals("true")) {
                    golf.setImageResource(R.drawable.golf_icon);
                    S_Preference.setString(getApplication(), "isGolfChecked", "false");
                    Toast.makeText(getApplicationContext(), "골프 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    golf.setImageResource(R.drawable.golf_b);
                    S_Preference.setString(getApplication(), "isGolfChecked", "true");
                    Toast.makeText(getApplicationContext(), "골프 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });

        ski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isSkiChecked = S_Preference.getString(getApplication(), "isSkiChecked");

                System.out.println("isSkiChecked : " + isSkiChecked);

                if (isSkiChecked.equals("true")) {
                    ski.setImageResource(R.drawable.skking_icon);
                    S_Preference.setString(getApplication(), "isSkiChecked", "false");
                    Toast.makeText(getApplicationContext(), "스키 알람이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    ski.setImageResource(R.drawable.ski_b);
                    S_Preference.setString(getApplication(), "isSkiChecked", "true");
                    Toast.makeText(getApplicationContext(), "스키 알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                //cycling.setSelected(!cycling.isSelected());

            }
        });


        // activity finish if keycode back








    }

    @Override
    public void onBackPressed() {

        finish();

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








    /*
    public static Integer getIsChecked() {
        Integer isCheckedRunning = 0;
        Integer isCheckedGolf = 0;
        Integer isCheckedCycle = 0;
        Integer isCheckedGild = 0;
        Integer isCheckedFish = 0;
        Integer isCheckedSurf = 0;
        Integer isCheckedSki = 0;
        Integer isCheckedHiking = 0;
*/

        /*
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

*/


}

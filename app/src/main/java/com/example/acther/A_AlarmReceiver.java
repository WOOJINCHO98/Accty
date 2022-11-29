package com.example.acther;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class A_AlarmReceiver extends BroadcastReceiver {

    //TODO [클래스 전역 변수 선언 부분]
    Context mContext;



    //TODO [onReceive 메소드를 재정의 (채널 알림 메시지 확인 부분)]
    @Override
    public void onReceive(Context context, Intent intent) {

        A_Alarm tAlarm = new A_Alarm();

        String whatIsChecked = tAlarm.whatIsChecked;


        SplashActivity flagMaker = new SplashActivity();

        ((SplashActivity)SplashActivity.mContext).makeFlag(); // 알림 발생 시 makeFlag() 메소드 호출


        try {
            mContext = context;
            Log.d("---", "---");
            Log.w("//===========//", "================================================");
            Log.d("", "\n" + "[A_AlarmReceiver > onReceive() 메소드 : 수신받은 알림 내용 확인]");
            Log.w("//===========//", "================================================");
            Log.d("---", "---");


            String header =  "";
            String body ="";



            // 만약 러닝이 선택되어 있고, 러닝에 대한 알림이 발생했다면?
            if (whatIsChecked.equals("running")){
                if (Integer.parseInt(flagMaker.hMap.get("Running") )== 1) {
                    header = "러닝하기 좋은 날 입니다.";
                    body = "러닝을 하러 가볼까요?";
                }
                else {
                    header = "러닝하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("cycling")){
                if (Integer.parseInt(flagMaker.hMap.get("Cycle") )== 1) {
                    header = "사이클링하기 좋은 날 입니다.";
                    body = "사이클링을 하러 가볼까요?";
                }
                else {
                    header = "사이클링하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("hiking")){
                if (Integer.parseInt(flagMaker.hMap.get("Hiking") )== 1) {
                    header = "등산하기 좋은 날 입니다.";
                    body = "등산을 하러 가볼까요?";
                }
                else {
                    header = "등산하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("gliding")){
                if (Integer.parseInt(flagMaker.hMap.get("Paragliding") )== 1) {
                    header = "패러글라이딩하기 좋은 날 입니다.";
                    body = "패러글라이딩을 하러 가볼까요?";
                }
                else {
                    header = "패러글라이딩하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("surfing")){
                if (Integer.parseInt(flagMaker.hMap.get("surfing") )== 1) {
                    header = "서핑하기 좋은 날 입니다.";
                    body = "서핑을 하러 가볼까요?";
                }
                else {
                    header = "서핑하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("fishing")){
                if (Integer.parseInt(flagMaker.hMap.get("fishing") )== 1) {
                    header = "낚시하기 좋은 날 입니다.";
                    body = "낚시를 하러 가볼까요?";
                }
                else {
                    header = "낚시하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("golf")){
                if (Integer.parseInt(flagMaker.hMap.get("Golf") )== 1) {
                    header = "골프하기 좋은 날 입니다.";
                    body = "골프를 하러 가볼까요?";
                }
                else {
                    header = "골프하기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else if (whatIsChecked.equals("ski")){
                if (Integer.parseInt(flagMaker.hMap.get("Ski") )== 1) {
                    header = "스키타기 좋은 날 입니다.";
                    body = "스키를 타러 가볼까요?";
                }
                else {
                    header = "스키타기 좋은 날이 아닙니다.";
                    body = "집에서 할 수 있는 운동은 어떨까요?";
                }
            }
            else{
                header = "운동을 선택하지 않았습니다";
                body = "설정을 확인해주세요";
            }







/*
            body += " 오늘의 평균 온도는 약 " + a.get(7).toString() + "도 입니다. \n";
            body += " 오늘의 평균 강수량은 " + a.get(8).toString() + "mm 입니다. ";
*/


            //TODO 노티피케이션 알림 호출 실시
            Toast.makeText(mContext, S_Preference.getString(mContext, "Alarm_Date") + " [알림]", Toast.LENGTH_SHORT).show();
            serviceTittle = header;
            serviceContent = body;
            setNotificationShow();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO [노티피케이션 알림 표시 부분]
    NotificationManager notificationManager; //매니저
    NotificationChannel notificationChannel; //채널
    NotificationCompat.Builder builder; //빌더
    String serviceTittle = "";
    String serviceContent = "";

    public void setNotificationShow() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //TODO 오레오 버전 이상
                //오레오 버전 이상부터는 노티피케이션을 사용하기 위해서 채널이 필요하다
                int importance = NotificationManager.IMPORTANCE_HIGH; //TODO 알림 우선순위를 최상으로 설정 (타이틀 및 소리)
                String Noti_Channel_ID = "DefaultNoti";
                String Noti_Channel_Group_ID = "DefaultNoti_Group";
                notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationChannel = new NotificationChannel(Noti_Channel_ID, Noti_Channel_Group_ID, importance);
                //TODO 뱃지 카운트 실시 (디폴트 true)
                //notificationChannel.setShowBadge(true);
                if (notificationManager.getNotificationChannel(Noti_Channel_ID) != null) { //TODO 채널이 존재할 경우
                    Log.d("---", "---");
                    Log.w("//===========//", "================================================");
                    Log.d("", "\n" + "[A_ImmotalServiceReceiver > setNotificationShow() 메소드 : 채널이 이미 존재합니다]");
                    Log.w("//===========//", "================================================");
                    Log.d("---", "---");
                } else {
                    Log.d("---", "---");
                    Log.e("//===========//", "================================================");
                    Log.d("", "\n" + "[A_ImmotalServiceReceiver > setNotificationShow() 메소드 : 채널이 없어서 만듭니다]");
                    Log.e("//===========//", "================================================");
                    Log.d("---", "---");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.createNotificationChannel(notificationChannel);
                builder = new NotificationCompat.Builder(mContext, Noti_Channel_ID)
                        .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background))
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setColor(ContextCompat.getColor(mContext, R.color.colorAccent))
                        .setWhen(System.currentTimeMillis())
                        .setShowWhen(true)
                        .setAutoCancel(true)
                        /**
                         .setOngoing(true) // 사용자가 직접 못지우게 계속 실행하기. (클릭해야 메시지 읽음 상태)
                         */
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentTitle(serviceTittle) //TODO 제목
                        .setChannelId(Noti_Channel_ID)
                        .setContentText(serviceContent); //TODO 내용

                builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL; //TODO 노티 알림 삭제 시 자동으로 푸시 뱃지 표시 지움

                notificationManager.notify(1, builder.build()); //TODO 아이디값이 달라야 노티피케이션이 여러개 표시된다

                getDisplayWakeUp(); //TODO 화면 강제 기상
            } else {
                builder = new NotificationCompat.Builder(mContext)
                        .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher_background))
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setColor(ContextCompat.getColor(mContext, R.color.colorAccent))
                        .setWhen(System.currentTimeMillis())
                        .setShowWhen(true)
                        .setAutoCancel(true)
                        /**
                         .setOngoing(true) // 사용자가 직접 못지우게 계속 실행하기. (클릭해야 메시지 읽음 상태)
                         */
                        .setPriority(NotificationCompat.PRIORITY_MAX) //TODO Priority와 Vibrator가 있어야 알림바 표시됨
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setContentTitle(serviceTittle) //TODO 제목
                        .setContentText(serviceContent); //TODO 내용

                builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL; //TODO 노티 알림 삭제 시 자동으로 푸시 뱃지 표시 지움

                notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build()); //TODO 아이디값이 달라야 노티피케이션이 여러개 표시된다

                getDisplayWakeUp(); //TODO 화면 강제 기상
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO [화면 기상 시키는 메소드]
    public void getDisplayWakeUp() {
        try {
            /**
             * [화면 기상 방법]
             * 1. 화면 제어 권한 획득 실시 - AndroidManifest.xml : <uses-permission android:name="android.permission.WAKE_LOCK"/>
             * 2. PowerManager.WakeLock 사용해 화면 강제 기상 (깨우기) 실시
             */
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "My:Tag");
            wakelock.acquire(); //TODO 화면 즉시 기상 실시
            wakelock.release(); //TODO WakeLock 자원 해제
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}//TODO 클래스 종료





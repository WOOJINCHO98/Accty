package com.example.acther;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class A_AlarmReceiver extends BroadcastReceiver {

    //TODO [클래스 전역 변수 선언 부분]
    Context mContext;

    //TODO [onReceive 메소드를 재정의 (채널 알림 메시지 확인 부분)]
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            mContext = context;
            Log.d("---","---");
            Log.w("//===========//","================================================");
            Log.d("","\n"+"[A_AlarmReceiver > onReceive() 메소드 : 수신받은 알림 내용 확인]");
            Log.w("//===========//","================================================");
            Log.d("---","---");

            //TODO 노티피케이션 알림 호출 실시
            Toast.makeText(mContext,S_Preference.getString(mContext, "Alarm_Date")+" [알림]",Toast.LENGTH_SHORT).show();
            serviceContent = S_Preference.getString(mContext, "Alarm_Date");
            setNotificationShow();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [노티피케이션 알림 표시 부분]
    NotificationManager notificationManager; //매니저
    NotificationChannel notificationChannel; //채널
    NotificationCompat.Builder builder; //빌더
    String serviceTittle = "[알람 매니저]";
    String serviceContent = "";
    public void setNotificationShow(){
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //TODO 오레오 버전 이상
                //오레오 버전 이상부터는 노티피케이션을 사용하기 위해서 채널이 필요하다
                int importance = NotificationManager.IMPORTANCE_HIGH; //TODO 알림 우선순위를 최상으로 설정 (타이틀 및 소리)
                String Noti_Channel_ID = "DefaultNoti";
                String Noti_Channel_Group_ID = "DefaultNoti_Group";
                notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationChannel = new NotificationChannel(Noti_Channel_ID,Noti_Channel_Group_ID,importance);
                //TODO 뱃지 카운트 실시 (디폴트 true)
                //notificationChannel.setShowBadge(true);
                if(notificationManager.getNotificationChannel(Noti_Channel_ID) != null){ //TODO 채널이 존재할 경우
                    Log.d("---","---");
                    Log.w("//===========//","================================================");
                    Log.d("","\n"+"[A_ImmotalServiceReceiver > setNotificationShow() 메소드 : 채널이 이미 존재합니다]");
                    Log.w("//===========//","================================================");
                    Log.d("---","---");
                }
                else{
                    Log.d("---","---");
                    Log.e("//===========//","================================================");
                    Log.d("","\n"+"[A_ImmotalServiceReceiver > setNotificationShow() 메소드 : 채널이 없어서 만듭니다]");
                    Log.e("//===========//","================================================");
                    Log.d("---","---");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.createNotificationChannel(notificationChannel);
                builder = new NotificationCompat.Builder(mContext,Noti_Channel_ID)
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

                notificationManager.notify(1,builder.build()); //TODO 아이디값이 달라야 노티피케이션이 여러개 표시된다

                getDisplayWakeUp(); //TODO 화면 강제 기상
            }
            else {
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

                notificationManager.notify(1,builder.build()); //TODO 아이디값이 달라야 노티피케이션이 여러개 표시된다

                getDisplayWakeUp(); //TODO 화면 강제 기상
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [화면 기상 시키는 메소드]
    public void getDisplayWakeUp(){
        try {
            /**
             * [화면 기상 방법]
             * 1. 화면 제어 권한 획득 실시 - AndroidManifest.xml : <uses-permission android:name="android.permission.WAKE_LOCK"/>
             * 2. PowerManager.WakeLock 사용해 화면 강제 기상 (깨우기) 실시
             */
            PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "My:Tag");
            wakelock.acquire(); //TODO 화면 즉시 기상 실시
            wakelock.release(); //TODO WakeLock 자원 해제
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}//TODO 클래스 종료





package com.example.acther;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class MyFirebaseMessaging extends FirebaseMessagingService {
    public MyFirebaseMessaging() {
        super();
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if (1==1) {

            System.out.println(remoteMessage.getFrom());
            System.out.println(remoteMessage.getData().size());
            System.out.println(remoteMessage.getNotification().getTitle());
            System.out.println(remoteMessage.getNotification().getBody());
            System.out.println(remoteMessage.getData().get("message"));
            System.out.println(remoteMessage.getData().get("body"));
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            System.out.println("IN RECEIVEMESSAGE#####");
        }
        else {
            System.out.println("ELSE RECEIVEMESSAGE#####");
        }



    }
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        System.out.println("###############");
        System.out.println(token);
    }

    private RemoteViews getCustomDesign(String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification_popup);
        remoteViews.setTextViewText(R.id.noti_title, title);
        remoteViews.setTextViewText(R.id.noti_message, message);
        remoteViews.setImageViewResource(R.id.logo, R.drawable.ic_launcher_background);
        return remoteViews;
    }

    public void showNotification(String title, String message) {
        //팝업 터치시 이동할 액티비티를 지정합니다.
        Intent intent = new Intent(this, MainActivity.class);
        //알림 채널 아이디 : 본인 하고싶으신대로...
        String channelId = getString(R.string.default_notification_channel_id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,  PendingIntent.FLAG_IMMUTABLE);

        //기본 사운드로 알림음 설정. 커스텀하려면 소리 파일의 uri 입력
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000}) //알림시 진동 설정 : 1초 진동, 1초 쉬고, 1초 진동
                .setOnlyAlertOnce(true) //동일한 알림은 한번만.. : 확인 하면 다시 울림
                .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { //안드로이드 버전이 커스텀 알림을 불러올 수 있는 버전이면
            //커스텀 레이아웃 호출
            System.out.println("custom");
            builder = builder.setContent(getCustomDesign(title, message));
        } else { //아니면 기본 레이아웃 호출
            builder = builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_launcher_background); //커스텀 레이아웃에 사용된 로고 파일과 동일하게..
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //알림 채널이 필요한 안드로이드 버전을 위한 코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, getString(R.string.default_notification_channel_id), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri, null);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //알림 표시 !
        notificationManager.notify(0, builder.build());
    }


}
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
        try {
            mContext = context;
            Log.d("---", "---");
            Log.w("//===========//", "================================================");
            Log.d("", "\n" + "[A_AlarmReceiver > onReceive() 메소드 : 수신받은 알림 내용 확인]");
            Log.w("//===========//", "================================================");
            Log.d("---", "---");

            //TODO 노티피케이션 알림 호출 실시
            Toast.makeText(mContext, S_Preference.getString(mContext, "Alarm_Date") + " [알림]", Toast.LENGTH_SHORT).show();
            serviceContent = S_Preference.getString(mContext, "Alarm_Date") + "Lorem Ipsum";
            setNotificationShow();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO [노티피케이션 알림 표시 부분]
    NotificationManager notificationManager; //매니저
    NotificationChannel notificationChannel; //채널
    NotificationCompat.Builder builder; //빌더
    String serviceTittle = "[알람 매니저 실행]";
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

    /*

    String key = "oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";
    String result = "";


    public static int TO_GRID = 0;
    public static int TO_GPS = 1;

    public String getData() {

        // 위치 관리자 객체 참조하기
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(A_AlarmReceiver.this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            // 가장최근 위치정보 가져오기
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();


                // gps 좌표에서 기상청 좌표계로 변환
                A_AlarmReceiver.LatXLngY real = convertGRID_GPS(TO_GRID, latitude, longitude);

                // 받아온 위 경도 로그로 확인
                Log.e(">>", "x = " + real.x + ", y = " + real.y);


                //좌표 형변환 더블 -> 정수 -> 문자열
                int temp_x = (int) real.x;
                int temp_y = (int) real.y;
                String strX = Integer.toString(temp_x);
                String strY = Integer.toString(temp_y);


                String kakaoX = Double.toString(longitude);
                String kakaoY = Double.toString(latitude);


                //kakao Test
                new Thread(() -> {
                    try {
                        String kakaoRes = gpsInfo(kakaoX, kakaoY); // network 동작, 인터넷에서 받아오는 코드
                        System.out.println(kakaoRes);


                        String jsonData = kakaoRes;
                        String receiveMsg = kakaoRes;


                        JSONObject jObject = new JSONObject(jsonData);
                        JSONArray jArray = jObject.getJSONArray("documents");
                        JSONObject obj = jArray.getJSONObject(0);
                        obj = obj.getJSONObject("address");
                        String address = obj.getString("address_name");


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }).start();

                // 사용자의 날짜 받아오기
                Date today = new Date();
                System.out.println(today);
                System.out.println(strX);
                System.out.println(strY);


                //Date  api 요청 파라미터로 사용 위해 문자열 처리 필요 ex) base_date=20221007
                String strNowDate = "10";


                // 현재 시간 구하기 ( 00 시 ~ 05 시 서비스 사용 시 당일 05시 발표되는 예보 받기 불가. 전날 예보 사용해야 함)
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH");
                String strNowHour = simpleTimeFormat.format(today);
                int intHour = Integer.parseInt(strNowHour);
                System.out.println("현재 시간 출력 : " + intHour);

                if (intHour < 5) {
                    Date dDate = new Date();
                    dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    strNowDate = simpleDateFormat.format(dDate);

                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    //원하는 데이터 포맷 지정
                    strNowDate = simpleDateFormat.format(today);
                    //지정한 포맷으로 변환
                    System.out.println("포맷 지정 후 : " + strNowDate);


                }

                // 기상청 단기 예보 API 사용
                String finalStrNowDate = strNowDate;
                new Thread(() -> {

                    String url = ("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey="
                            + key + "&dataType=json&numOfRows=400&pageNo=1&base_date=" + finalStrNowDate + "&base_time=0500&" +
                            "nx=" + strX + "&ny=" + strY);
                    System.out.println(url);
                    InputStream is = null;
                    try {
                        is = new URL(url).openStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String str;
                        StringBuffer buffer = new StringBuffer();
                        while ((str = rd.readLine()) != null) {
                            buffer.append(str + "\n");
                        }
                        String jsonData = buffer.toString();
                        String receiveMsg = buffer.toString();

                        System.out.println("jsonData ::::: " + jsonData);
                        System.out.println("recMSG ::::::" + receiveMsg);

                        //값 들을 리스트로 받기
                        List<String> categoryList = new ArrayList<String>();
                        List fcstDateList = new ArrayList();
                        List fcstTimeList = new ArrayList();
                        List fcstValueList = new ArrayList();

                        List inTimeList = new ArrayList();

                        List windSpeedList = new ArrayList();
                        List windDirectionList = new ArrayList();
                        // jsonData를 먼저 JSONObject 형태로 바꾼다.
                        JSONObject obj = new JSONObject(jsonData);
                        obj = obj.getJSONObject("response");
                        obj = obj.getJSONObject("body");
                        obj = obj.getJSONObject("items");
                        JSONArray ValueArray = obj.getJSONArray("item");


                        //리스트에 전부 추가한다.
                        //만약 카테고리가 TMP OR 바람세기 이면~ 나머지 값들 추가 ;; 이런 식으로 구현할듯
                        for (int i = 0; i < ValueArray.length(); i++) {
                            JSONObject valueObject = ValueArray.getJSONObject(i);


                            //value.setCategory(valueObject.getString("category"));
                            //value.setFcstDate(valueObject.getString("fcstDate"));
                            //value.setFcstTime(valueObject.getString("fcstTime"));
                            //value.setFcstValue(valueObject.getString("fcstValue"));

                            categoryList.add(valueObject.getString("category"));
                            fcstDateList.add(valueObject.getString("fcstDate"));
                            fcstTimeList.add(valueObject.getString("fcstTime"));
                            fcstValueList.add(valueObject.getString("fcstValue"));

                            int intNowDate = Integer.parseInt(finalStrNowDate);

                            //9시 부터 17 시 까지 시간에 (당일만 !!)
                            int intTime = Integer.parseInt(valueObject.getString("fcstTime"));
                            int intDate = Integer.parseInt(valueObject.getString("fcstDate"));
                            if (intTime > 900 && intTime <= 1700 && intDate == intNowDate) {
                                inTimeList.add(valueObject.getString("fcstTime"));

                                // 카테고리가 풍속일 때 리스트에 추가
                                if (valueObject.getString("category").equals("WSD")) {
                                    windSpeedList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                                }
                                // 카테고리가 풍향일 떄 리스트에 추가
                                if (valueObject.getString("category").equals("VEC")) {
                                    windDirectionList.add(Integer.parseInt(valueObject.getString("fcstValue")));
                                }

                            }

                        }

                        Comparable maxWindSpeed = Collections.max(windSpeedList);
                        Double doubleMaxWindSpeed = (Double) (maxWindSpeed);

                        // 풍속이 4.0ms 이하일 때
                        if (doubleMaxWindSpeed <= 4.0) {

                            System.out.println("풍속이 4.0ms 이하입니다.");
                        }
                        //아니면.
                        else {


                        }

                        System.out.println(windDirectionList);


                        List windDirectionCntList = new ArrayList();
                        String windDirection = "";
                        for (Object direction : windDirectionList) {
                            System.out.println(direction);
                            int intDirection = (int) (direction);

                            if (intDirection > 0 && intDirection < 45) {
                                windDirectionCntList.add("N-NE");

                            } else if (intDirection > 45 && intDirection < 90) {
                                windDirectionCntList.add("NE-E");

                            } else if (intDirection > 90 && intDirection < 135) {
                                windDirectionCntList.add("E-SE");

                            } else if (intDirection > 135 && intDirection < 180) {
                                windDirectionCntList.add("SE-S");

                            } else if (intDirection > 180 && intDirection < 225) {
                                windDirectionCntList.add("S-SW");

                            } else if (intDirection > 225 && intDirection < 270) {
                                windDirectionCntList.add("SW-W");

                            } else if (intDirection > 225 && intDirection < 315) {
                                windDirectionCntList.add("W-NW");

                            } else if (intDirection > 315 && intDirection < 360) {
                                windDirectionCntList.add("NW-N");

                            }
                        }


                        int i;
                        for (i = 0; i < categoryList.size(); i++) {

                            TextView textView01 = new TextView(getApplicationContext());
                            textView01.setText(categoryList.get(i));  //배열리스트 이용


                        }


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();

                    }

                }).start();


            }


        }

        // 위치정보를 원하는 시간, 거리마다 갱신해준다.
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                1,
                gpsLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                gpsLocationListener);
        return "asdf";
    }


    public static String gpsInfo(String x, String y) throws IOException {
        URL url = new URL("http://dapi.kakao.com/v2/local/geo/coord2address.json?x=" + x + "&y=" + y + "&input_coord=WGS84");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "KakaoAK c1c0921d459951a565bc6c7669060e68");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
        return response;
    }


    // gps 좌표를 기상청에서 사용하는 좌표계로 변환합니다.

    private A_AlarmReceiver.LatXLngY convertGRID_GPS(int mode, double lat_X, double lng_Y) {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기준점 Y좌표(GRID)

        //
        // LCC DFS 좌표변환 ( code : "TO_GRID"(위경도->좌표, lat_X:위도,  lng_Y:경도), "TO_GPS"(좌표->위경도,  lat_X:x, lng_Y:y) )
        //


        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        A_AlarmReceiver.LatXLngY rs = new A_AlarmReceiver.LatXLngY();

        if (mode == TO_GRID) {
            rs.lat = lat_X;
            rs.lng = lng_Y;
            double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lng_Y * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        } else {
            rs.x = lat_X;
            rs.y = lng_Y;
            double xn = lat_X - XO;
            double yn = ro - lng_Y + YO;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) {
                ra = -ra;
            }
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            double theta = 0.0;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) {
                        theta = -theta;
                    }
                } else theta = Math.atan2(xn, yn);
            }
            double alon = theta / sn + olon;
            rs.lat = alat * RADDEG;
            rs.lng = alon * RADDEG;
        }
        return rs;
    }


    public class Value {
        private String category;
        private String fcstDate;
        private String fcstTime;
        private String fcstValue;


        public String getCategory() {
            return category;
        }

        public String getFcstDate() {
            return fcstDate;
        }

        public String getFcstTime() {
            return fcstTime;
        }

        public String getFcstValue() {
            return fcstValue;
        }


        public void setCategory(String category) {
            this.category = category;
        }

        public void setFcstDate(String fcstDate) {
            this.fcstDate = fcstDate;
        }

        public void setFcstTime(String fcstTime) {
            this.fcstTime = fcstTime;
        }

        public void setFcstValue(String fcstValue) {
            this.fcstValue = fcstValue;
        }

    }


    class LatXLngY {
        public double lat;
        public double lng;

        public double x;
        public double y;

    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.
            String provider = location.getProvider();  // 위치정보
            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            double altitude = location.getAltitude(); // 고도
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public void onProviderEnabled(String provider) {

        }

        public void onProviderDisabled(String provider) {

        }
    };
*/

}//TODO 클래스 종료





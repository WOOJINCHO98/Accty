package com.example.acther;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;



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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {


    String key = "oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";
    //String result = "";

    // 다른 액티비티에 결과값을 전달하기 위한 문자열 리스트
    // 0 / 1 / 2 / 3 / 4 / ...
    // 0 : 첫번째 운동 FLAG   ( 0 : 운동 비추 / 1 : 운동 보통 / 2 : 운동 추천 )
    // 1 : 두번째 운동 FLAG
    // 2 : 세번째 운동 FLAG
    // 3 : 네번째 운동 FLAG
    // 4 : 다섯번째 운동 FLAG
    // 5 : 여섯번째 운동 FLAG
    // 6 : 일곱번째 운동 FLAG
    // 7 : 여덟번째 운동 FLAG
    // 8 : 기온
    // 9 : 강수량
    // 10 : 습도
    // 11 : 풍속
    // 12 : 주소 정보

    List<String> result = new ArrayList<String>();


    public static HashMap<String, String> hMap = new HashMap<String, String>();




    public static Context mContext;


    public static int TO_GRID = 0;
    public static int TO_GPS = 1;

    public static String tmX = "";
    public static String tmY = "";

    String address = "";

    String stationName = "";
    String pm10Grade = "";
    String O3Grade = "";
    //값 들을 리스트로 받기
    List<String> categoryList = new ArrayList<String>();
    List fcstDateList = new ArrayList();
    List fcstTimeList = new ArrayList();
    List fcstValueList = new ArrayList();

    List inTimeList = new ArrayList();

    List windSpeedList = new ArrayList();
    List windDirectionList = new ArrayList();
    List rainPerList = new ArrayList();
    List rainTypeList = new ArrayList();
    List<String> rain1hList = new ArrayList<String>();
    List rainList = new ArrayList();
    List skyList = new ArrayList();
    List tempList = new ArrayList();
    List humidityList = new ArrayList();
    List<String> snow1hList = new ArrayList<String>();
    List waveList = new ArrayList();
    List maxTempList = new ArrayList();
    List minTempList = new ArrayList();


    Comparable maxWindSpeed = 0;
    Double doubleMaxWindSpeed = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mContext = this;




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 4000);
    }






// 다른 액티비티에서 사용하기 위한 메서드
    public List makeFlag() {

        ActivityCompat.requestPermissions(SplashActivity.this,
                new String[]{"android.permission.INTERNET"},
                0);

        ActivityCompat.requestPermissions(SplashActivity.this,
                new String[]{"android.permission.ACCESS_FINE_LOCATION"},
                0);

        ActivityCompat.requestPermissions(SplashActivity.this,
                new String[]{"android.permission.ACCESS_COURCE_LOCATION"},
                0);


        Log.d("----------", "onCreate: ################## IN FLAGMAKER ####### AT ON CREATE ###############" );


        // 위치 관리자 객체 참조하기
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Log.d("----------", "onCreate: ################## IN 어디? ####### AT ON CREATE ###############" );

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( SplashActivity.this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0 );

            Log.d("----------", "onCreate: ################## IN 어디ㅐㅔㅓㅑㅕㅗ? ####### AT ON CREATE ###############" );

        }
        else{
            Log.d("----------", "onCreate: ################## IN ㅎㄹ오호옹ㅅ뇨어디? ####### AT ON CREATE ###############" );
            System.out.println("여기는 어디?");
            // 가장최근 위치정보 가져오기
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();


                System.out.println("위치정보 : " + provider + "\n" +
                        "위도 : " + longitude + "\n" +
                        "경도 : " + latitude + "\n" +
                        "고도  : " + altitude);

                Log.d("----------", "onCreate: ################## IN 어디? ####### AT ON CREATE ###############" );

                // gps 좌표에서 기상청 좌표계로 변환
                SplashActivity.LatXLngY real = convertGRID_GPS(TO_GRID, latitude, longitude);

                // 받아온 위 경도 로그로 확인
                Log.e(">>", "x = " + real.x + ", y = " + real.y);


                //test 셋
                //LatXLngY tmp = convertGRID_GPS(TO_GRID, 37.579871128849334, 126.98935225645432);
                //LatXLngY tmp2 = convertGRID_GPS(TO_GRID, 35.101148844565955, 129.02478725562108);
                //LatXLngY tmp3 = convertGRID_GPS(TO_GRID, 33.500946412305076, 126.54663058817043);

                //Log.e(">>", "x = " + tmp.x + ", y = " + tmp.y);
                //Log.e(">>", "x = " + tmp2.x + ", y = " + tmp2.y);
                //Log.e(">>", "x = " + tmp3.x + ", y = " + tmp3.y);

                //좌표 형변환 더블 -> 정수 -> 문자열
                int temp_x = (int)real.x;
                int temp_y = (int)real.y;
                String strX = Integer.toString(temp_x);
                String strY = Integer.toString(temp_y);

                System.out.println("기상청 x 좌표 : "+ temp_x + "\n" + "기상청 y 좌표 : " + temp_y);


                String kakaoX = Double.toString(longitude);
                String kakaoY = Double.toString(latitude);


                new Thread(()-> {

                    try{

                        String kakaoTm = getTm(kakaoX, kakaoY);
                        String jsonData = kakaoTm;


                        try{
                            JSONObject jObject = new JSONObject(jsonData);
                            JSONArray jArray = jObject.getJSONArray("documents");
                            JSONObject obj = jArray.getJSONObject(0);
                            //obj = obj.getJSONObject("road_address");
                            tmX = obj.getString("x");
                            tmY = obj.getString("y");
                        }catch(JSONException e){

                        }

                        Log.e(">>", "tmX : " + tmX + "\n" + "tmY : " + tmY);
                        Log.e(">>", "tmX : " + tmX + "\n" + "tmY : " + tmY);
                        Log.e(">>", "tmX : " + tmX + "\n" + "tmY : " + tmY);
                        Log.e(">>", "tmX : " + tmX + "\n" + "tmY : " + tmY);

                        stationName = getSpectator(tmX,tmY);

                        Log.e(">>", "stationName : " + stationName);
                        Log.e(">>", "stationName : " + stationName);


                        pm10Grade = getPolution(stationName);

                        if (pm10Grade.equals("1")){
                            pm10Grade = "좋음";
                        }else if (pm10Grade.equals("2")){
                            pm10Grade = "보통";
                        }else if (pm10Grade.equals("3")){
                            pm10Grade = "나쁨";
                        }else if (pm10Grade.equals("4")){
                            pm10Grade = "매우나쁨";
                        }


                        O3Grade = getO3(stationName);
                        if (O3Grade.equals("1")){
                            O3Grade = "좋음";
                        }else if (O3Grade.equals("2")){
                            O3Grade = "보통";
                        }
                        else if (O3Grade.equals("3")){
                            O3Grade = "나쁨";
                        }
                        else if (O3Grade.equals("4")){
                            O3Grade = "매우나쁨";
                        }



                        hMap.put("pm10Grade", pm10Grade);
                        hMap.put("O3Grade", O3Grade);

                        Log.e(">>", "pm10Grade : " + pm10Grade);
                        Log.e(">>", "pm10Grade : " + pm10Grade);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }


                }).start();

                new Thread(() ->{
                    try{


                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }).start();

                //kakao Test
                new Thread(() -> {
                    try {
                        String kakaoRes = gpsInfo(kakaoX,kakaoY); // network 동작, 인터넷에서 받아오는 코드
                        System.out.println(kakaoRes);


                        String jsonData = kakaoRes;
                        String receiveMsg = kakaoRes;


                        System.out.println("######################################### \n\n\n"+jsonData);




                        try{
                            JSONObject jObject = new JSONObject(jsonData);
                            JSONArray jArray = jObject.getJSONArray("documents");
                            JSONObject obj = jArray.getJSONObject(0);
                            obj = obj.getJSONObject("road_address");
                            address = obj.getString("address_name");
                        }catch(JSONException e){
                            JSONObject jObject2 = new JSONObject(jsonData);
                            JSONArray jArray2 = jObject2.getJSONArray("documents");
                            JSONObject obj2 = jArray2.getJSONObject(0);
                            obj2 = obj2.getJSONObject("address");
                            address = obj2.getString("address_name");
                        }
                        System.out.println("######################################### \n\n\n"+address);

                        System.out.println("######################################### \n\n\n"+address);


                        System.out.println("주소 : " + address);

                        result.add(address);

                        hMap.put("address", address);


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
                    dDate = new Date(dDate.getTime()+(1000*60*60*24*-1));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    strNowDate = simpleDateFormat.format(dDate);

                }
                else{
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
                            + key + "&dataType=json&numOfRows=400&pageNo=1&base_date="+ finalStrNowDate +"&base_time=0500&"+
                            "nx="+strX+ "&ny=" + strY);
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

                        System.out.println("jsonData ::::: "+jsonData);
                        System.out.println("recMSG ::::::"+receiveMsg);



                        // jsonData를 먼저 JSONObject 형태로 바꾼다.
                        JSONObject obj = new JSONObject(jsonData);
                        obj = obj.getJSONObject("response");
                        obj = obj.getJSONObject("body");
                        obj = obj.getJSONObject("items");
                        JSONArray ValueArray = obj.getJSONArray("item");


                        //valueObject 에 전부 추가한다.
                        //만약 카테고리가 TMP OR 바람세기 이면~ 나머지 값들 추가 ;; 이런 식으로 구현할듯
                        for(int i=0; i<ValueArray.length(); i++)
                        {
                            JSONObject valueObject = ValueArray.getJSONObject(i);

                            SplashActivity.Value value = new SplashActivity.Value();


                            //value.setCategory(valueObject.getString("category"));
                            //value.setFcstDate(valueObject.getString("fcstDate"));
                            //value.setFcstTime(valueObject.getString("fcstTime"));
                            //value.setFcstValue(valueObject.getString("fcstValue"));

                            categoryList.add(valueObject.getString("category"));
                            fcstDateList.add(valueObject.getString("fcstDate"));
                            fcstTimeList.add(valueObject.getString("fcstTime"));
                            fcstValueList.add(valueObject.getString("fcstValue"));

                            int intNowDate = Integer.parseInt(finalStrNowDate);


                            // 일 최고 기온
                            if (valueObject.getString("category").equals("TMX")){
                                maxTempList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                            }

                            // 일 최저 기온
                            if (valueObject.getString("category").equals("TMN")){
                                System.out.println("TMN :::: "+valueObject.getString("fcstValue"));
                                minTempList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                            }


                            //9시 부터 17 시 까지 시간에 (당일만 !!)
                            int intTime = Integer.parseInt(valueObject.getString("fcstTime"));
                            int intDate = Integer.parseInt(valueObject.getString("fcstDate"));
                            if (intTime>900 && intTime<=1700 && intDate == intNowDate){
                                inTimeList.add(valueObject.getString("fcstTime"));

                                // 카테고리가 풍속일 때 리스트에 추가
                                if (valueObject.getString("category").equals("WSD")){
                                    windSpeedList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                                }
                                // 카테고리가 풍향일 떄 리스트에 추가
                                if (valueObject.getString("category").equals("VEC")){
                                    windDirectionList.add(Integer.parseInt(valueObject.getString("fcstValue")));
                                }


                                // 강수확률
                                if (valueObject.getString("category").equals("POP")){
                                    rainList.add(Integer.parseInt(valueObject.getString("fcstValue")));
                                }

                                //


                                // 강수형태
                                if (valueObject.getString("category").equals("PTY")){
                                    if (valueObject.getString("fcstValue").equals("1")){
                                        rainTypeList.add("비");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("2")){
                                        rainTypeList.add("비/눈");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("3")){
                                        rainTypeList.add("눈");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("4")){
                                        rainTypeList.add("소나기");
                                    }
                                    else{
                                        rainTypeList.add("없음");
                                    }
                                }

                                // 1시간 강수량
                                if (valueObject.getString("category").equals("PCP")){
                                    rain1hList.add(valueObject.getString("fcstValue"));
                                }

                                // 1시간 적설량
                                if (valueObject.getString("category").equals("SNO")){
                                    snow1hList.add(valueObject.getString("fcstValue"));
                                }

                                // 습도
                                if (valueObject.getString("category").equals("REH")){
                                    humidityList.add(Integer.parseInt(valueObject.getString("fcstValue")));
                                }

                                //1시간 기온
                                if (valueObject.getString("category").equals("TMP")){
                                    tempList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                                }



                                // 파고
                                if (valueObject.getString("category").equals("WAV")){
                                    waveList.add(Double.parseDouble(valueObject.getString("fcstValue")));
                                }



                                // 하늘 상태
                                if (valueObject.getString("category").equals("SKY")){
                                    if (valueObject.getString("fcstValue").equals("1")){
                                        skyList.add("맑음");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("2")){
                                        skyList.add("구름조금");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("3")){
                                        skyList.add("구름많음");
                                    }
                                    else if (valueObject.getString("fcstValue").equals("4")){
                                        skyList.add("흐림");
                                    }
                                }



                            }

                        }

                        maxWindSpeed = Collections.max(windSpeedList);
                        doubleMaxWindSpeed = (Double)(maxWindSpeed);


                        System.out.println(windDirectionList);

                        //풍향 방향 설정 하기
//                        for (int i=0; i<windDirectionList.size(); i++){
//                            System.out.println(windDirectionList.get(i));
//                        }

                        List windDirectionCntList = new ArrayList();
                        String windDirection = "";
                        for (Object direction : windDirectionList) {
                            System.out.println(direction);
                            int intDirection = (int)(direction);

                            if (intDirection>0 && intDirection<45){
                                windDirectionCntList.add("N-NE");

                            }
                            else if (intDirection>45 && intDirection<90){
                                windDirectionCntList.add("NE-E");

                            }
                            else if (intDirection>90 && intDirection<135){
                                windDirectionCntList.add("E-SE");

                            }
                            else if (intDirection>135 && intDirection<180){
                                windDirectionCntList.add("SE-S");

                            }
                            else if (intDirection>180 && intDirection<225){
                                windDirectionCntList.add("S-SW");

                            }
                            else if (intDirection>225 && intDirection<270){
                                windDirectionCntList.add("SW-W");

                            }
                            else if (intDirection>225 && intDirection<315){
                                windDirectionCntList.add("W-NW");

                            }
                            else if (intDirection>315 && intDirection<360){
                                windDirectionCntList.add("NW-N");

                            }
                        }

                        System.out.println("바람의 방향은  \n" + windDirectionCntList);



                        System.out.println(receiveMsg);
                        System.out.println(obj);


                        System.out.println("카테고리 리스트 : "+ categoryList);
                        System.out.println("날짜 리스트 : " + fcstDateList);
                        System.out.println("시간 리스트 : " + fcstTimeList);
                        System.out.println("값 리스트 : " + fcstValueList);
                        System.out.println("시간 리스트 : " + inTimeList);
                        System.out.println("풍속 리스트 : " + windSpeedList);
                        System.out.println("풍향 리스트 : " + windDirectionList);
                        System.out.println("풍향 문자 리스트 : " + windDirectionCntList);
                        System.out.println("파고 리스트 : " + waveList);
                        System.out.println("강수 확률 리스트 : " + rainList);
                        System.out.println("비 형태 리스트 : " + rainTypeList);
                        System.out.println("1시간 강수량 리스트 : " + rain1hList);
                        System.out.println("1시간 적설량 리스트 : " + snow1hList);
                        System.out.println("하늘 리스트 : " + skyList);
                        System.out.println("습도 리스트 : " + humidityList);
                        System.out.println("기온 리스트 : " + tempList);
                        System.out.println("일 최고 기온 리스트 : " + maxTempList);
                        System.out.println("일 최저 기온 리스트 : " + minTempList);




                        int i;
                        for (i=0; i<categoryList.size(); i++) {

                            TextView textView01 = new TextView(getApplicationContext());
                            textView01.setText(categoryList.get(i));  //배열리스트 이용


                        }




                        System.out.println("주소 : " + address);

                        System.out.println("Running : " + setRunningFlag());
                        System.out.println("Hiking : " + setHikingFlag());
                        System.out.println("Cycle : " + setCycleFlag());
                        System.out.println("Gofl : " + setGolfFlag());
                        System.out.println("Ski : " + setSkiFlag());
                        System.out.println("Paragliding : " + setParaglidingFlag());


                        System.out.println("--------------------------------------");

                        System.out.println("TempAverage : " + setTempAverage());
                        System.out.println("Rain1hAverage : " + setRain1hAverage());
                        System.out.println("Snow1hAverage : " + setSnow1hAverage());
                        System.out.println("WindSpeedAverage : " + setWindSpeedAverage());

                        System.out.println("--------------------------------------");



                        System.out.println(address);



                        result.add(setRunningFlag().toString());
                        result.add(setGolfFlag().toString());
                        result.add(setCycleFlag().toString());
                        result.add(setParaglidingFlag().toString());
                        result.add(setSkiFlag().toString());
                        result.add(setHikingFlag().toString());

                        hMap.put("Running", setRunningFlag().toString());
                        hMap.put("Golf", setGolfFlag().toString());
                        hMap.put("Cycle", setCycleFlag().toString());
                        hMap.put("Surfing", setSurfingFlag().toString());
                        hMap.put("Paragliding", setParaglidingFlag().toString());
                        hMap.put("Ski", setSkiFlag().toString());
                        hMap.put("Hiking", setHikingFlag().toString());



                        result.add(setTempAverage().toString());
                        result.add(setRain1hAverage().toString());
                        result.add(setSnow1hAverage().toString());
                        result.add(setWindSpeedAverage().toString());
                        result.add(maxWindSpeed.toString());

                        hMap.put("waveAverage", setWaveAverage().toString());
                        hMap.put("TempAverage", setTempAverage().toString());
                        hMap.put("Rain1hAverage", setRain1hAverage().toString());
                        hMap.put("Snow1hAverage", setSnow1hAverage().toString());
                        hMap.put("WindSpeedAverage", setWindSpeedAverage().toString());
                        hMap.put("MaxWindSpeed", maxWindSpeed.toString());


                        //setSurfingFlag();
                        //setFishingFlag();





                        System.out.println("setDailyTempGap : " + setDailyTempGapFlag());
                        System.out.println("setRainFlag : " + setRainFlag());
                        System.out.println("setSkyFlag : " + setSkyFlag());
                        System.out.println("setSnowFlag : " + setSnowFlag());
                        System.out.println("setMinTempFlag() : " + setMinTempFlag());
                        System.out.println("setMaxTempFlag() : " + setMaxTempFlag());
                        System.out.println("setBaseFlag : " + setBaseFlag());




                        //hMap.put("JijumCode", getJijumCodeJson(temp_x, temp_y));
                        //System.out.println("getJijumCodeJson : " + getJijumCodeJson(temp_x, temp_y));
                    }
                    catch (IOException | JSONException e) {
                        e.printStackTrace();

                    }

                }).start();



            }




        }


        /*
        // 위치정보를 원하는 시간, 거리마다 갱신해준다.
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                1,
                gpsLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                gpsLocationListener);

         */

        return result;
    }


    public Double setWaveAverage(){
        Double sum = 0.0;
        for(int i=0; i<waveList.size(); i++){
            sum += (Double) waveList.get(i);
        }
        return sum/waveList.size();
    }

    public Double setWindSpeedAverage() {
        Double windSpeedAverage = 0.0;
        int i;
        for (i=0; i<windSpeedList.size(); i++) {
            windSpeedAverage += (Double)windSpeedList.get(i);
        }
        windSpeedAverage = windSpeedAverage / windSpeedList.size();
        return windSpeedAverage;
    }


    public Double setRain1hAverage(){


        Double sum = 0.0;

        try {
            for (int i=0; i<rain1hList.size(); i++){
                sum += Double.parseDouble(rain1hList.get(i));
            }
            return sum/rain1hList.size();
        }
        catch (Exception e){
            return 0.0;
        }

    }


    public Double setSnow1hAverage(){


        Double sum = 0.0;

        try {
            for (int i=0; i<snow1hList.size(); i++){
                sum += Double.parseDouble(snow1hList.get(i));
            }
            return sum/snow1hList.size();
        }
        catch (Exception e){
            return 0.0;
        }

    }

    public Double setTempAverage() {
        Double tempAverage = 0.0;
        Double tempSum = 0.0;
        Integer tempCnt = 0;
        Integer i;

        for (i=0; i<tempList.size(); i++) {
            if (tempList.get(i) != null) {
                tempSum += (Double)tempList.get(i);
                tempCnt++;
            }
        }

        tempAverage = tempSum / tempCnt;

        System.out.println("tempAverage : " + tempAverage);
        return tempAverage;
    }


    // 하늘 흐리다면 0, 아니면1
    public Integer setSkyFlag(){

        int i;
        int SkyFlag = 0;
        for (i=0; i<skyList.size(); i++){
            if (skyList.get(i).equals("맑음") || skyList.get(i).equals("구름조금") || skyList.get(i).equals("구름많음")) {
                SkyFlag = 1;
            }

            else if (skyList.get(i).equals("흐림")){
                SkyFlag = 0;
            }
        }
        return SkyFlag;

    }

    //----------------------------------------------- 기초가 되는 BaseFlag들 -------------------------------------------------
    // 예측 강수 있다면, rainFlag == 0
    public Integer setRainFlag(){
        int i;
        int rainFlag = 0;
        for (i=0; i<rain1hList.size(); i++){
            if (rain1hList.get(i).equals("강수없음")){
                rainFlag = 1;
            }
            else{
                hMap.put("rainMsg","오늘은 비가 내릴 수도 있어요");
                rainFlag = 0;
                break;
            }
        }
        return rainFlag;
    }

    // 예측 강수 있다면, rainFlag == 0
    public Integer setSnowFlag(){
        int i;
        int snowFlag = 0;
        for (i=0; i<snow1hList.size(); i++){
            if (snow1hList.get(i).equals("적설없음")){
                snowFlag = 1;
            }
            else{
                hMap.put("snowMsg","오늘은 눈이 내릴 수도 있어요");

                snowFlag = 0;
                break;
            }
        }
        return snowFlag;
    }

    // 활동 시간 내에 최저 기온이 0도 이하라면 0 반환
    public Integer setMinTempFlag(){
        int i;
        int minTempFlag = 0;
        for (i=0; i<tempList.size(); i++){
            if ((Double)tempList.get(i) <= 0){
                minTempFlag = 0;
                hMap.put("temp0Msg","오늘은 영하권이에요.");

                break;
            }
            else{
                minTempFlag = 1;
            }
        }
        return minTempFlag;
    }

    // 활동 시간 내에 최대 기온이 30도 이상이라면 0 반환
    public Integer setMaxTempFlag(){
        int i;
        int maxTempFlag = 0;
        for (i=0; i<tempList.size(); i++){
            if ((Double)tempList.get(i) >= 30){
                maxTempFlag = 0;
                hMap.put("temp30Msg","오늘은 한 낮에 30도가 넘어가요");

                break;
            }
            else{
                maxTempFlag = 1;
            }
        }
        return maxTempFlag;
    }

    // 일교차 FLAG
    public Integer setDailyTempGapFlag(){
        Double dailyTempGap = 0.0;
        Integer DailyTempGapFlag = 0;
        dailyTempGap = (Double) maxTempList.get(0) - (Double) minTempList.get(0);

        if (dailyTempGap >= 10){
            hMap.put("tmepGapMsg","오늘은 일교차가 10도 이상이에요.");
            DailyTempGapFlag = 1;
        }
        else if (dailyTempGap >= 15){
            hMap.put("tmepGapMsg","오늘은 일교차가 15도 이상이에요.");
            DailyTempGapFlag = 2;
        }
        else if (dailyTempGap >= 20){
            hMap.put("tmepGapMsg","오늘은 일교차가 20도 이상이에요.");
            DailyTempGapFlag = 2;
        }
        else{
            DailyTempGapFlag = 0;
        }

        return DailyTempGapFlag;
    }


    // 적절 기온 FLAG
    public  int setTempFlag(){

        Integer tempFlag = 1;

        //for-loop 통한 전체 조회
        for(Object object : tempList) {
            Double element = (Double) object;
            if (element >= 5) {
                tempFlag = 1;
            }
            else if (element < 5) {
                tempFlag = 0;
            }
        }

        return tempFlag;
    }

    //---------------------------------------------------BaseFlag를 합친 Flag -------------------------------------------------
    // BaseFlag들을 합쳐서 최종 Flag를 만든다.
    public Integer setBaseFlag() {
        int i;
        int baseFlag = 0;

        if (setRainFlag() == 1 && setSnowFlag() == 1 && setMinTempFlag() == 1 && setMaxTempFlag() == 1) {
            baseFlag = 1;
        } else {
            baseFlag = 0;
        }

        return baseFlag;
    }



    //---------------------------------------------------각각의 액티비티 FLAG 매서드------------------------------------------------------------------------------------------------------------
    // 런닝 FLAG 값 정하기
    public Integer setRunningFlag(){

        int runningFlag = 0;

        if (setBaseFlag() == 1 && pm10Grade.equals("좋음")||pm10Grade.equals("보통")){
            runningFlag = 1;
        }
        else{
            runningFlag = 0;
        }

        return runningFlag;
    }

    // 등산 FLAG 값 정하기
    public Integer setHikingFlag(){

        int hikingFlag = 0;

        if (setBaseFlag() == 1 && setDailyTempGapFlag() == 0){
            hikingFlag = 1;
        }
        else{
            hikingFlag = 0;
        }

        return hikingFlag;
    }


    // 싸이클 FLAG 값 정하기
    public Integer setCycleFlag() {
        // 풍속이 4.0ms 이하일 때
        if (setWindSpeedAverage() <= 4.0 && setBaseFlag() == 1) {
            int Flag = 1;
            return Flag;
        }
        //아니면.
        else{
            hMap.put("cycleMsg","오늘은 풍속이 4m/s 이상이에요.");
            int Flag = 0;
            return Flag;
        }
    }



    // 골프 FLAG 값 정하기
    public Integer setGolfFlag(){

        int golfFlag = 0;

        if (setBaseFlag() == 1 && setDailyTempGapFlag() == 0){
            golfFlag = 1;
        }
        else{
            golfFlag = 0;
        }

        return golfFlag;
    }

    // 스키 FLAG 값 정하기
    // 스키는 조건을 조금 더 추가해야함
    public Integer setSkiFlag(){

        int skiFlag = 0;

        if (setBaseFlag() == 1 && setDailyTempGapFlag() == 1 && (Double)maxTempList.get(0) < 8.0) {
            skiFlag = 1;
        }
        else{

            skiFlag = 0;
            if (setSnowFlag() == 0){
                skiFlag = 1;
            }
        }

        return skiFlag;
    }

    // 패러글라이딩 FLAG 값 정하기
    public Integer setParaglidingFlag(){

        int paraglidingFlag = 0;

        if (setBaseFlag() == 1 && doubleMaxWindSpeed >= 1.0 && doubleMaxWindSpeed <= 6.0){
            paraglidingFlag = 1;
        }
        else if (setBaseFlag() == 1 && doubleMaxWindSpeed >= 6.0){
            hMap.put("glidingMsg","오늘은 풍속이 6m/s 가 넘어가요.");

            paraglidingFlag = 2;
        }
        else{
            hMap.put("glidingMsg","오늘은 바람이 너무 안 부네요.");

            paraglidingFlag = 3;
        }

        return paraglidingFlag;
    }


    // 서핑 FLAG 값 정하기
    public Integer setSurfingFlag(){

        int surfingFlag = 0;

        if (setBaseFlag() == 1 && doubleMaxWindSpeed >= 1.0 && doubleMaxWindSpeed <= 6.0){
            surfingFlag = 1;
        }
        else if (setBaseFlag() == 1 && doubleMaxWindSpeed >= 6.0){
            surfingFlag = 2;
        }
        else{
            surfingFlag = 3;
        }

        return surfingFlag;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------

    // 카카오 로컬 API
    public static String gpsInfo(String x, String y) throws IOException {
        URL url = new URL("http://dapi.kakao.com/v2/local/geo/coord2address.json?x="+x+"&y="+y+"&input_coord=WGS84");
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





    // 기기 로컬에서 받아온 gps 좌표를 기상청에서 사용하는 좌표계로 변환합니다.
    public LatXLngY convertGRID_GPS(int mode, double lat_X, double lng_Y )
    {
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
        SplashActivity.LatXLngY rs = new SplashActivity.LatXLngY();

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
        }
        else {
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
            }
            else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) {
                        theta = -theta;
                    }
                }
                else theta = Math.atan2(xn, yn);
            }
            double alon = theta / sn + olon;
            rs.lat = alat * RADDEG;
            rs.lng = alon * RADDEG;
        }
        return rs;
    }


    public class Value{
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


    class LatXLngY
    {
        public double lat;
        public double lng;

        public double x;
        public double y;

    }


    // gps 좌표를 받아서 위도 경도로 변환
    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.
            String provider = location.getProvider();  // 위치정보
            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            double altitude = location.getAltitude(); // 고도
            System.out.println("위치정보 : " + provider + "\n" + "위도 : " + longitude + "\n" + "경도 : " + latitude + "\n" + "고도 : " + altitude);
        } public void onStatusChanged(String provider, int status, Bundle extras) {

        } public void onProviderEnabled(String provider) {

        } public void onProviderDisabled(String provider) {

        }
    };

    public String getTm(String lat, String lng) throws IOException{
        String tm = "";



        //kakao 로 TM 좌표계 반환

        URL url = new URL("https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?input_coord=WGS84&output_coord=TM&x="+lat+"&y="+lng);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "KakaoAK c1c0921d459951a565bc6c7669060e68");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println("리스폰스 입니다. TM"+response);

        return response;

    }

    public String getSpectator(String tmX,String tmY) throws IOException{

        String spectator = "";


        String url = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX="+tmX+"&tmY="+tmY+"&returnType=json&serviceKey=oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";


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



            // jsonData를 먼저 JSONObject 형태로 바꾼다.
            JSONObject obj = new JSONObject(jsonData);
            obj = obj.getJSONObject("response");
            obj = obj.getJSONObject("body");
            JSONArray ValueArray = obj.getJSONArray("items");
            //JSONArray ValueArray = obj.getJSONArray("item");

            spectator = ValueArray.getJSONObject(0).getString("stationName");

            Log.e("spectator",spectator);
            Log.e("spectator",spectator);

        }
        catch (IOException | JSONException e) {
            e.printStackTrace();

        }

        return spectator;

    }

    public String getPolution(String stationName) throws IOException {

        String polution = "";
        Log.e("misae", "misae");
        Log.e("misae", "misae");
        Log.e("misae", "misae");
        Log.e("misae", "misae");
        Log.e("misae", "misae");




        String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+stationName+"&dataTerm=DAILY&pageNo=1&numOfRows=100&returnType=json&serviceKey="+"oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";
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

            // jsonData를 먼저 JSONObject 형태로 바꾼다.
            JSONObject obj = new JSONObject(jsonData);
            obj = obj.getJSONObject("response");
            obj = obj.getJSONObject("body");
            JSONArray ValueArray = obj.getJSONArray("items");
            //JSONArray ValueArray = obj.getJSONArray("item");

            polution = ValueArray.getJSONObject(0).getString("pm10Grade");


        }
        catch (IOException | JSONException e) {
            e.printStackTrace();

        }



        return polution;
    }


    public String getO3(String stationName) throws IOException {


        String result = "";


        String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+stationName+"&dataTerm=DAILY&pageNo=1&numOfRows=100&returnType=json&serviceKey="+"oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";
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

            // jsonData를 먼저 JSONObject 형태로 바꾼다.
            JSONObject obj = new JSONObject(jsonData);
            obj = obj.getJSONObject("response");
            obj = obj.getJSONObject("body");
            JSONArray ValueArray = obj.getJSONArray("items");
            //JSONArray ValueArray = obj.getJSONArray("item");

            result = ValueArray.getJSONObject(0).getString("o3Grade");


        }
        catch (IOException | JSONException e) {
            e.printStackTrace();

        }



        return result;
    }


    public String getJijumCodeJson(int x, int y){



        String result = "";

        int xCode = x; // 위도
        int yCode = y; // 경도

        int count = 0;

        Log.e(">>", "x = " + xCode);

        Log.e(">>", "y = " + yCode);



        String JijumCode = "";

        try{


            InputStream inputStream = getAssets().open("JijumCode.json");

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String str = "";
            StringBuffer buffer2 = new StringBuffer();
            while ((str = rd.readLine()) != null) {

                buffer2.append(str + "\n");

                //buffer.append(str);
            }
            String jsonData = buffer2.toString();
            String receiveMsg = buffer2.toString();

            //System.out.println("jsonData ::::: "+jsonData);



            //Log.e(">>", "data = " + jsonData);

            Log.e(">>", "포문 밖에는 나오나여?11 = " + xCode);

            // jsonData를 먼저 JSONObject 형태로 바꾼다.
            JSONObject obj = new JSONObject(jsonData);


            Log.e(">>", "포문 밖에는 나오나여? 22= " + obj);



            JSONArray ValueArray = new JSONArray(obj);
            Log.e(">>", "포문 밖에는 나오나여?33 = " + xCode);


            //valueObject 에 전부 추가한다.
            //만약 카테고리가 TMP OR 바람세기 이면~ 나머지 값들 추가 ;; 이런 식으로 구현할듯
            for(int i=0; i<ValueArray.length(); i++)
            {
                JSONObject valueObject = ValueArray.getJSONObject(i);

                //SplashActivity.Value value = new SplashActivity.Value();

                Log.e(">>", "도는중 = " + xCode);


                if(valueObject.getString("X").equals(String.valueOf(xCode)) && valueObject.getString("Y").equals(String.valueOf(yCode))){
                    JijumCode = valueObject.getString("JijumCode");
                    Log.e(">>", "와 찾았다 ! = " + xCode);

                    break;
                }



            }
            Log.e(">>", "code = " +JijumCode);
            //Log.e(">>", "x = " + obj.toString());

            //Log.e(">>", "data = " + obj);



        }
        catch (Exception e){
        }

        Log.e(">>", "포문 밖에는 나오나여?123123 = " + JijumCode);

        return JijumCode;

    }



}
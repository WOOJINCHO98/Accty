package com.example.acther;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FishingActivity extends AppCompatActivity {

    TextView test,test1,test2;
    String key = "oTsloDJ6xmHymJiItQxmn1GEp2HiiX%2B8fA%2BH6PRKbCUp3XWPNEAViCpeWOir0YPCRpFHH3XQ6i6PlYwNdEg4dQ%3D%3D";
    String result = "";




    public static int TO_GRID = 0;
    public static int TO_GPS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);


        test = (TextView) findViewById(R.id.test) ;
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);

        // 위치 관리자 객체 참조하기
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( FishingActivity.this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0 );
        }
        else{
            // 가장최근 위치정보 가져오기
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null) {
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();

                test.setText("위치정보 : " + provider + "\n" +
                        "위도 : " + longitude + "\n" +
                        "경도 : " + latitude + "\n" +
                        "고도  : " + altitude);

                LatXLngY real = convertGRID_GPS(TO_GRID, latitude, longitude);
                //LatXLngY tmp = convertGRID_GPS(TO_GRID, 37.579871128849334, 126.98935225645432);
                //LatXLngY tmp2 = convertGRID_GPS(TO_GRID, 35.101148844565955, 129.02478725562108);
                //LatXLngY tmp3 = convertGRID_GPS(TO_GRID, 33.500946412305076, 126.54663058817043);


                Log.e(">>", "x = " + real.x + ", y = " + real.y);

                //Log.e(">>", "x = " + tmp.x + ", y = " + tmp.y);
                //Log.e(">>", "x = " + tmp2.x + ", y = " + tmp2.y);
                //Log.e(">>", "x = " + tmp3.x + ", y = " + tmp3.y);


                int temp_x = (int)real.x;
                int temp_y = (int)real.y;
                String strX = Integer.toString(temp_x);
                String strY = Integer.toString(temp_y);

                test1.setText("x 좌표변환한거 : "+ temp_x + "\n" + "y 격자 : " + temp_y);



                Date today = new Date();
                System.out.println(today);



                // Input Stream 으로 문자열로 받아온듯 JSON 데이터 처리 가능?

                new Thread(() -> {

                    String url = ("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey="
                            + key + "&dataType=json&numOfRows=10&pageNo=1&base_date=20221004&base_time=0500&"+
                            "nx="+strX+ "&ny=" + strY);
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

                        List<String> categoryList = new ArrayList<String>();
                        List fcstDateList = new ArrayList();
                        List fcstTimeList = new ArrayList();
                        List fcstValueList = new ArrayList();


                        // jsonData를 먼저 JSONObject 형태로 바꾼다.
                        JSONObject obj = new JSONObject(jsonData);
                        obj = obj.getJSONObject("response");
                        obj = obj.getJSONObject("body");
                        obj = obj.getJSONObject("items");
                        JSONArray ValueArray = obj.getJSONArray("item");

                        for(int i=0; i<ValueArray.length(); i++)
                        {
                            JSONObject valueObject = ValueArray.getJSONObject(i);

                            Value value = new Value();


                            //value.setCategory(valueObject.getString("category"));
                            //value.setFcstDate(valueObject.getString("fcstDate"));
                            //value.setFcstTime(valueObject.getString("fcstTime"));
                            //value.setFcstValue(valueObject.getString("fcstValue"));

                            categoryList.add(valueObject.getString("category"));
                            fcstDateList.add(valueObject.getString("fcstDate"));
                            fcstTimeList.add(valueObject.getString("fcstTime"));
                            fcstValueList.add(valueObject.getString("fcstValue"));
                        }



                        System.out.println(receiveMsg);
                        System.out.println(obj);


                        System.out.println(categoryList);
                        System.out.println(fcstDateList);
                        System.out.println(fcstTimeList);
                        System.out.println(fcstValueList);

                        int i;
                        for (i=0; i<categoryList.size(); i++) {

                            TextView textView01 = new TextView(getApplicationContext());
                            textView01.setText(categoryList.get(i));  //배열리스트 이용

                        }



                            //test2.setText();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                test2.setText(categoryList+"\n"+fcstDateList+"\n"+fcstTimeList+"\n"+fcstValueList);
                            }
                        });

                    }
                    catch (IOException | JSONException e) {
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
    }







    private LatXLngY convertGRID_GPS(int mode, double lat_X, double lng_Y )
    {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)

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
        LatXLngY rs = new LatXLngY();

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

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // 위치 리스너는 위치정보를 전달할 때 호출되므로 onLocationChanged()메소드 안에 위지청보를 처리를 작업을 구현 해야합니다.
            String provider = location.getProvider();  // 위치정보
            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            double altitude = location.getAltitude(); // 고도
            test.setText("위치정보 : " + provider + "\n" + "위도 : " + longitude + "\n" + "경도 : " + latitude + "\n" + "고도 : " + altitude);
        } public void onStatusChanged(String provider, int status, Bundle extras) {

        } public void onProviderEnabled(String provider) {

        } public void onProviderDisabled(String provider) {

        }
    };



}


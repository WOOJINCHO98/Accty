package com.example.acther;

import static android.app.PendingIntent.FLAG_MUTABLE;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class A_Alarm2 extends AppCompatActivity {

    //private static final int FLAG_IMMUTABLE =0 ;
    //TODO [클래스 전역 변수 선언]
    String timeTotal = "";
    String yyyy = "";
    String MM = "";
    String dd = "";
    String hh = "";
    String mmm = "";
    String ss = "";

    //TODO [클래스 컴포넌트 선언]
    EditText year_edit;
    EditText month_edit;
    EditText day_edit;
    EditText hour_edit;
    EditText minute_edit;
    EditText second_edit;
    Button ok_button;
    Button no_button;




    //TODO [액티비티 시작 메소드]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_Alarm > onCreate() 메소드 : 액티비티 시작 실시]");
        Log.d("//===========//","================================================");
        Log.d("---","---");

        //TODO [초기 프리퍼런스에 현재 날짜 저장 실시]
        try {
            S_Preference.setString(getApplication(), "Alarm_Date", getNowTime2());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //TODO [프리퍼런스에 저장된 데이터가 있는지 체크]
        try {
            //TODO [2021-05-04 11:35:12] 형태
            String pref = String.valueOf(S_Preference.getString(getApplication(), "Alarm_Date"));
            pref = pref.trim(); //TODO 끝쪽 공백 제거
            if(pref != null && pref.length() > 0 && !pref.equals("") && !pref.contains("null")){
                Log.d("---","---");
                Log.w("//===========//","================================================");
                Log.d("","\n"+"[A_Alarm > 프리퍼런스 저장된 날짜 확인]");
                Log.d("","\n"+"[날짜 : "+String.valueOf(pref)+"]");
                Log.w("//===========//","================================================");
                Log.d("---","---");
                //TODO 중앙 공백 기준으로 날짜 및 시간 분리
                String date = pref.split(" ")[0];
                String hour = pref.split(" ")[1];
                getDateParce(date); //TODO 메소드 호출
                getTimeParce(hour); //TODO 메소드 호출
            }
            else{
                Log.d("---","---");
                Log.e("//===========//","================================================");
                Log.d("","\n"+"[A_Alarm > 프리퍼런스 저장된 날짜 확인]");
                Log.d("","\n"+"[날짜 : "+String.valueOf(pref)+"]");
                Log.e("//===========//","================================================");
                Log.d("---","---");
                //TODO 기본값 설정
                yyyy = "2022";
                MM = "11";
                dd = "03";
                hh = "17";
                mmm = "30";
                ss = "00";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //TODO [컴포넌트 매칭 실시]
        year_edit = (EditText)findViewById(R.id.year_edit);
        year_edit.setText(yyyy);

        month_edit = (EditText)findViewById(R.id.month_edit);
        month_edit.setText(MM);

        day_edit = (EditText)findViewById(R.id.day_edit);
        day_edit.setText(dd);

        hour_edit = (EditText)findViewById(R.id.hour_edit);
        hour_edit.setText(hh);

        minute_edit = (EditText)findViewById(R.id.minute_edit);
        minute_edit.setText(mmm);

        second_edit = (EditText)findViewById(R.id.second_edit);
        second_edit.setText(ss);

        ok_button = (Button)findViewById(R.id.ok_button);
        no_button = (Button)findViewById(R.id.no_button);

        //TODO [버튼 클릭 이벤트 정의]
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yyyy = year_edit.getText().toString().trim();
                MM = month_edit.getText().toString().trim();
                dd = day_edit.getText().toString().trim();
                hh = hour_edit.getText().toString().trim();
                mmm = minute_edit.getText().toString().trim();
                ss = second_edit.getText().toString().trim();

                setAlarmSetting(yyyy, MM, dd, hh, mmm, ss); //TODO 알람 설정 메소드 호출
            }
        });

        //TODO [버튼 클릭 이벤트 정의]
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmCancle(); //TODO 알람 취소 메소드 호출
            }
        });

    }//TODO 메인 종료

    //TODO [알림 등록 실시 메소드]
    public void setAlarmSetting(String year, String month, String day, String hour, String minute, String second){
        String str_date = "";
        //TODO [2021-05-04 11:35:12] 형태
        str_date = String.valueOf(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_Alarm > setAlarmSetting() 메소드 : 알람 등록 수행 실시]");
        Log.d("","\n"+"[날짜 : "+str_date+"]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            String now = getNowTime2();
            int result = str_date.compareTo(now);
            if(result < 0){ //TODO 저장하려는 시간이 현재시간보다 작은 경우
                getAlertDialog("알람 설정",
                        "\n"+"[설정하려는 알람 시간을 다시 확인해주세요]"+"\n"+"\n"+
                                "[현재 날짜]"+"\n"+String.valueOf(now)+"\n"+"\n"+
                                "[예약할 날짜]"+"\n"+String.valueOf(str_date),
                        "확인",
                        "취소",
                        "");
            }
            else{
                //TODO [알림 매니저 객체 생성]
                AlarmManager alarmManager  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();

                //TODO [인텐트 등록]
                Intent intent = new Intent(A_Alarm2.this, A_AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(A_Alarm2.this, 1, intent, FLAG_MUTABLE);

                //TODO [캘린더에 알림 날짜 저장 실시]
                SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.KOREA);
                try {
                    Date date = simpleDateFormat.parse(str_date);
                    calendar.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d("---","---");
                Log.w("//===========//","================================================");
                Log.d("","\n"+"[A_Alarm > setAlarmSetting() 메소드 : 알람 등록 수행 실시]");
                Log.d("","\n"+"[Calendar : "+calendar.toString()+"]");
                Log.w("//===========//","================================================");
                Log.d("---","---");

                //TODO [알림 등록 실시]
                if (Build.VERSION.SDK_INT >= 23) { //TODO 23보다 큰 경우
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); //TODO 버전 23 이상
                }
                else { //TODO 23보다 크지 않은 경우
                    if(Build.VERSION.SDK_INT >= 19){ //TODO 19보다 큰 경우
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); //TODO 버전 19 이상
                    }
                    else { //TODO 19보다 크지 않은 경우
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent); //TODO 버전 19 미만
                    }
                }

                S_Preference.setString(getApplication(), "Alarm_Date", str_date);

                getAlertDialog("알람 설정",
                        "\n"+"[알림이 정상적으로 예약되었습니다]"+"\n"+"\n"+S_Preference.getString(getApplication(), "Alarm_Date"),
                        "확인",
                        "취소",
                        "");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [알림 등록 취소 메소드]
    public void setAlarmCancle(){
        Log.d("---","---");
        Log.e("//===========//","================================================");
        Log.d("","\n"+"[A_Alarm > setAlarmCancle() 메소드 : 알람 등록 해제 실시]");
        Log.e("//===========//","================================================");
        Log.d("---","---");
        String save = S_Preference.getString(getApplication(), "Alarm_Date");
        try {
            if(save != null && save.length() > 0 && !save.equals("") && !save.contains("null")){
                String now = getNowTime2();
                int result = save.compareTo(now);
                if(result < 0){ //TODO 저장된 시간이 현재 시간보다 작은 경우 (비정상)
                    getAlertDialog("알람 해제",
                            "\n"+"[알람 해제할 시간을 다시 확인해주세요]"+"\n"+"\n"+
                                    "[현재 날짜]"+"\n"+String.valueOf(now)+"\n"+"\n"+
                                    "[저장된 날짜]"+"\n"+S_Preference.getString(getApplication(), "Alarm_Date"),
                            "확인",
                            "취소",
                            "");
                }
                else { //TODO 저장된 시간이 현재 시간보다 큰 경우 (정상)
                    //TODO [알림 매니저 객체 생성]
                    AlarmManager alarmManager  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    //TODO [인텐트 해제]
                    Intent intent = new Intent(A_Alarm2.this, A_AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(A_Alarm2.this, 1, intent, FLAG_MUTABLE);
                    alarmManager.cancel(pendingIntent);
                }
            }
            else {
                getAlertDialog("알람 해제",
                        "\n"+"[등록된 알람 설정이 없습니다]",
                        "확인",
                        "취소",
                        "");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [날짜 형태 데이터 파싱 실시]
    public void getDateParce(String data){
        try {
            int check = 0;
            for(int i=0; i<data.length(); i++) {
                if(data.charAt(i) == '-') { //TODO 날짜 타입
                    check ++;
                }
            }
            if(data.length() > 0) {
                if(check > 0) { //데이터가 한개 초과 저장된 경우
                    for(int j=0; j<=check; j++) { //콤마가 포함된 [기준]으로 문자열을 분리시킨다
                        System.out.println("저장된 데이터 : "+data.split("[-]")[j]);
                        if(j == 0){
                            yyyy = data.split("[-]")[j].trim();
                        }
                        if(j == 1){
                            MM = data.split("[-]")[j].trim();
                        }
                        if(j == 2){
                            dd = data.split("[-]")[j].trim();
                        }
                    }
                    Log.d("---","---");
                    Log.w("//===========//","================================================");
                    Log.d("","\n"+"[A_Alarm > getDateParce() 메소드 : 날짜 형식 데이터 파싱]");
                    Log.d("","\n"+"[[연] 결과 : "+String.valueOf(yyyy)+"]");
                    Log.d("","\n"+"[[월] 결과 : "+String.valueOf(MM)+"]");
                    Log.d("","\n"+"[[일] 결과 : "+String.valueOf(dd)+"]");
                    Log.w("//===========//","================================================");
                    Log.d("---","---");
                }
                else { //데이터가 한개만 저장된 경우
                    Log.d("---","---");
                    Log.w("//===========//","================================================");
                    Log.d("","\n"+"[A_Alarm > getDateParce() 메소드 : 날짜 형식 데이터 파싱]");
                    Log.d("","\n"+"[결과 : "+String.valueOf(data)+"]");
                    Log.w("//===========//","================================================");
                    Log.d("---","---");
                }
            }
            else {
                Log.d("---","---");
                Log.e("//===========//","================================================");
                Log.d("","\n"+"[A_Alarm > getDateParce() 메소드 : 날짜 형식 데이터 파싱]");
                Log.d("","\n"+"[결과 : "+String.valueOf("저장된 데이터를 확인해주세요")+"]");
                Log.e("//===========//","================================================");
                Log.d("---","---");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [시간 형태 데이터 파싱 실시]
    public void getTimeParce(String data){
        try {
            int check = 0;
            for(int i=0; i<data.length(); i++) {
                if(data.charAt(i) == ':') { //TODO 날짜 타입
                    check ++;
                }
            }
            if(data.length() > 0) {
                if(check > 0) { //데이터가 한개 초과 저장된 경우
                    for(int j=0; j<=check; j++) { //콤마가 포함된 [기준]으로 문자열을 분리시킨다
                        System.out.println("저장된 데이터 : "+data.split("[:]")[j]);
                        if(j == 0){
                            hh = data.split("[:]")[j].trim();
                        }
                        if(j == 1){
                            mmm = data.split("[:]")[j].trim();
                        }
                        if(j == 2){
                            ss = data.split("[:]")[j].trim();
                        }
                    }
                    Log.d("---","---");
                    Log.w("//===========//","================================================");
                    Log.d("","\n"+"[A_Alarm > getTimeParce() 메소드 : 시간 형식 데이터 파싱]");
                    Log.d("","\n"+"[[시] 결과 : "+String.valueOf(hh)+"]");
                    Log.d("","\n"+"[[분] 결과 : "+String.valueOf(mmm)+"]");
                    Log.d("","\n"+"[[초] 결과 : "+String.valueOf(ss)+"]");
                    Log.w("//===========//","================================================");
                    Log.d("---","---");
                }
                else { //데이터가 한개만 저장된 경우
                    Log.d("---","---");
                    Log.w("//===========//","================================================");
                    Log.d("","\n"+"[A_Alarm > getTimeParce() 메소드 : 시간 형식 데이터 파싱]");
                    Log.d("","\n"+"[결과 : "+String.valueOf(data)+"]");
                    Log.w("//===========//","================================================");
                    Log.d("---","---");
                }
            }
            else {
                Log.d("---","---");
                Log.e("//===========//","================================================");
                Log.d("","\n"+"[A_Alarm > getTimeParce() 메소드 : 시간 형식 데이터 파싱]");
                Log.d("","\n"+"[결과 : "+String.valueOf("저장된 데이터를 확인해주세요")+"]");
                Log.e("//===========//","================================================");
                Log.d("---","---");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [바깥 레이아웃 클릭 시 키보드 내림]
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        int action = event.getAction();
        switch(action){
            case(MotionEvent.ACTION_DOWN):
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(year_edit.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(month_edit.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(day_edit.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(hour_edit.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(minute_edit.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(second_edit.getWindowToken(), 0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case(MotionEvent.ACTION_MOVE):
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    //TODO [백버튼 터치시 뒤로 가기]
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 디바이스의 키 이벤트가 발생했는데, 뒤로가기 이벤트일때
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("---","---");
            Log.d("//===========//","================================================");
            Log.d("","\n"+"[A_Alarm > onKeyDown() 메소드 : 백버튼 터치시 뒤로 가기 이벤트 실시]");
            Log.d("//===========//","================================================");
            Log.d("---","---");
            //TODO [액티비티 종료 실시]
            finish();
            overridePendingTransition(0,0);
        }
        return true;
    }

    //TODO [액티비티 종료 메소드]
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_Alarm > onDestroy() 메소드 : 액티비티 종료 실시]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        //TODO [변수 초기화 실시]
        try {

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [액티비티 실행 준비 메소드]
    @Override
    public void onResume(){
        super.onResume();
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_Alarm > onResume() 메소드 : 액티비티 실행 준비 실시]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            //TODO [외부 브라우저 복귀 시 화면 전환 애니메이션 없애기 위함]
            overridePendingTransition(0,0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO [일회용 AlertDialog 팝업창 호출 메소드 정의 실시]
    public void getAlertDialog(String header, String content, String ok, String no, String normal){
        //TODO 타이틀 및 내용 표시
        String Tittle = header;
        String Message = content;

        //TODO 버튼 이름 정의
        String buttonNo = no;
        String buttonYes = ok;
        String buttonNature = normal;

        //TODO AlertDialog 팝업창 생성
        new AlertDialog.Builder(A_Alarm2.this)
                .setTitle(Tittle) //팝업창 타이틀 지정
                .setIcon(R.drawable.ic_launcher_background) //팝업창 아이콘 지정
                .setMessage(Message) //팝업창 내용 지정
                .setCancelable(false) //외부 레이아웃 클릭시도 팝업창이 사라지지않게 설정
                .setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .setNeutralButton(buttonNature, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .show();
    }

    //TODO [현재 시간 알아오는 메소드]
    public static String getNowTime2() {
        long time = System.currentTimeMillis();
        //SimpleDateFormat dayTime = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String str = dayTime.format(new Date(time));
        return str;
    }

}//TODO 클래스 종료


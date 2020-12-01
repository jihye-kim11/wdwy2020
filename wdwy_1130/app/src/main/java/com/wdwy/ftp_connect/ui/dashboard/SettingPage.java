package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.LoginActivity;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class SettingPage extends AppCompatActivity {
    SQLiteDatabase sqlDB;
    myDBAdapter dbAdapter;
    com.wdwy.ftp_connect.ui.home.myHelper myHelper;
    String myJSON;
    Switch switchBtn;
    LinearLayout developerQA, logout, delete;
    JSONArray tempAlarm;
    int getAlarm;
    String getPassword;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        switchBtn = (Switch)findViewById(R.id.switch1);
        developerQA = (LinearLayout)findViewById(R.id.developerQA);
        logout = (LinearLayout) findViewById(R.id.logout);
        delete = (LinearLayout) findViewById(R.id.delete);

        // 데이터 수신
        Intent intent = getIntent();
        String getId = intent.getExtras().getString("userId");

        // 알람 정보 받아오기
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_info.php");
            String result = request.PhPtest_userId(String.valueOf(getId));
            myJSON =result.trim();
            getAlarm_();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        if (getAlarm == 1) {
            switchBtn.setChecked(true);
        } else {
            switchBtn.setChecked(false);
        }

//        Log.e("getKeyHash", ""+getKeyHash(SettingPage.this));

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 클릭 시 user테이블 alram on / off
                try {
                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/modify_alarm.php");
                    String result = request.PhPtest_modifyAlarm(String.valueOf(getId), Integer.valueOf(getAlarm));

                    if (result.equals("1")) {
                        Toast.makeText(getApplication(), "변경 완료", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "변경 실패", Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });

        developerQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //개발자 문의 페이지 - 아이디 넘기는 부분 확인
                Intent intent = new Intent(getApplicationContext() ,DeveloperPage.class);
                intent.putExtra("userId", getId);
                startActivity(intent);
            }
        });

        context = getApplicationContext();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그아웃 시 로그인 페이지로 이동
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingPage.this);
                alert.setTitle("로그아웃 하시겠습니까?");
                alert.setMessage("계속하려면 확인을 눌러주세요");
                alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // HomeFragment가 아닌 Login.class로 바꿔줄것.
                        dbAdapter = new myDBAdapter(context);
                        dbAdapter.open();
                        dbAdapter.clear();
                        dbAdapter.close();
//                        dbAdapter.close();
//                        context.deleteDatabase("groupDB.db");
//                        context.deleteDatabase("groupDB-journal.db");
                        //////
//                        File data1 = Environment.getDataDirectory();
//                        File data2 = Environment.getDataDirectory();
//                        String currentDBPath1 = "/data/com.example.ftp_connect/databases/" + "groupDB";
//                        String currentDBPath2 = "/data/com.example.ftp_connect/databases/" + "groupDB-journal";
//                        File currentDB1 = new File(data1, currentDBPath1);
//                        File currentDB2 = new File(data2, currentDBPath2);
//                        boolean deleted1 = SQLiteDatabase.deleteDatabase(currentDB1);
//                        boolean deleted2 = SQLiteDatabase.deleteDatabase(currentDB2);

//                        sqlDB = myHelper.getWritableDatabase();
//                        dbAdapter = new myDBAdapter(context);
//                        dbAdapter.open();
//                        sqlDB.execSQL("DROP TABLE IF EXISTS "+"groupTBL");
//                        myHelper.onCreate(sqlDB);

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
//                        UserApiClient.getInstance().logout(error -> {
//                            return null;
//                        });
//
//                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() { //로그아웃 실행
//                            @Override
//                            public void onCompleteLogout() {
//                                //로그아웃 성공 시 로그인 화면(LoginActivity)로 이동
//                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                            }
//                        });

                    }
                });

                alert.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 탈퇴 시 cascade 이용해서 정보 삭제
                // 데이터 송신
                PHPRequest request = null;
                String result = "";
                try {
                    request = new PHPRequest("http://hyper0616.dothome.co.kr/can_delete.php");
                    result = request.PhPtest_userId(String.valueOf(getId));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if (result.equals("0")) {
                    Toast.makeText(getApplication(), "아직 종료되지 않은 강의가 있습니다 (탈퇴 불가)", Toast.LENGTH_LONG).show();
                }
                else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingPage.this);

                    alert.setTitle("정말 탈퇴하시겠습니까?");
                    alert.setMessage("계속하려면 비밀번호를 입력해주세요");

                    final EditText pw = new EditText(SettingPage.this);
                    alert.setView(pw);

                    alert.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    alert.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (String.valueOf(pw.getText()).equals(getPassword)) {
                                dialogInterface.dismiss();
                                try {
                                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/delete_user_info.php");
                                    String result = request.PhPtest_userId(String.valueOf(getId));

                                    if (result.equals("1")) {
                                        dbAdapter = new myDBAdapter(context);
                                        dbAdapter.open();
                                        dbAdapter.clear();
                                        dbAdapter.close();
                                        Toast.makeText(getApplication(), "탈퇴 성공", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplication(), "실패", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast myToast = Toast.makeText(getApplicationContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT);
                                myToast.show();
                            }
                        }
                    });


                    alert.show();
                }
            }
        });
    }
    protected void getAlarm_(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tempAlarm = jsonObj.getJSONArray("result");

            for(int i=0;i < tempAlarm.length();i++){
                JSONObject c =  tempAlarm.getJSONObject(i);
                int al = c.getInt("alarm");
                String password = c.getString("password");

                getAlarm = al;
                getPassword = password;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    protected void deleteSqlite (SQLiteDatabase database) {
//        database.execSQL("DROP TABLE groupTBL");
//    }

//    public static String getKeyHash(final Context context) {
//        PackageManager pm = context.getPackageManager();
//        try {
//            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
//            if (packageInfo == null)
//                return null;
//
//            for (Signature signature : packageInfo.signatures) {
//                try {
//                    MessageDigest md = MessageDigest.getInstance("SHA");
//                    md.update(signature.toByteArray());
//                    return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    // 뒤로가기 버튼 동작 추가
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

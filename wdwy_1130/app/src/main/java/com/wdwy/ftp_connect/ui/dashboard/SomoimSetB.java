package com.wdwy.ftp_connect.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class SomoimSetB extends AppCompatActivity {
    private String class_no2;
    private String userId;
    myDBAdapter dbAdapter;
    String myJSON, myJSON2;
    String getName, getImage = "no";
    JSONArray tempName;
    JSONArray times;
    String monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    static char[] mon_sch = new char[5];
    static char[] tue_sch = new char[5];
    static char[] wed_sch = new char[5];
    static char[] thu_sch = new char[5];
    static char[] fri_sch = new char[5];
    static char[] sat_sch = new char[5];
    static char[] sun_sch = new char[5];

    ToggleButton mon1, mon2, mon3, mon4, mon5;
    ToggleButton tue1, tue2, tue3, tue4, tue5;
    ToggleButton wed1, wed2, wed3, wed4, wed5;
    ToggleButton thu1, thu2, thu3, thu4, thu5;
    ToggleButton fri1, fri2, fri3, fri4, fri5;
    ToggleButton sat1, sat2, sat3, sat4, sat5;
    ToggleButton sun1, sun2, sun3, sun4, sun5;

    ImageView profile_img;
    TextView time_view, name;

    private ImageButton backButton;
    private ImageView week_view;

    private RecyclerView recyclerView;
    private SomoimSetAdapter adapter;
    int count=0;
    int i;

    ArrayList<String> mon = new ArrayList<>();
    ArrayList<String> tue = new ArrayList<>();
    ArrayList<String> wed = new ArrayList<>();
    ArrayList<String> thu = new ArrayList<>();
    ArrayList<String> fri = new ArrayList<>();
    ArrayList<String> sat = new ArrayList<>();
    ArrayList<String> sun = new ArrayList<>();

    char[][] mon_sch_ = new char[5][5];
    char[][] tue_sch_ = new char[5][5];
    char[][] wed_sch_ = new char[5][5];
    char[][] thu_sch_ = new char[5][5];
    char[][] fri_sch_ = new char[5][5];
    char[][] sat_sch_ = new char[5][5];
    char[][] sun_sch_ = new char[5][5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somoim_set_b);
        NetworkUtil.setNetworkPolicy();
        getSupportActionBar().hide();

        //이전 페이지에서 값 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");

        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        week_view = (ImageView) findViewById(R.id.week_view);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        time_view = (TextView) findViewById(R.id.time_view);

        //php 연결하여 이미지와 이름 가져오기
        profile_img = (ImageView) findViewById(R.id.profile_img);
        name = (TextView) findViewById(R.id.name);

        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_info.php");
            String result = request.PhPtest_userId(userId);
            myJSON = result.trim();
            getInfo();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        name.setText(getName);
        if (!getImage.equals("no")) {
            try {
                URL url = new URL(getImage);

                URLConnection conn = url.openConnection();
                conn.connect();

                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                profile_img.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //phpcode로 mon_sch~sun_sch 불러오기
        //불러온 것 바탕으로 toggle button 기본 값 설정해둬야?      => 해결
        //if mon_sch[0] == '1' -> mon1.isChecked()
        //else -> mon1.isCehcked() == false

        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_schedule.php");
            String result2 = request.PhPtest_getsch(class_no2, userId);
            myJSON2 = result2.trim();
            bring_sch();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            mon_sch[i] = monday.charAt(i);
            tue_sch[i] = tuesday.charAt(i);
            wed_sch[i] = wednesday.charAt(i);
            thu_sch[i] = thursday.charAt(i);
            fri_sch[i] = friday.charAt(i);
            sat_sch[i] = saturday.charAt(i);
            sun_sch[i] = sunday.charAt(i);
        }

        //System.out.println("char mon:" + mon_sch[0] + mon_sch[1] + mon_sch[2]+mon_sch[3]+mon_sch[4] );
        //System.out.println("char tue:" + tue_sch[0] + tue_sch[1] + tue_sch[2]+tue_sch[3]+tue_sch[4] );
        //여기까지는 완료


        //toggle click code
        //변경 후 UPDATE 필요 (php code)
        //이걸 어댑터에서 어떻게 사용..?
        //mon1 = (ToggleButton) findViewById(R.id.mon1);
        if (mon_sch[0] == '1') {
            mon1 = (ToggleButton) findViewById(R.id.mon1);
            mon1.setBackgroundResource(R.color.colorPrimary);
            mon1.setText("가능");
        } else mon1 = (ToggleButton) findViewById(R.id.mon1);
        mon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon1.isChecked()) {
                    mon1.setBackgroundResource(R.color.colorPrimary);
                    mon1.setText("가능");
                    mon_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mon1.setBackgroundResource(R.color.colorgray);
                    mon_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (mon_sch[1] == '1') {
            mon2 = (ToggleButton) findViewById(R.id.mon2);
            mon2.setBackgroundResource(R.color.colorPrimary);
            mon2.setText("가능");
        } else mon2 = (ToggleButton) findViewById(R.id.mon2);
        mon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon2.isChecked()) {
                    mon2.setBackgroundResource(R.color.colorPrimary);
                    mon2.setText("가능");
                    mon_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mon1.setBackgroundResource(R.color.colorgray);
                    mon_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (mon_sch[2] == '1') {
            mon3 = (ToggleButton) findViewById(R.id.mon3);
            mon3.setBackgroundResource(R.color.colorPrimary);
            mon3.setText("가능");
        } else mon3 = (ToggleButton) findViewById(R.id.mon3);
        mon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon3.isChecked()) {
                    mon3.setBackgroundResource(R.color.colorPrimary);
                    mon3.setText("가능");
                    mon_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mon1.setBackgroundResource(R.color.colorgray);
                    mon_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (mon_sch[3] == '1') {
            mon4 = (ToggleButton) findViewById(R.id.mon4);
            mon4.setBackgroundResource(R.color.colorPrimary);
            mon4.setText("가능");
        } else mon4 = (ToggleButton) findViewById(R.id.mon4);
        mon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon4.isChecked()) {
                    mon4.setBackgroundResource(R.color.colorPrimary);
                    mon4.setText("가능");
                    mon_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mon1.setBackgroundResource(R.color.colorgray);
                    mon_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (mon_sch[4] == '1') {
            mon5 = (ToggleButton) findViewById(R.id.mon5);
            mon5.setBackgroundResource(R.color.colorPrimary);
            mon5.setText("가능");
        } else mon5 = (ToggleButton) findViewById(R.id.mon5);
        mon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon5.isChecked()) {
                    mon5.setBackgroundResource(R.color.colorPrimary);
                    mon5.setText("가능");
                    mon_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mon1.setBackgroundResource(R.color.colorgray);
                    mon_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_mon.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(mon_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        if (tue_sch[0] == '1') {
            tue1 = (ToggleButton) findViewById(R.id.tue1);
            tue1.setBackgroundResource(R.color.colorPrimary);
            tue1.setText("가능");
        } else tue1 = (ToggleButton) findViewById(R.id.tue1);
        tue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue1.isChecked()) {
                    tue1.setBackgroundResource(R.color.colorPrimary);
                    tue1.setText("가능");
                    tue_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    tue1.setBackgroundResource(R.color.colorgray);
                    tue_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (tue_sch[1] == '1') {
            tue2 = (ToggleButton) findViewById(R.id.tue2);
            tue2.setBackgroundResource(R.color.colorPrimary);
            tue2.setText("가능");
        } else tue2 = (ToggleButton) findViewById(R.id.tue2);
        tue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue2.isChecked()) {
                    tue2.setBackgroundResource(R.color.colorPrimary);
                    tue2.setText("가능");
                    tue_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    tue2.setBackgroundResource(R.color.colorgray);
                    tue_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (tue_sch[2] == '1') {
            tue3 = (ToggleButton) findViewById(R.id.tue3);
            tue3.setBackgroundResource(R.color.colorPrimary);
            tue3.setText("가능");
        } else tue3 = (ToggleButton) findViewById(R.id.tue3);
        tue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue3.isChecked()) {
                    tue3.setBackgroundResource(R.color.colorPrimary);
                    tue3.setText("가능");
                    tue_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    tue3.setBackgroundResource(R.color.colorgray);
                    tue_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (tue_sch[3] == '1') {
            tue4 = (ToggleButton) findViewById(R.id.tue4);
            tue4.setBackgroundResource(R.color.colorPrimary);
            tue4.setText("가능");
        } else tue4 = (ToggleButton) findViewById(R.id.tue4);
        tue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue4.isChecked()) {
                    tue4.setBackgroundResource(R.color.colorPrimary);
                    tue4.setText("가능");
                    tue_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    tue4.setBackgroundResource(R.color.colorgray);
                    tue_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (tue_sch[4] == '1') {
            tue5 = (ToggleButton) findViewById(R.id.tue5);
            tue5.setBackgroundResource(R.color.colorPrimary);
            tue5.setText("가능");
        } else tue5 = (ToggleButton) findViewById(R.id.tue5);
        tue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue5.isChecked()) {
                    tue5.setBackgroundResource(R.color.colorPrimary);
                    tue5.setText("가능");
                    tue_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    tue5.setBackgroundResource(R.color.colorgray);
                    tue_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_tue.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(tue_sch));
                        //myJSON2 = result2.trim();
                        //bring_sch();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //wed
        if (wed_sch[0] == '1') {
            wed1 = (ToggleButton) findViewById(R.id.wed1);
            wed1.setBackgroundResource(R.color.colorPrimary);
            wed1.setText("가능");
        } else wed1 = (ToggleButton) findViewById(R.id.wed1);
        wed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed1.isChecked()) {
                    wed1.setBackgroundResource(R.color.colorPrimary);
                    wed1.setText("가능");
                    wed_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    wed1.setBackgroundResource(R.color.colorgray);
                    wed_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (wed_sch[1] == '1') {
            wed2 = (ToggleButton) findViewById(R.id.wed2);
            wed2.setBackgroundResource(R.color.colorPrimary);
            wed2.setText("가능");
        } else wed2 = (ToggleButton) findViewById(R.id.wed2);
        wed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed2.isChecked()) {
                    wed2.setBackgroundResource(R.color.colorPrimary);
                    wed2.setText("가능");
                    wed_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    wed2.setBackgroundResource(R.color.colorgray);
                    wed_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (wed_sch[2] == '1') {
            wed3 = (ToggleButton) findViewById(R.id.wed3);
            wed3.setBackgroundResource(R.color.colorPrimary);
            wed3.setText("가능");
        } else wed3 = (ToggleButton) findViewById(R.id.wed3);
        wed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed3.isChecked()) {
                    wed3.setBackgroundResource(R.color.colorPrimary);
                    wed3.setText("가능");
                    wed_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    wed3.setBackgroundResource(R.color.colorgray);
                    wed_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (wed_sch[3] == '1') {
            wed4 = (ToggleButton) findViewById(R.id.wed4);
            wed4.setBackgroundResource(R.color.colorPrimary);
            wed4.setText("가능");
        } else wed4 = (ToggleButton) findViewById(R.id.wed4);
        wed4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed4.isChecked()) {
                    wed4.setBackgroundResource(R.color.colorPrimary);
                    wed4.setText("가능");
                    wed_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    wed4.setBackgroundResource(R.color.colorgray);
                    wed_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (wed_sch[4] == '1') {
            wed5 = (ToggleButton) findViewById(R.id.wed5);
            wed5.setBackgroundResource(R.color.colorPrimary);
            wed5.setText("가능");
        } else wed5 = (ToggleButton) findViewById(R.id.wed5);
        wed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed5.isChecked()) {
                    wed5.setBackgroundResource(R.color.colorPrimary);
                    wed5.setText("가능");
                    wed_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    wed5.setBackgroundResource(R.color.colorgray);
                    wed_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_wed.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(wed_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //thu
        if (thu_sch[0] == '1') {
            thu1 = (ToggleButton) findViewById(R.id.thu1);
            thu1.setBackgroundResource(R.color.colorPrimary);
            thu1.setText("가능");
        } else thu1 = (ToggleButton) findViewById(R.id.thu1);
        thu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu1.isChecked()) {
                    thu1.setBackgroundResource(R.color.colorPrimary);
                    thu1.setText("가능");
                    thu_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    thu1.setBackgroundResource(R.color.colorgray);
                    thu_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (thu_sch[1] == '1') {
            thu2 = (ToggleButton) findViewById(R.id.thu2);
            thu2.setBackgroundResource(R.color.colorPrimary);
            thu2.setText("가능");
        } else thu2 = (ToggleButton) findViewById(R.id.thu2);
        thu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu2.isChecked()) {
                    thu2.setBackgroundResource(R.color.colorPrimary);
                    thu2.setText("가능");
                    thu_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    thu1.setBackgroundResource(R.color.colorgray);
                    thu_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (thu_sch[2] == '1') {
            thu3 = (ToggleButton) findViewById(R.id.thu3);
            thu3.setBackgroundResource(R.color.colorPrimary);
            thu3.setText("가능");
        } else thu3 = (ToggleButton) findViewById(R.id.thu3);
        thu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu3.isChecked()) {
                    thu3.setBackgroundResource(R.color.colorPrimary);
                    thu3.setText("가능");
                    thu_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    thu3.setBackgroundResource(R.color.colorgray);
                    thu_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (thu_sch[3] == '1') {
            thu4 = (ToggleButton) findViewById(R.id.thu4);
            thu4.setBackgroundResource(R.color.colorPrimary);
            thu4.setText("가능");
        } else thu4 = (ToggleButton) findViewById(R.id.thu4);
        thu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu4.isChecked()) {
                    thu4.setBackgroundResource(R.color.colorPrimary);
                    thu4.setText("가능");
                    thu_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    thu4.setBackgroundResource(R.color.colorgray);
                    thu_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (thu_sch[4] == '1') {
            thu5 = (ToggleButton) findViewById(R.id.thu5);
            thu5.setBackgroundResource(R.color.colorPrimary);
            thu5.setText("가능");
        } else thu5 = (ToggleButton) findViewById(R.id.thu5);
        thu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu5.isChecked()) {
                    thu5.setBackgroundResource(R.color.colorPrimary);
                    thu5.setText("가능");
                    thu_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    thu5.setBackgroundResource(R.color.colorgray);
                    thu_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_thu.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(thu_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //fri
        if (fri_sch[0] == '1') {
            fri1 = (ToggleButton) findViewById(R.id.fri1);
            fri1.setBackgroundResource(R.color.colorPrimary);
            fri1.setText("가능");
        } else fri1 = (ToggleButton) findViewById(R.id.fri1);
        fri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri1.isChecked()) {
                    fri1.setBackgroundResource(R.color.colorPrimary);
                    fri1.setText("가능");
                    fri_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    fri1.setBackgroundResource(R.color.colorgray);
                    fri_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (fri_sch[1] == '1') {
            fri2 = (ToggleButton) findViewById(R.id.fri2);
            fri2.setBackgroundResource(R.color.colorPrimary);
            fri2.setText("가능");
        } else fri2 = (ToggleButton) findViewById(R.id.fri2);
        fri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri2.isChecked()) {
                    fri2.setBackgroundResource(R.color.colorPrimary);
                    fri2.setText("가능");
                    fri_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    fri2.setBackgroundResource(R.color.colorgray);
                    fri_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (fri_sch[2] == '1') {
            fri3 = (ToggleButton) findViewById(R.id.fri3);
            fri3.setBackgroundResource(R.color.colorPrimary);
            fri3.setText("가능");
        } else fri3 = (ToggleButton) findViewById(R.id.fri3);
        fri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri3.isChecked()) {
                    fri3.setBackgroundResource(R.color.colorPrimary);
                    fri3.setText("가능");
                    fri_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    fri3.setBackgroundResource(R.color.colorgray);
                    fri_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (fri_sch[3] == '1') {
            fri4 = (ToggleButton) findViewById(R.id.fri4);
            fri4.setBackgroundResource(R.color.colorPrimary);
            fri4.setText("가능");
        } else fri4 = (ToggleButton) findViewById(R.id.fri4);
        fri4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri4.isChecked()) {
                    fri4.setBackgroundResource(R.color.colorPrimary);
                    fri4.setText("가능");
                    fri_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    fri4.setBackgroundResource(R.color.colorgray);
                    fri_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (fri_sch[4] == '1') {
            fri5 = (ToggleButton) findViewById(R.id.fri5);
            fri5.setBackgroundResource(R.color.colorPrimary);
            fri5.setText("가능");
        } else fri5 = (ToggleButton) findViewById(R.id.fri5);
        fri5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri5.isChecked()) {
                    fri5.setBackgroundResource(R.color.colorPrimary);
                    fri5.setText("가능");
                    fri_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    fri5.setBackgroundResource(R.color.colorgray);
                    fri_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_fri.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(fri_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        //sat
        if (sat_sch[0] == '1') {
            sat1 = (ToggleButton) findViewById(R.id.sat1);
            sat1.setBackgroundResource(R.color.colorPrimary);
            sat1.setText("가능");
        } else sat1 = (ToggleButton) findViewById(R.id.sat1);
        sat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat1.isChecked()) {
                    sat1.setBackgroundResource(R.color.colorPrimary);
                    sat1.setText("가능");
                    sat_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sat1.setBackgroundResource(R.color.colorgray);
                    sat_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sat_sch[1] == '1') {
            sat2 = (ToggleButton) findViewById(R.id.sat2);
            sat2.setBackgroundResource(R.color.colorPrimary);
            sat2.setText("가능");
        } else sat2 = (ToggleButton) findViewById(R.id.sat2);
        sat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat2.isChecked()) {
                    sat2.setBackgroundResource(R.color.colorPrimary);
                    sat2.setText("가능");
                    sat_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sat2.setBackgroundResource(R.color.colorgray);
                    sat_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sat_sch[2] == '1') {
            sat3 = (ToggleButton) findViewById(R.id.sat3);
            sat3.setBackgroundResource(R.color.colorPrimary);
            sat3.setText("가능");
        } else sat3 = (ToggleButton) findViewById(R.id.sat3);
        sat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat3.isChecked()) {
                    sat3.setBackgroundResource(R.color.colorPrimary);
                    sat3.setText("가능");
                    sat_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sat3.setBackgroundResource(R.color.colorgray);
                    sat_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sat_sch[3] == '1') {
            sat4 = (ToggleButton) findViewById(R.id.sat4);
            sat4.setBackgroundResource(R.color.colorPrimary);
            sat4.setText("가능");
        } else sat4 = (ToggleButton) findViewById(R.id.sat4);
        sat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat4.isChecked()) {
                    sat4.setBackgroundResource(R.color.colorPrimary);
                    sat4.setText("가능");
                    sat_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sat4.setBackgroundResource(R.color.colorgray);
                    sat_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sat_sch[4] == '1') {
            sat5 = (ToggleButton) findViewById(R.id.sat5);
            sat5.setBackgroundResource(R.color.colorPrimary);
            sat5.setText("가능");
        } else sat5 = (ToggleButton) findViewById(R.id.sat5);
        sat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat5.isChecked()) {
                    sat5.setBackgroundResource(R.color.colorPrimary);
                    sat5.setText("가능");
                    sat_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sat5.setBackgroundResource(R.color.colorgray);
                    sat_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sat.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sat_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //sun
        if (sun_sch[0] == '1') {
            sun1 = (ToggleButton) findViewById(R.id.sun1);
            sun1.setBackgroundResource(R.color.colorPrimary);
            sun1.setText("가능");
        } else sun1 = (ToggleButton) findViewById(R.id.sun1);
        sun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun1.isChecked()) {
                    sun1.setBackgroundResource(R.color.colorPrimary);
                    sun1.setText("가능");
                    sun_sch[0] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sun1.setBackgroundResource(R.color.colorgray);
                    sun_sch[0] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sun_sch[1] == '1') {
            sun2 = (ToggleButton) findViewById(R.id.sun2);
            sun2.setBackgroundResource(R.color.colorPrimary);
            sun2.setText("가능");
        } else sun2 = (ToggleButton) findViewById(R.id.sun2);
        sun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun2.isChecked()) {
                    sun2.setBackgroundResource(R.color.colorPrimary);
                    sun2.setText("가능");
                    sun_sch[1] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sun2.setBackgroundResource(R.color.colorgray);
                    sun_sch[1] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sun_sch[2] == '1') {
            sun3 = (ToggleButton) findViewById(R.id.sun3);
            sun3.setBackgroundResource(R.color.colorPrimary);
            sun3.setText("가능");
        } else sun3 = (ToggleButton) findViewById(R.id.sun3);
        sun3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun3.isChecked()) {
                    sun3.setBackgroundResource(R.color.colorPrimary);
                    sun3.setText("가능");
                    sun_sch[2] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sun3.setBackgroundResource(R.color.colorgray);
                    sun_sch[2] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sun_sch[3] == '1') {
            sun4 = (ToggleButton) findViewById(R.id.sun4);
            sun4.setBackgroundResource(R.color.colorPrimary);
            sun4.setText("가능");
        } else sun4 = (ToggleButton) findViewById(R.id.sun4);
        sun4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun4.isChecked()) {
                    sun4.setBackgroundResource(R.color.colorPrimary);
                    sun4.setText("가능");
                    sun_sch[3] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sun4.setBackgroundResource(R.color.colorgray);
                    sun_sch[3] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if (sun_sch[4] == '1') {
            sun5 = (ToggleButton) findViewById(R.id.sun5);
            sun5.setBackgroundResource(R.color.colorPrimary);
            sun5.setText("가능");
        } else sun5 = (ToggleButton) findViewById(R.id.sun5);
        sun5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun5.isChecked()) {
                    sun5.setBackgroundResource(R.color.colorPrimary);
                    sun5.setText("가능");
                    sun_sch[4] = '1';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    sun5.setBackgroundResource(R.color.colorgray);
                    sun_sch[4] = '0';
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/push_sun.php");
                        String result2 = request.PhPtest_pushsch(class_no2, userId, String.valueOf(sun_sch));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /*
        // other users button
        recyclerView = findViewById(R.id.recycler_somoim);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 Adapter 객체 지정.
        adapter = new SomoimSetAdapter(this);
        recyclerView.setAdapter(adapter);
        ArrayList<String> users = new ArrayList<>();
        //전체 유저 불러오기
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_users_except.php");
            String result = request.PhPtest_send2(class_no2, userId);
            StringTokenizer st1 = new StringTokenizer(result);
            count = st1.countTokens();
            while (st1.hasMoreTokens()) {
                users.add(st1.nextToken());
                i++;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //여기부터
        //각 유저별 스케쥴 불러오기
        for (int j = 0; j < count; j++) {
            try {
                PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_schedule.php");
                String result2 = request.PhPtest_getsch(class_no2, users.get(j));
                myJSON2 = result2.trim();
                bring_sch();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        //각자 스케쥴 char 형식으로 바꾸기
        for (int j = 0; j < count; j++) {
            for (i = 0; i < 5; i++) {
                mon_sch_[j][i] = mon.get(j).charAt(i);
                tue_sch_[j][i] = tue.get(j).charAt(i);
                wed_sch_[j][i] = wed.get(j).charAt(i);
                thu_sch_[j][i] = thu.get(j).charAt(i);
                fri_sch_[j][i] = fri.get(j).charAt(i);
                sat_sch_[j][i] = sat.get(j).charAt(i);
                sun_sch_[j][i] = sun.get(j).charAt(i);
            }
        }

        for (int j = count; j < 5; j++) {
            for (i = 0; i < 5; i++) {
                mon_sch_[j][i] = '1';
                tue_sch_[j][i] = '1';
                wed_sch_[j][i] = '1';
                thu_sch_[j][i] = '1';
                fri_sch_[j][i] = '1';
                sat_sch_[j][i] = '1';
                sun_sch_[j][i] = '1';
            }
        }

        for (int j = count; j < count; j++) {
            adapter.addItem(new TimeList(mon_sch_[j], tue_sch_[j], wed_sch_[j], thu_sch_[j], fri_sch_[j], sat_sch_[j], sun_sch_[j]));
        }
        recyclerView.setAdapter(adapter);
        */
    }

    protected void getInfo() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tempName = jsonObj.getJSONArray("result");

            for (int i = 0; i < tempName.length(); i++) {
                JSONObject c = tempName.getJSONObject(i);
                String name = c.getString("name");

//                 이미지는 추후 수정
                String image;
                if (!c.getString("image").equals(null)) {
                    image = c.getString("image");
                    getImage = image;
                }
                getName = name;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void bring_sch() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON2);
            times = jsonObj.getJSONArray("result");

            for (int i = 0; i < times.length(); i++) {
                JSONObject c = times.getJSONObject(i);
                String mon_ = c.getString("mon_");
                String tue_ = c.getString("tue_");
                String wed_ = c.getString("wed_");
                String thu_ = c.getString("thu_");
                String fri_ = c.getString("fri_");
                String sat_ = c.getString("sat_");
                String sun_ = c.getString("sun_");

                monday = mon_;
                tuesday = tue_;
                wednesday = wed_;
                thursday = thu_;
                friday = fri_;
                saturday = sat_;
                sunday = sun_;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SomoimPage extends AppCompatActivity {
    String myJSON, myJSON2;;
    JSONArray dates, times;
    private String class_no2;
    private String userId;
    myDBAdapter dbAdapter;
    private SomoimAdapter adapter;
    private RecyclerView recyclerView;

    private TextView Somoim;
    private ImageView introduce;
    private ImageButton plusButton, backButton;
    int count, randnum, counting;
    int i = 0;
    int time, yoil;
    String yoils;

    ArrayList<String> mon = new ArrayList<>();
    ArrayList<String> tue = new ArrayList<>();
    ArrayList<String> wed = new ArrayList<>();
    ArrayList<String> thu = new ArrayList<>();
    ArrayList<String> fri = new ArrayList<>();
    ArrayList<String> sat = new ArrayList<>();
    ArrayList<String> sun = new ArrayList<>();

    char[][] mon_sch = new char[5][5];
    char[][] tue_sch = new char[5][5];
    char[][] wed_sch = new char[5][5];
    char[][] thu_sch = new char[5][5];
    char[][] fri_sch = new char[5][5];
    char[][] sat_sch = new char[5][5];
    char[][] sun_sch = new char[5][5];

    char[][] on_times = new char[5][7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somoim_page);
        NetworkUtil.setNetworkPolicy();
        getSupportActionBar().hide();




        //이전 페이지에서 값 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");

        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> users = new ArrayList<>();
        //ArrayList<UserClass> users = new ArrayList<>();

        Somoim = (TextView) findViewById(R.id.Somoim);
        introduce = (ImageView) findViewById(R.id.introduce);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        plusButton = (ImageButton) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SomoimSetB.class);
                intent.putExtra("class_no2", class_no2);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_somoim);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 Adapter 객체 지정.
        adapter = new SomoimAdapter(list);
        recyclerView.setAdapter(adapter);

        //전체 유저 불러오기
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_users.php");
            String result = request.PhPtest_search(class_no2);
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
                mon_sch[j][i] = mon.get(j).charAt(i);
                tue_sch[j][i] = tue.get(j).charAt(i);
                wed_sch[j][i] = wed.get(j).charAt(i);
                thu_sch[j][i] = thu.get(j).charAt(i);
                fri_sch[j][i] = fri.get(j).charAt(i);
                sat_sch[j][i] = sat.get(j).charAt(i);
                sun_sch[j][i] = sun.get(j).charAt(i);
            }
        }

        for (int j = count; j < 5; j++) {
            for (i = 0; i < 5; i++) {
                mon_sch[j][i] = '1';
                tue_sch[j][i] = '1';
                wed_sch[j][i] = '1';
                thu_sch[j][i] = '1';
                fri_sch[j][i] = '1';
                sat_sch[j][i] = '1';
                sun_sch[j][i] = '1';
            }
        }

        for(i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.println(fri_sch[i][j]);

            }
        }

        randnum = 0;
        for (i = 0; i < 5; i++) {
            if (mon_sch[0][i] == '1' && mon_sch[1][i] == '1' && mon_sch[2][i] == '1' && mon_sch[3][i] == '1' && mon_sch[4][i] == '1') {
                on_times[i][0] = '1';
                randnum++;
            } else on_times[i][0] = '0';
            if (tue_sch[0][i] == '1' && tue_sch[1][i] == '1' && tue_sch[2][i] == '1' && tue_sch[3][i] == '1' && tue_sch[4][i] == '1') {
                on_times[i][1] = '1';
                randnum++;
            } else on_times[i][1] = '0';
            if (wed_sch[0][i] == '1' && wed_sch[1][i] == '1' && wed_sch[2][i] == '1' && wed_sch[3][i] == '1' && wed_sch[4][i] == '1') {
                on_times[i][2] = '1';
                randnum++;
            } else on_times[i][2] = '0';
            if (thu_sch[0][i] == '1' && thu_sch[1][i] == '1' && thu_sch[2][i] == '1' && thu_sch[3][i] == '1' && thu_sch[4][i] == '1') {
                on_times[i][3] = '1';
                randnum++;
            } else on_times[i][3] = '0';
            if (fri_sch[0][i] == '1' && fri_sch[1][i] == '1' && fri_sch[2][i] == '1' && fri_sch[3][i] == '1' && fri_sch[4][i] == '1') {
                on_times[i][4] = '1';
                randnum++;
            } else on_times[i][4] = '0';
            if (sat_sch[0][i] == '1' && sat_sch[1][i] == '1' && sat_sch[2][i] == '1' && sat_sch[3][i] == '1' && sat_sch[4][i] == '1') {
                on_times[i][5] = '1';
                randnum++;
            } else on_times[i][5] = '0';
            if (sun_sch[0][i] == '1' && sun_sch[1][i] == '1' && sun_sch[2][i] == '1' && sun_sch[3][i] == '1' && sun_sch[4][i] == '1') {
                on_times[i][6] = '1';
                randnum++;
            } else on_times[i][6] = '0';
        }
        System.out.println("randnum : " + randnum);

        int n = (int) (Math.random() * randnum) + 1;
        System.out.println("n : " + n);

        count = 0;
        //on_times 중 하나 랜덤으로 골라 날짜로 바꾸기
        for (int j = 0; j < 5; j++) {
            for (i = 0; i < 7; i++) {
                if (on_times[j][i] == '1') {
                    count++;
                    if (count == n) {
                        System.out.println("count : " + count);
                        time = j;
                        yoil = i;
                        break;
                    }
                }
            }
        }
        System.out.println("somoim : " + yoil + " " +time);

        if (time == 0) time = 1;
        else if (time == 1) time = 3;
        else if (time == 2) time = 5;
        else if (time == 3) time = 7;
        else time = 9;

        if (yoil == 0) yoils = "MONDAY";
        else if (yoil == 1) yoils = "TUSEDAY";
        else if (yoil == 2) yoils = "WEDNESDAY";
        else if (yoil == 3) yoils = "THURSDAY";
        else if (yoil == 4) yoils = "FRIDAY";
        else if (yoil == 5) yoils = "SATURDAY";
        else if (yoil == 6) yoils = "SUNDAY";

        System.out.println("somoim date : " + yoils + " " +time);

        //SOMOIM 저장하기
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/save_somoim.php");
            String result = request.PhPtest_send3(class_no2, String.valueOf(time), yoils);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        //somoim 가져오기
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_somoim_date.php");
            String result = request.PhPtest_search(class_no2);
            myJSON = result.trim();
            showList();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            dates = jsonObj.getJSONArray("result");

            for (int i = 0; i < dates.length(); i++) {
                JSONObject c = dates.getJSONObject(i);
                String day = c.getString("day");
                //String year = c.getString("year");
                //String month = c.getString("month");
                //String day = c.getString("day");
                String ap = c.getString("ap");
                String hour = c.getString("hour");
                HashMap<String, String> date_ = new HashMap<String, String>();
                //date_.put("year", year);
                //date_.put("month", month);
                date_.put("day", day);
                date_.put("ap", ap);
                date_.put("hour", hour);

                String date = "● " + day + " : " + ap + " " + hour + " ●\n";
                adapter.addItem(date);
            }
            recyclerView.setAdapter(adapter);

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

                mon.add(mon_);
                tue.add(tue_);
                wed.add(wed_);
                thu.add(thu_);
                fri.add(fri_);
                sat.add(sat_);
                sun.add(sun_);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
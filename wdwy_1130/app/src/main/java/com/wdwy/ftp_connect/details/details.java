package com.wdwy.ftp_connect.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.details.fragment2.Fragment2;
import com.wdwy.ftp_connect.details.fragment3.Fragment3;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class details extends AppCompatActivity {
    public TextView class_name_t,user_name_t,class_category_t,class_price_t,class_time_t,class_start_t,class_count_t,class_runningtime_t,class_max_student_t;
    public ImageView class_image_btn;
    String myJSON;
    String search,no;
    String class_image;
    public ImageButton back_btn;
    String class_name,user_name,class_category,class_price,class_time,class_start,class_count,class_runningtime,class_max_student;
    JSONArray classes = null;

    TabLayout tabs;
    Fragment selected;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    public Button pay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent() ;
        search=intent.getStringExtra("classno2");
        no=intent.getStringExtra("classno");
        class_name_t=(TextView)findViewById(R.id.class_name);
        user_name_t=(TextView)findViewById(R.id.user_name);
        class_category_t=(TextView)findViewById(R.id.class_category);
        class_price_t=(TextView)findViewById(R.id.class_price);
        class_time_t=(TextView)findViewById(R.id.class_time);
        class_start_t=(TextView)findViewById(R.id.class_start);
        class_count_t=(TextView)findViewById(R.id.class_count);
        class_runningtime_t=(TextView)findViewById(R.id.class_runningtime);
        class_max_student_t=(TextView)findViewById(R.id.class_max_student);
        class_image_btn=(ImageButton)findViewById(R.id.class_image);
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/class_data1.php");
            String result = request.PhPtest_search(String.valueOf(search));
            myJSON =result.trim();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
            class_name=classes.getJSONObject(0).getString("class_name");
            class_name_t.setText(class_name);
            user_name=classes.getJSONObject(0).getString("user_name");
            user_name_t.setText(user_name);
            class_category=classes.getJSONObject(0).getString("class_category");
            class_category_t.setText(class_category);
            class_price=classes.getJSONObject(0).getString("class_price");
            class_price_t.setText(class_price);
            class_time=classes.getJSONObject(0).getString("class_time");
            class_time_t.setText(class_time);
            class_start=classes.getJSONObject(0).getString("class_start");
            class_start_t.setText(class_start);
            class_count=classes.getJSONObject(0).getString("class_count");
            class_count_t.setText(class_count);
            class_runningtime=classes.getJSONObject(0).getString("class_runningtime");
            class_runningtime_t.setText(class_runningtime);
            class_max_student=classes.getJSONObject(0).getString("class_max_student");
            class_max_student_t.setText(class_max_student);
            class_image=classes.getJSONObject(0).getString("class_image");
            Glide.with(this).load(class_image)
                    .into( class_image_btn);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        selected =  fragment1;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
        Bundle bundle = new Bundle();
        bundle.putString("key",no); // Key, Value
        fragment2.setArguments(bundle);
        fragment3.setArguments(bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putString("key",search); // Key, Value
        fragment1.setArguments(bundle2);
        tabs = (TabLayout) findViewById(R.id.tab_layout) ;
        tabs.addTab(tabs.newTab().setText("클래스 소개"));
        tabs.addTab(tabs.newTab().setText("후기"));
        tabs.addTab(tabs.newTab().setText("Q/A"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //Fragment selected = null;
                if(position == 0)
                    selected = fragment1;
                else if(position == 1)
                    selected = fragment2;
                else if(position == 2)
                    selected = fragment3;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setActionBar();
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 종료 후 이전페이지가 나타남
            }
        });

        pay_btn=(Button)findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {//인텐트로 pay 액티비티에 classno2 넘기기
                Intent intent = new Intent(getApplicationContext(), pay.class);
                intent.putExtra( "class_name",class_name);//문자열 넘기기
                intent.putExtra( "user_name",user_name);//문자열 넘기기
                intent.putExtra( "class_price",class_price);//문자열 넘기기
                intent.putExtra( "classno2",search);//문자열 넘기기
                intent.putExtra( "classno",no);//문자열 넘기기
                startActivity(intent);
            }
        });

    }
    private void setActionBar(){
        CustomActionBar_details ca=new CustomActionBar_details(this, getSupportActionBar());
        ca.setActionBar();
    }
}
package com.wdwy.ftp_connect.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.details.details;
import com.wdwy.ftp_connect.ui.home.search.CustomActionBar_search;
import com.wdwy.ftp_connect.ui.home.search.Item_search;
import com.wdwy.ftp_connect.ui.home.search.Myadapter_search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class category extends AppCompatActivity {
    String myJSON;
    JSONArray classes = null;
    String category;
    private RecyclerView recyclerView;
    private Myadapter_search adapter;
    public TextView category_bar;
    public ImageButton back_btn;
    public String no2,no;//수정 부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setActionBar();
        NetworkUtil.setNetworkPolicy();


        recyclerView = findViewById(R.id.category_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false); //레이아웃매니저 생성



        recyclerView.setLayoutManager(layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
        adapter = new Myadapter_search(getApplication());
        Intent intent = getIntent() ;
        category=intent.getStringExtra("category");

        category_bar=(TextView)findViewById(R.id.search_bar);
        category_bar.setText(category+" class");
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/category_class.php");
            String result = request.PhPtest_search(String.valueOf(category));
            myJSON =result.trim();
            showList();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        //수정 시작부분
        adapter.setOnItemClickListener(new Myadapter_search.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) throws JSONException {
                Intent intent = new Intent( getApplication(), details.class);
                JSONObject jsonObj = new JSONObject(myJSON);
                classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
                no2=classes.getJSONObject(pos).getString("class_no2");
                no=classes.getJSONObject(pos).getString("class_no");//수정
                intent.putExtra( "classno2",no2);//문자열 넘기기
                intent.putExtra( "classno",no);//문자열 넘기기
                startActivity(intent);
            }


        });//수정 종료부분
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 종료 후 이전페이지가 나타남
            }
        });


    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String id = c.getString("class_name");
                String no = c.getString("class_no");
                String no2 = c.getString("class_no2");
                String image = c.getString("class_image");
                String u_name = c.getString("user_name");
                String time = c.getString("class_time");
                String price = c.getString("class_price");
                String start = c.getString("class_start");


                HashMap<String,String> class_search = new HashMap<String,String>();

                class_search.put("class_name",id);
                class_search.put("class_no",no);
                class_search.put("class_no2",no2);
                class_search.put("class_image",image);
                class_search.put("user_name",u_name);
                class_search.put("class_time",time);
                class_search.put("class_price",price);
                class_search.put("class_start",start);

                adapter.addItem(new Item_search(id,image,no,no2,u_name,time,price,start));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void setActionBar(){
        CustomActionBar_search ca=new CustomActionBar_search(this, getSupportActionBar());
        ca.setActionBar();
    }
}
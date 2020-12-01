package com.wdwy.ftp_connect.ui.dashboard;

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
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

import static android.app.PendingIntent.getActivity;


public class NoticePage extends AppCompatActivity {
    String myJSON;
    JSONArray titles = null;
    private ImageButton  back_btn;
    private String class_no2;
    myDBAdapter dbAdapter;
    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private TextView Notice;

    String userId;
    String notice_title;
    String notice_writer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_page);
        NetworkUtil.setNetworkPolicy();
        getSupportActionBar().hide();

        //이전 페이지에서 값 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");

        Notice = (TextView) findViewById(R.id.Notice);

        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        // 목록 가져와야
        /*
        ArrayList<NoticeTitle> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            list.add(String.format("TEXT %d", i)) ;
        }
        */

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        recyclerView = findViewById(R.id.recycler_title) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 Adapter 객체 지정.
        adapter = new NoticeAdapter(this) ;
        recyclerView.setAdapter(adapter) ;

        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_notice.php");
            String result = request.PhPtest_search(class_no2);
            myJSON =result.trim();
            showList();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }



        //go back to the mypage
        //해당 page 끝내기 -> finish()

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

/*
        notice_adapter = new NoticeAdapter();
        NoticeTitleList.setAdapter(notice_adapter);
        try {
            com.example.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_notice.php");
            String result = request.PhPtest_userId(String.valueOf(userId));
            myJSON = result.trim();
            showList(learningListView, NoticeAdapter, myJSON);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        final ArrayAdapter writer_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        NoticeTitleList.setAdapter(writer_adapter);

    }

    protected void showListL(ListView listView, ArrayAdapter adapter, JSONArray notices, String myJSON){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            notices= jsonObj.getJSONArray("result");

            for(int i=0;i < notices.length();i++){
                JSONObject c =  notices.getJSONObject(i);
                String title = c.getString("notice_title");
                String writer = c.getString("notice_writer");

                adapter.addItem(title, writer);
            }

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            titles= jsonObj.getJSONArray("result");

            for(int i=0;i< titles.length();i++){
                JSONObject c =  titles.getJSONObject(i);
                String title = c.getString("title");
                String writer = c.getString("writer");
                HashMap<String,String> title_ = new HashMap<String,String>();

                title_.put("title",title);
                title_.put("writer",writer);
                adapter.addItem(new NoticeTitle(title, writer));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
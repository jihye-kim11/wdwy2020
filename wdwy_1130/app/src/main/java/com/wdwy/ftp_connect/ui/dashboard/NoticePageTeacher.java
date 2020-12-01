package com.wdwy.ftp_connect.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class NoticePageTeacher extends AppCompatActivity {

    String myJSON;
    JSONArray titles = null;
    private ImageButton back_btn;
    String class_no2;
    myDBAdapter dbAdapter;
    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private TextView Notice;
    private Button write_btn;

    String userId;
    String notice_title;
    String notice_writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_page_teacher);
        NetworkUtil.setNetworkPolicy();
        getSupportActionBar().hide();

        //이전 페이지에서 값 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");

        //Toast.makeText(getApplicationContext(), "class_no2 : "+class_no2, 0).show();

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

        write_btn = (Button)findViewById(R.id.button3);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NoticeWrite activity 호출
                Intent intent = new Intent(getApplicationContext(), NoticeWrite.class);
                intent.putExtra("class_no2", class_no2);
                startActivity(intent);
            }
        });
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
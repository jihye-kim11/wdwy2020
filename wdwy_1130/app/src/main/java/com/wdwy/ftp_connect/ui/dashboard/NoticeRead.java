package com.wdwy.ftp_connect.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import java.net.MalformedURLException;

public class NoticeRead extends AppCompatActivity {
    private TextView view_title_, view_content_;
    private ImageButton  back_btn;
    myDBAdapter dbAdapter;
    String userId, class_no2, title;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_read);
        NetworkUtil.setNetworkPolicy();
        getSupportActionBar().hide();

        //id 받아오기
        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        // class 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");
        title = intent.getStringExtra("title");

        view_title_ = (TextView) findViewById(R.id.view_title);
        view_title_.setText(""+title);

        view_content_ = (TextView) findViewById(R.id.view_content);
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_notice_content.php");
            String result = request.PhPtest_send2(String.valueOf(class_no2), String.valueOf(title));
            view_content_.setText(""+result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
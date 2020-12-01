package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

public class SomoimSet extends AppCompatActivity {
    private String class_no2;
    private String userId;
    myDBAdapter dbAdapter;

    private ImageButton backButton;
    private ImageView week_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somoim_set);
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


    }
}
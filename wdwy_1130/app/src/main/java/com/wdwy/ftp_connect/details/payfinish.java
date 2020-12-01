package com.wdwy.ftp_connect.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.details.pay;

public class payfinish extends AppCompatActivity {
    public Button pay_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payfinish);

        pay_btn=(Button)findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {//인텐트로 pay 액티비티에 classno2 넘기기
                finish();
            }
        });


    }
}
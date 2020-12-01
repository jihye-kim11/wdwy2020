package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;

import java.net.MalformedURLException;

public class DeveloperPage extends AppCompatActivity {
    EditText title, email, content;
    Button sendBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_qa_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String getId = intent.getExtras().getString("userId");

        title = (EditText)findViewById(R.id.data_title);
        email = (EditText)findViewById(R.id.data_email);
        content = (EditText)findViewById(R.id.data_content);
        sendBtn = (Button)findViewById(R.id.sendButton);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터 송신
                try {
                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/send_to_developer.php");
                    String result = request.PhPtest_sendToDeveloper(String.valueOf(getId), String.valueOf(title.getText()), String.valueOf(email.getText()), String.valueOf(content.getText()));

                    if (result.equals("1")) {
                        Toast.makeText(getApplication(), "전송 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "전송 실패", Toast.LENGTH_SHORT).show();
                    }

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }

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

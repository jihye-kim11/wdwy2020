package com.wdwy.ftp_connect.ui.dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import java.net.MalformedURLException;

public class NoticeWrite extends AppCompatActivity {
    private ImageButton back_btn;
    private Button update_btn;
    myDBAdapter dbAdapter;
    String userId, class_no2, notice_title, user_name, notice_contents;
    private EditText title_btn, content_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);
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

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_name.php");
            user_name = request.PhPtest_search(userId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };

        title_btn = (EditText) findViewById(R.id.write_title);
        content_btn = (EditText) findViewById(R.id.write_content);

        update_btn = (Button) findViewById(R.id.update_button);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(NoticeWrite.this);
                dlg.setTitle("공지가 등록되었습니다.");
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notice_title = title_btn.getText().toString();
                                notice_contents = content_btn.getText().toString();
                                try {
                                    //db 연동
                                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/add_notice.php");
                                    String result = request.PhPtest_updatenotice(class_no2, notice_title, userId, notice_contents, user_name);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                finish();
                            }
                        });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }

}
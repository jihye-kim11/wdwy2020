package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import java.net.MalformedURLException;


public class StudentClass extends AppCompatActivity {
    private ImageButton MeButton, LectureLinkButton, NoticeButton, SomoimButton, ChatButton;
    private TextView ClassName;
    public ImageButton back_btn;
    String userId;
    String class_no2;
    myDBAdapter dbAdapter;

    public StudentClass() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);
        NetworkUtil.setNetworkPolicy();


        //id 받아오기
        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        //class 받아오기
        Intent intent = getIntent();
        class_no2 = intent.getStringExtra("class_no2");


        ClassName = (TextView) findViewById(R.id.ClassName);
        ClassName.setTextSize(30);
        //ClassName.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        try {
            //db 연동 필요
            //값 받아오기 필요
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_class_name.php");
            String result = request.PhPtest_search(String.valueOf(class_no2));;
            ClassName.setText(""+result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };

        //go back to the mypage
        //해당 page 끝내기 -> finish()
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //go to My profile
        //need to add profile class
        /*
        MeButton = (ImageButton)findViewById(R.id.MeButton);
        MeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to My profile
                Intent intent = new Intent(getApplicationContext(), UserInfoPage.class);
                startActivity(intent);
            }
        });
*/
        //버튼 클릭시 강의 링크 바로가기
        LectureLinkButton = (ImageButton)findViewById(R.id.LectureLinkButton);
        LectureLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //강의 웹 페이지 열기
                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_class_link.php");
                    String result = request.PhPtest_search(class_no2);
                    Intent lectureIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                    startActivity(lectureIntent);
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

/*
{
               //강의 링크 복사
                    AlertDialog.Builder dlg = new AlertDialog.Builder(StudentClass.this);
                dlg.setMessage("링크가 복사되었습니다.");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                            //링크 네이버 -> 강의 링크로 수정
                        }
 */

        //공지사항 게시판으로 가기
        NoticeButton = (ImageButton)findViewById(R.id.NoticeButton);
        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NoticePage activity 호출
                Intent intent = new Intent(getApplication(), NoticePage.class);
                intent.putExtra("class_no2", class_no2);
                startActivity(intent);
            }
        });

        //소모임 페이지로 가기
        SomoimButton = (ImageButton)findViewById(R.id.SomoimButton);
        SomoimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SomoimPage activity 호출
                Intent intent = new Intent(getApplicationContext(), SomoimPage.class);
                intent.putExtra("class_no2", class_no2);
                startActivity(intent);
            }
        });

        //채팅 화면으로 가기
        ChatButton = (ImageButton)findViewById(R.id.ChatButton);
        ChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ChatPage activity 호출
                Intent intent = new Intent(getApplicationContext(), ChatPage.class);
                intent.putExtra("class_no2", class_no2);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();
    }

}
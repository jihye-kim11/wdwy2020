package com.wdwy.ftp_connect.ui.dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import java.net.MalformedURLException;

//추가해야할 것 : 이전 my page에서 class 정보 받아오기 -> 클래스명, 링크 등등
public class TeacherClass extends AppCompatActivity {
    private ImageButton  back_btn, LectureLinkButton, NoticeButton, ChatButton;
    private Button addLectureLinkButton;
    private TextView ClassName, message;
    private View addLinkView;
    private EditText editlink;
    private String LectureLink;
    //String myJSON;

    String userId;
    String class_no2;
    myDBAdapter dbAdapter;

    public TeacherClass() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class);
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

        ClassName = (TextView) findViewById(R.id.ClassName);
        ClassName.setTextSize(30);
        //ClassName.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_class_name.php");
            String result = request.PhPtest_search(String.valueOf(class_no2));
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
                //파일 합칠 시에 MyProfile class 이름 변경 필요
                Intent intent = new Intent(getApplicationContext(), UserInfoPage.class);
                startActivity(intent);
            }
        });
*/
        //add lecture link
        //editLink에 입력 받은 링크를 db에 추가해야함 -> 코드 수정 필요
        addLectureLinkButton = (Button)findViewById(R.id.addLectureLinkButton);
        addLectureLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //강의 링크 추가할 대화상자 열기
                addLinkView = (View) View.inflate(TeacherClass.this, R.layout.add_lecture_link, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(TeacherClass.this);
                dlg.setTitle("강의 링크");
                dlg.setView(addLinkView);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editlink = (EditText)addLinkView.findViewById(R.id.editLink);
                                LectureLink = editlink.getText().toString();
                                try {
                                    //db 연동
                                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/input_class_link.php");
                                    String result = request.PhPtest_send2(LectureLink, class_no2);
                                    //LectureLink를 db로 보내는 코드 필요
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                //링크 text 화면에 띄우기
                                //message.setText(editlink.getText().toString());
                            }
                        });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

        //버튼 클릭시 강의 링크 바로가기
        LectureLinkButton = (ImageButton)findViewById(R.id.LectureLinkButton);
        LectureLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //강의 웹 페이지 열기
                    //값 받아오기 필요

                    com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_class_link.php");
                    String result = request.PhPtest_search(String.valueOf(class_no2));

                    Intent lectureIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
                    startActivity(lectureIntent);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                };
            }
        });

/*
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
                Intent intent = new Intent(getApplicationContext(), NoticePageTeacher.class);
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
        //setActionBar();
    }

}
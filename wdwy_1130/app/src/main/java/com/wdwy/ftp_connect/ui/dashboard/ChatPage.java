package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.wdwy.ftp_connect.PHPRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.wdwy.ftp_connect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatPage extends AppCompatActivity {
    EditText et;
    ListView listView;
    String myJSON;
    URL url = null;
    Bitmap bitmap;
    String getId, class_no2, className;

    String getName, getImage = "no";
    JSONArray tempName;

    ArrayList<MessageItem> messageItems=new ArrayList<>();
    ChatAdapter adapter;

    //Firebase Database 관리 객체참조변수
    FirebaseDatabase firebaseDatabase;

    //'chat'노드의 참조객체 참조변수
    DatabaseReference chatRef;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatpage);
        sendButton = (Button) findViewById(R.id.sendBtn);


        et=findViewById(R.id.et);
        listView=findViewById(R.id.listview);
        adapter=new ChatAdapter(messageItems,getLayoutInflater());
        listView.setAdapter(adapter);


        Intent intent = getIntent();
        getId = intent.getExtras().getString("userId");
        class_no2 = intent.getExtras().getString("class_no2");


        //Firebase DB관리 객체와 'caht'노드 참조객체 얻어오기
        firebaseDatabase= FirebaseDatabase.getInstance();
        chatRef= firebaseDatabase.getReference(class_no2);

        //ClassName.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        try {
            //db 연동 필요
            //값 받아오기 필요
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_class_name.php");
            String result = request.PhPtest_search(String.valueOf(class_no2));;
            className = result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle(className);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // id로 이름 받아오기
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_info.php");
            String result = request.PhPtest_userId(String.valueOf(getId));
            myJSON =result.trim();
            getInfo();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        if (!getImage.equals("no")) {
            try {
                url = new URL(getImage);

                URLConnection conn = url.openConnection();
                conn.connect();

                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //firebaseDB에서 채팅 메세지들 실시간 읽어오기..
        //'chat'노드에 저장되어 있는 데이터들을 읽어오기
        //chatRef에 데이터가 변경되는 것으 듣는 리스너 추가
        chatRef.addChildEventListener(new ChildEventListener() {
            //새로 추가된 것만 줌 ValueListener는 하나의 값만 바뀌어도 처음부터 다시 값을 줌
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //새로 추가된 데이터(값 : MessageItem객체) 가져오기
                MessageItem messageItem= dataSnapshot.getValue(MessageItem.class);

                //새로운 메세지를 리스뷰에 추가하기 위해 ArrayList에 추가
                messageItems.add(messageItem);

                //리스트뷰를 갱신
                adapter.notifyDataSetChanged();
                listView.setSelection(messageItems.size()-1); //리스트뷰의 마지막 위치로 스크롤 위치 이동
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void clickSend(View view) {

        //firebase DB에 저장할 값들( 닉네임, 메세지, 프로필 이미지URL, 시간)
        String nickName= getName;
        String message= et.getText().toString();
        String pofileUrl= getImage;
        String id = getId;

        //메세지 작성 시간 문자열로..
        Calendar calendar= Calendar.getInstance(); //현재 시간을 가지고 있는 객체
        String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE); //14:16
        //날짜
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String date= year + "년 " + month + "월 " + day + "일";


        //firebase DB에 저장할 값(MessageItem객체) 설정
        MessageItem messageItem= new MessageItem(nickName,message,time,pofileUrl, id, date);
        //'char'노드에 MessageItem객체를 통해
        chatRef.push().setValue(messageItem);

        //EditText에 있는 글씨 지우기
        et.setText("");

        //소프트키패드를 안보이도록..
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        //처음 시작할때 EditText가 다른 뷰들보다 우선시 되어 포커스를 받아 버림.
        //즉, 시작부터 소프트 키패드가 올라와 있음.

        //그게 싫으면...다른 뷰가 포커스를 가지도록
        //즉, EditText를 감싼 Layout에게 포커스를 가지도록 속성을 추가!![[XML에]
    }

    protected void getInfo(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tempName = jsonObj.getJSONArray("result");

            for(int i=0;i < tempName.length();i++){
                JSONObject c =  tempName.getJSONObject(i);
                String name = c.getString("name");
//                 이미지는 추후 수정
                String image;
                if (!c.getString("image").equals(null)) {
                    image = c.getString("image");
                    getImage = image;
                }
                getName = name;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

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
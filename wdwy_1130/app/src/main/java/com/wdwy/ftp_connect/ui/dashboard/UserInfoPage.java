package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class UserInfoPage extends AppCompatActivity {
    ImageView image;
    String myJSON;
    Button modifyBtn;
    TextView id, name, password, password_2, ph;
    String getName, getImage = "no";
    JSONArray tempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_page);

        image = (ImageView)findViewById(R.id.image);
//        modifyBtn = (Button)findViewById(R.id.modifyButton);
        id = (TextView)findViewById(R.id.data_id);
        name = (TextView)findViewById(R.id.data_name);
//        password = (EditText)findViewById(R.id.data_password);
//        password_2 = (EditText)findViewById(R.id.data_password_2);
//        ph = (EditText)findViewById(R.id.data_phone);

        // 데이터 수신
        Intent intent = getIntent();
        String getId = intent.getExtras().getString("userId");
        // id로 이름 받아오기
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_info.php");
            String result = request.PhPtest_userId(String.valueOf(getId));
            myJSON =result.trim();
            getInfo();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        // id, 이름 고정값
        id.setText(getId);
        name.setText(getName);
        if (!getImage.equals("no")) {
            try {
                URL url = new URL(getImage);

                URLConnection conn = url.openConnection();
                conn.connect();

                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                image.setImageBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);



//        modifyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 데이터 송신
//                try {
//                    if (String.valueOf(password.getText()).equals(String.valueOf(password_2.getText()))) {
//                        com.example.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/modify_user_info.php");
//                        String result = request.PhPtest_modifyUserInfo(String.valueOf(getId), String.valueOf(password.getText()), String.valueOf(password_2.getText()), String.valueOf(ph.getText()));
//
//                        if (result.equals("1")) {
//                            Toast.makeText(getApplication(), "성공", Toast.LENGTH_SHORT).show();
//                            finish();
//                        } else {
//                            Toast.makeText(getApplication(), "실패", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else {
//                        Toast.makeText(getApplication(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
//                    }
//
//                }catch (MalformedURLException e){
//                    e.printStackTrace();
//                }
//            }
//        });
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

package com.wdwy.ftp_connect;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2;
    private EditText data1, data2, data3,data4;
    private TextView textView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        NetworkUtil.setNetworkPolicy();

        getHashKey();
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        data1 = (EditText)findViewById(R.id.data1);
        data2 = (EditText)findViewById(R.id.data2);
        data3 = (EditText)findViewById(R.id.data3);
        data4 = (EditText)findViewById(R.id.data4);
        textView9=(TextView)findViewById(R.id.textView9);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/insertdata.php");
                    String result = request.PhPtest2(String.valueOf(data1.getText()),String.valueOf(data2.getText()),String.valueOf(data3.getText()),String.valueOf(data4.getText()));
                    if(result.equals("1")){
                        Toast.makeText(getApplication(),"성공",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplication(),"실패", Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/pay.php");
                    String result = request.PhPtest2("1", "2","", "acdfvs");//유저 아이디 우선 yumi로 임의값 넣어놓음.

//<br>을 \n으로 변경 필요

                    textView9.setText(result);

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}
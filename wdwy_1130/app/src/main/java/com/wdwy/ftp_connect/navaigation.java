package com.wdwy.ftp_connect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wdwy.ftp_connect.ui.home.myDBAdapter;
import com.wdwy.ftp_connect.ui.home.search.CustomActionBar_search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navaigation extends AppCompatActivity {
    String userId;
    String strId;
    //String user_id;
    //Profile userProfile;
    String nickname;
    String userImage;
    Button log_button;

    //sqlite
    myDBAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navaigation);
        NetworkUtil.setNetworkPolicy();

        //login 정보 받아오기
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        nickname = intent.getStringExtra("nickname");
        userImage = intent.getStringExtra("userImage");

        //id string으로 변환
        //String user_id = String.valueOf(userId);
        //System.out.println(userId);
        //System.out.println(nickname);
        //System.out.println(userImage);

        dbAdapter = new myDBAdapter(this);
        dbAdapter.open();

        strId = dbAdapter.idOpen();
        dbAdapter.close();

        //id 확인
//        log_button = (Button) findViewById(R.id.logbutton);
//        log_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast log_mess = Toast.makeText(navaigation.this, ""+strId, Toast.LENGTH_SHORT);
//                log_mess.show();
//            }
//        });

        //받아온 정보 보내기 필요함.

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
       // setActionBar();
    }
    private void setActionBar(){
        CustomActionBar_search ca=new CustomActionBar_search(this, getSupportActionBar());
        ca.setActionBar();
    }
}
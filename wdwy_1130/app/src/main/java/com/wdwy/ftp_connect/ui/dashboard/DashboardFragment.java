package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.navaigation;
import com.wdwy.ftp_connect.ui.dashboard.qa_answer.qa_answer;
import com.wdwy.ftp_connect.ui.dashboard.review.review;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    myDBAdapter dbAdapter;
    String myJSON;
    Button userinfoBtn, settingBtn, reviewBtn, qaBtn;
    TextView text;
    private ListView learningListView, teachingListView;
    private ListViewAdapterL learningAdapter;
    private ListViewAdapterT teachingAdapter;
    JSONArray learnClasses = null, teachClasses = null, tempPW = null;
    // 임시 id
    String userId;
    //String userId = "1524618196";
    // 강유경님 아이디 : "1524618196"
    // 내꺼 : "1526328517"
    String userPw = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        NetworkUtil.setNetworkPolicy();

        // sqlite에서 id 받아오기
        Context context_ = getActivity();
        dbAdapter = new myDBAdapter(context_);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        G.id = userId;

        ActionBar actionBar = ((navaigation) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // sqlite 에서 id받아와야함. 현재 터짐
//        sqlDB = myHelper.getReadableDatabase();
//        Cursor cusor;
//        cusor = sqlDB.rawQuery("SELECT gName FROM groupTBL;", null);
//        String strId = "";
//        while (cusor.moveToNext()) {
//            strId += cusor.getString(0) + "\r\n";
//        }
//        System.out.println(strId);
//        cusor.close();


        // 변수 지정
        userinfoBtn = (Button)root.findViewById(R.id.userInfoButton);
        settingBtn = (Button)root.findViewById(R.id.settingButton);
        reviewBtn = (Button)root.findViewById(R.id.reviewButton);
        qaBtn = (Button)root.findViewById(R.id.qaButton);
        text = (TextView)root.findViewById(R.id.textView8);

        // 수강 강의 목록 불러오기
        learningAdapter = new ListViewAdapterL();
        learningListView = (ListView) root.findViewById(R.id.learn_class);
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/learn_class.php");
            String result = request.PhPtest_userId(String.valueOf(userId));
            myJSON = result.trim();
            showListL(learningListView, learningAdapter, learnClasses, myJSON);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        // 강의 목록 불러오기
        teachingAdapter = new ListViewAdapterT();
        teachingListView = (ListView) root.findViewById(R.id.teach_class);
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/teach_class.php");
            String result = request.PhPtest_userId(String.valueOf(userId));
            myJSON = result.trim();
            showListT(teachingListView, teachingAdapter, teachClasses, myJSON);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        Context context = getActivity(); // fragment 내 이동
        // 클릭이벤트 지정
        userinfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터 송신
                Intent intent = new Intent(context, UserInfoPage.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //settingPage 만들기
                Intent intent = new Intent(context, SettingPage.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reviewPage 만들기
            Intent intent = new Intent(context, review.class);
             startActivity(intent);
            }
        });
        qaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //qaPage 만들기
             Intent intent = new Intent(context, qa_answer.class);
              startActivity(intent);
            }
        });

        return root;
    }

    protected void showListL(ListView listView, ListViewAdapterL adapter, JSONArray classes, String myJSON){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i < classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("class_name");
                String time = c.getString("class_time");
                String no2 = c.getString("class_no2");

                adapter.addItem(time, R.drawable.book, name, no2);
            }

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void showListT(ListView listView, ListViewAdapterT adapter, JSONArray classes, String myJSON){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i < classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("class_name");
                String time = c.getString("class_time");
                String no2 = c.getString("class_no2");

                adapter.addItem(time, R.drawable.book, name, no2);
            }

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void getPassword(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tempPW = jsonObj.getJSONArray("result");
            String pw_ = "";
            for(int i=0;i < tempPW.length();i++){
                JSONObject c =  tempPW.getJSONObject(i);
                pw_ = c.getString("password");
                userPw = pw_;
            }

            System.out.println(pw_);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("11111111");
        }

    }

}
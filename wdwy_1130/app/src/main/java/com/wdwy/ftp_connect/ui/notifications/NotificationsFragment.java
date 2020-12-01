package com.wdwy.ftp_connect.ui.notifications;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.navaigation;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    myDBAdapter dbAdapter;
    String myJSON;
    private ListView alarmListView;
    private ListViewAlarmAdapter alarmAdapter;
    JSONArray alarm_list = null;
    String userId;
    JSONArray tempAlarm;
    int getAlarm;
    TextView text;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        ActionBar actionBar = ((navaigation) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // sqlite 에서 id 수신
        Context context_ = getActivity();
        dbAdapter = new myDBAdapter(context_);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();

        text = (TextView) root.findViewById(R.id.textAlarm);

        // 알람 정보 받아오기
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_user_info.php");
            String result = request.PhPtest_userId(String.valueOf(userId));
            myJSON =result.trim();
            getAlarm_();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        if (getAlarm == 1) {
            text.setText("");
            // 알림 목록 불러오기
            alarmAdapter = new ListViewAlarmAdapter();
            alarmListView = (ListView) root.findViewById(R.id.alarm_list);
            try {
                com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/get_alarm_list.php");
                String result = request.PhPtest_userId(String.valueOf(userId));
                myJSON = result.trim();
                showList(alarmListView, alarmAdapter, alarm_list, myJSON);

            }catch (MalformedURLException e){
                e.printStackTrace();
            }
        }
        else if (getAlarm == 0) {
            text.setText("알림이 꺼져있습니다.\n설정페이지를 확인해주세요 !");
        }



        return root;
    }
    // 목록 불러오기
    protected void showList(ListView listView, ListViewAlarmAdapter adapter, JSONArray classes, String myJSON){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i < classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("class_name");
                String time = c.getString("class_time");
                String no2 = c.getString("class_no2");

                adapter.addItem(name, time, no2);
            }

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void getAlarm_(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            tempAlarm = jsonObj.getJSONArray("result");

            for(int i=0;i < tempAlarm.length();i++){
                JSONObject c =  tempAlarm.getJSONObject(i);
                int al = c.getInt("alarm");

                getAlarm = al;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
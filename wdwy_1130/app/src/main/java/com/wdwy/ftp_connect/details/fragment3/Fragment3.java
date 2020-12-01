package com.wdwy.ftp_connect.details.fragment3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class Fragment3 extends Fragment {
    String myJSON,no;
    JSONArray classes = null;
    private RecyclerView recyclerView;
    private EditText editText;
    private MyAdapter_fragment3 adapter;
    private Button send;
    myDBAdapter dbAdapter;
    String userId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        NetworkUtil.setNetworkPolicy();
        Context context = getActivity();//Fragment에서 context 쓰는법
        recyclerView = rootView.findViewById(R.id.fragment3_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false); //레이아웃매니저 생성
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter_fragment3(context);
        if(getArguments() != null){
            no = getArguments().getString("key"); // 전달한 key 값
        }
        Context context_ = getActivity();
        dbAdapter = new myDBAdapter(context_);
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/qa.php");
            String result = request.PhPtest_search(no);
            myJSON =result.trim();
            showList();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        send=(Button)rootView.findViewById(R.id.send);
        editText = (EditText)rootView.findViewById(R.id.editText);
        adapter.setOnItemClickListener(new MyAdapter_fragment3.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) throws JSONException {
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(editText.getText()).length()>=10) {
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/qasend.php");
                        String result = request.PhPtest2(String.valueOf(editText.getText()), "", no, userId);//유저 아이디 우선 yumi로 임의값 넣어놓음.
                        if (result.equals("1")) {
                            Toast.makeText(context, "질문이 정상적으로 입력되었습니다", Toast.LENGTH_SHORT).show();
                            editText.setText(" ");
                            adapter.clearItem();
                            try {
                                PHPRequest request1 = new PHPRequest("http://hyper0616.dothome.co.kr/qa.php");
                                String result1 = request1.PhPtest_search(no);
                                myJSON = result1.trim();
                                showList();
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "error: 다시 전송해주세요", Toast.LENGTH_SHORT).show();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                else{Toast.makeText(context,"최소 10글자 이상 작성 뒤 전송해주세요", Toast.LENGTH_SHORT).show();

                }

            }
        });
        return rootView;

    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("user_id");
                String b = c.getString("board_contents");
                String c_ = c.getString("comment_contents");
                String image = c.getString("user_image");
                HashMap<String,String> class_ = new HashMap<String,String>();

                class_.put("user_id",name);
                class_.put("board_contents",b);
                class_.put("comment_contents",c_);
                class_.put("user_image",image);
                adapter.addItem(new Item_fragment3(name,b,c_,image));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    protected void showList2(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i<1;i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("user_id");
                String b = c.getString("board_contents");
                String c_ = c.getString("comment_contents");
                String image = c.getString("user_image");
                HashMap<String,String> class_ = new HashMap<String,String>();

                class_.put("user_id",name);
                class_.put("board_contents",b);
                class_.put("comment_contents",c_);
                class_.put("user_image",image);
                adapter.addItem(new Item_fragment3(name,b,c_,image));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
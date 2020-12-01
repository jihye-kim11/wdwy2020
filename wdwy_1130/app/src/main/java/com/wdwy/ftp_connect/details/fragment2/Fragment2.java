package com.wdwy.ftp_connect.details.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class Fragment2 extends Fragment {
    String myJSON,no;
    JSONArray classes = null;
    private RecyclerView recyclerView;
    private MyAdapter_fragment2 adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);
        NetworkUtil.setNetworkPolicy();
        Context context = getActivity();//Fragment에서 context 쓰는법
        recyclerView = rootView.findViewById(R.id.fragment2_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false); //레이아웃매니저 생성
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter_fragment2(context);
        adapter.setOnItemClickListener(new MyAdapter_fragment2.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) throws JSONException {
            }
        });

        if(getArguments() != null){
            no = getArguments().getString("key"); // 전달한 key 값

             }

        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/review.php");
            String result = request.PhPtest_search(no);
            myJSON =result.trim();
            showList();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
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

                adapter.addItem(new Item_fragment2(name,b,c_,image));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
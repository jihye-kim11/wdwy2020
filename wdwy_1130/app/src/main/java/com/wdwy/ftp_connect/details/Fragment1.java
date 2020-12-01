package com.wdwy.ftp_connect.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class Fragment1 extends Fragment {
    public ImageView image;
    String myJSON,search,image2;
    JSONArray classes = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
         image=(ImageView) rootView.findViewById(R.id.imageView);
        Context context = getActivity();//Fragment에서 context 쓰는법
        if(getArguments() != null){
            search = getArguments().getString("key"); // 전달한 key 값

        }
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/classdata2.php");
            String result = request.PhPtest_search(String.valueOf(search));
            myJSON =result.trim();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
            image2=classes.getJSONObject(0).getString("info_image1");
            Glide.with(context).load(image2)
                    .into(image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}
package com.wdwy.ftp_connect.ui.home;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.details.details;
import com.wdwy.ftp_connect.navaigation;
import com.wdwy.ftp_connect.ui.home.search.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    String myJSON;
    JSONArray classes = null;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private GridLayoutManager G_layoutManager;
    public Button activity_btn,programmimg_btn,study_btn,photo_btn,art_btn,music_btn,making_btn;
    public ImageButton banner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        NetworkUtil.setNetworkPolicy();

        Context context = getActivity();//Fragment에서 context 쓰는법

        recyclerView = root.findViewById(R.id.main_listview);
        G_layoutManager = new GridLayoutManager(context, 2); //레이아웃매니저 생성
        recyclerView.setLayoutManager(G_layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
        adapter = new MyAdapter(context);
        try {
            com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/update_class.php");
           String result = request.PhPtest();
            myJSON =result.trim();
            showList();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        ActionBar actionBar = ((navaigation) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C59CD6")));//FFFFFF
        actionBar.setIcon(R.drawable.logo);
        actionBar.setTitle("");
       actionBar.setDisplayShowHomeEnabled(true);
       actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.cap);//돋보기 버튼


       setHasOptionsMenu(true);

        activity_btn=(Button) root.findViewById(R.id.activity_btn);
        activity_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="액티비티";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        programmimg_btn=(Button) root.findViewById(R.id.programmimg_btn);
        programmimg_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="프로그래밍";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        study_btn=(Button) root.findViewById(R.id.study_btn);
        study_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="어학";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        photo_btn=(Button) root.findViewById(R.id.photo_btn);
        photo_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="사진/동영상";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        art_btn=(Button) root.findViewById(R.id.art_btn);
        art_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="미술";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        music_btn=(Button) root.findViewById(R.id.music_btn);
        music_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="음악";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        making_btn =(Button) root.findViewById(R.id.making_btn );
        making_btn .setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, category.class);
                String c="수공예";
                intent.putExtra("category",c);//문자열 넘기기
                startActivity(intent);
            }
        });
        banner=(ImageButton) root.findViewById(R.id.banner);
        banner.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, com.wdwy.ftp_connect.ui.home.banner.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) throws JSONException {
                Intent intent = new Intent(context, details.class);
                JSONObject jsonObj = new JSONObject(myJSON);
                classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
                String no2 = classes.getJSONObject(pos).getString("class_no2");
                String no = classes.getJSONObject(pos).getString("class_no");
                intent.putExtra( "classno2",no2);//문자열 넘기기
                intent.putExtra( "classno",no);//문자열 넘기기
                startActivity(intent);
            }


        });
        return root;
    }
    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();

        }

    };
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("class_name");
                String no = c.getString("class_no");
                String no2 = c.getString("class_no2");
                String image = c.getString("class_image");
                String time = c.getString("class_time");

                HashMap<String,String> class_main = new HashMap<String,String>();

                class_main.put("class_name",name);
                class_main.put("class_no",no);
                class_main.put("class_no2",no2);
                class_main.put("class_image",image);
                class_main.put("class_time",time);

                adapter.addItem(new Item(name,image,no,no2,time));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    { // res/menu 에서 친구 탭에서 작동 할 menu를 가져온다.
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// 검색 버튼 클릭했을 때 searchview 길이 꽉차게
       SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색어를 입력하세요");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 입력받은 문자열 처리
                Intent intent = new Intent(getActivity(), search.class);
                intent.putExtra("search",s);//문자열 넘기기
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리
                return false;
            }
        });


        searchView.setSubmitButtonEnabled(true);
        super.onCreateOptionsMenu(menu, inflater);
    }


//아이템 선택시 이벤트 리스너(우선 사용x)
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

}
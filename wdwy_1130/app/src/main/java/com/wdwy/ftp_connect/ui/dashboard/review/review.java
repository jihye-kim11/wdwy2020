package com.wdwy.ftp_connect.ui.dashboard.review;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class review extends AppCompatActivity {
    String myJSON;
    JSONArray classes = null;
    public ImageButton back_btn;
    private RecyclerView recyclerView;
    private Myadapter_review adapter;
    private TextView class_name1;
    private EditText editText,editText2;
    private Button send;
    public String classname,no_;
    myDBAdapter dbAdapter;
    String userId ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        setActionBar();
        NetworkUtil.setNetworkPolicy();
        recyclerView = findViewById(R.id.review_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false); //레이아웃매니저 생성
        recyclerView.setLayoutManager(layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
        adapter = new Myadapter_review(getApplication());

        //회원 아이디 받아와야하나 우선은 값 설정.
        dbAdapter = new myDBAdapter(getApplicationContext());
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();
        class_name1=(TextView)findViewById(R.id.class_name1);
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/review_list.php");//수정
            String result = request.PhPtest_search(String.valueOf(userId));
            myJSON =result.trim();
            showList();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 종료 후 이전페이지가 나타남
            }
        });
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        adapter.setOnItemClickListener(new Myadapter_review.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) throws JSONException {
                JSONObject jsonObj = new JSONObject(myJSON);
                classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
                classname=classes.getJSONObject(pos).getString("class_name");
                no_=classes.getJSONObject(pos).getString("class_no");
                //Toast.makeText(getApplication(), no+"번째 질문 선택", Toast.LENGTH_SHORT).show();
                class_name1.setText(classname);
            }
        });
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(class_name1.getText()).length()>=1) {
                    if(String.valueOf(editText2.getText()).length()>=3) {
                    if(String.valueOf(editText.getText()).length()>=10) {
                        try {
                            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/review_send.php");
                            String result = request.PhPtest_send5("0",no_,userId,String.valueOf(editText2.getText()),String.valueOf(editText.getText()));//no_은 sugang_no
                            if (result.equals("1")) {
                                Toast.makeText(getApplication(), "후기가 정상적으로 입력되었습니다", Toast.LENGTH_SHORT).show();
                                editText.setText(" ");
                                editText2.setText(" ");
                                adapter.clearItem();
                                try {
                                    PHPRequest request1 = new PHPRequest("http://hyper0616.dothome.co.kr/review_list.php");
                                    String result1 = request1.PhPtest_search(userId);
                                    myJSON = result1.trim();
                                    showList();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplication(), "error: 다시 전송해주세요", Toast.LENGTH_SHORT).show();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    else{Toast.makeText(getApplication(),"최소 내용을 10글자 이상 작성 뒤 전송해주세요", Toast.LENGTH_SHORT).show();
                    }
                    }
                    else{Toast.makeText(getApplication(),"최소 제목을 3글자 이상 작성 뒤 전송해주세요", Toast.LENGTH_SHORT).show();
                    }
                }else{Toast.makeText(getApplication(),"강의를 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setActionBar(){
        CustomActionBar_review ca=new CustomActionBar_review(this, getSupportActionBar());
        ca.setActionBar();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String name = c.getString("class_name");
                String no = c.getString("class_no");
                String image = c.getString("class_image");



                HashMap<String,String> class_search = new HashMap<String,String>();

                class_search.put("class_name",name);
                class_search.put("class_no",no);
                class_search.put("class_image",image);


                adapter.addItem(new Item_review(name,image,no));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
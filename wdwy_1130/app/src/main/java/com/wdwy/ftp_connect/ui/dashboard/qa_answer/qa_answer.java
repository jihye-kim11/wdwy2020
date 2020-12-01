package com.wdwy.ftp_connect.ui.dashboard.qa_answer;

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

public class qa_answer extends AppCompatActivity {
    public ImageButton back_btn;
    private RecyclerView recyclerView;
    private Myadapter_qa_answer adapter;

    private TextView qa1, class_name1;
    String myJSON;
    JSONArray classes = null;
    public String no,classname,qa;//수정
    private EditText editText;
    private Button send;
    myDBAdapter dbAdapter;
    String userId ="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_answer);
        setActionBar();
        NetworkUtil.setNetworkPolicy();
        recyclerView = findViewById(R.id.qa_answer_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false); //레이아웃매니저 생성
        recyclerView.setLayoutManager(layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
        adapter = new Myadapter_qa_answer(getApplication());

        //회원 아이디 받아와야하나 우선은 값 설정.

        dbAdapter = new myDBAdapter(getApplicationContext());
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();
        class_name1=(TextView)findViewById(R.id.class_name1);
        qa1=(TextView)findViewById(R.id.qa1);
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/qa_answer.php");//수정
            String result = request.PhPtest_search(String.valueOf( userId));
            myJSON =result.trim();
            showList();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        editText = (EditText)findViewById(R.id.editText);
        adapter.setOnItemClickListener(new Myadapter_qa_answer.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int pos) throws JSONException {
                JSONObject jsonObj = new JSONObject(myJSON);
                classes= jsonObj.getJSONArray("result");//class_no2,class_no 넘기기
                no=classes.getJSONObject(pos).getString("board_no");
                classname=classes.getJSONObject(pos).getString("class_name");
                qa=classes.getJSONObject(pos).getString("class_qa");
                //Toast.makeText(getApplication(), no+"번째 질문 선택", Toast.LENGTH_SHORT).show();
                class_name1.setText(classname);
                qa1.setText(qa);


            }


        });
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(qa1.getText()).length()>=1) {
                if(String.valueOf(editText.getText()).length()>=10) {
                    try {
                        PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/qa_answer_send.php");
                        String result = request.PhPtest_send2(no,String.valueOf(editText.getText()));//답변,board_no
                        //Toast.makeText(getApplication(), no+"번째 질문 답변:"+String.valueOf(editText.getText()), Toast.LENGTH_SHORT).show();
                        if (result.equals("1")) {
                            Toast.makeText(getApplication(), "답변이 정상적으로 입력되었습니다", Toast.LENGTH_SHORT).show();
                            editText.setText(" ");
                            adapter.clearItem();
                            try {
                                PHPRequest request1 = new PHPRequest("http://hyper0616.dothome.co.kr/qa_answer.php");
                                String result1 = request1.PhPtest_search( userId);
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
                else{Toast.makeText(getApplication(),"최소 10글자 이상 작성 뒤 전송해주세요", Toast.LENGTH_SHORT).show();
                }}else{Toast.makeText(getApplication(),"질문을 선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 종료 후 이전페이지가 나타남
            }
        });


    }
    private void setActionBar(){
        CustomActionBar_qa_answer ca=new CustomActionBar_qa_answer(this, getSupportActionBar());
        ca.setActionBar();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray("result");

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String id = c.getString("class_name");
                String no = c.getString("board_no");
                String qa = c.getString("class_qa");
                String image = c.getString("class_image");
                String u_name = c.getString("user_name");



                HashMap<String,String> class_search = new HashMap<String,String>();

                class_search.put("class_name",id);
                class_search.put("class_no",no);
                class_search.put("class_qa",qa);
                class_search.put("class_image",image);
                class_search.put("user_name",u_name);


                adapter.addItem(new Item_qa_answer(id,image,no,u_name,qa));
            }

            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
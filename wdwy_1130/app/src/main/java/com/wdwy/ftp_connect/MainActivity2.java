package com.wdwy.ftp_connect;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    String myJSON;
    JSONArray classes = null;
    //HashMap의 key값으로  class_name,user_name,class_context
    private static final String TAG_RESULTS="result";
    private static final String Class_name = "class_name";
    private static final String User_name = "user_name";
    private static final String Class_context ="class_context";

    ArrayList<HashMap<String, String>> classList;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = (ListView) findViewById(R.id.listView);
        classList = new ArrayList<HashMap<String,String>>();
        getData("http://hyper0616.dothome.co.kr/class_list.php");

    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            classes= jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i< classes.length();i++){
                JSONObject c =  classes.getJSONObject(i);
                String id = c.getString(Class_name);
                String name = c.getString(User_name);
                String address = c.getString(Class_context);

                HashMap<String,String> class1 = new HashMap<String,String>();

                class1.put(Class_name,id);
                class1.put(User_name,name);
                class1.put(Class_context,address);

                classList.add(class1);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity2.this, classList, R.layout.list_item,
                    new String[]{Class_name,User_name,Class_context},
                    new int[]{R.id.class_name, R.id.user_name, R.id.class_context}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }



            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
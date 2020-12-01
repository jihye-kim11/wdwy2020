package com.wdwy.ftp_connect.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.navaigation;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Profile;
import com.kakao.sdk.user.model.User;

import java.net.MalformedURLException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* kotlin
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
*/

public class LoginActivity extends AppCompatActivity {
    //private static final String TAG = "Login";
    //public static final String USER_ID = "id";
    //public static final String PROFILE_IMG = "img";

    //sqlite
    myDBAdapter dbAdapter;

    String userImage, nickname, user_id;
    long userId;
    String strId;

    private Button autoLogButton;

    private ImageButton loginBtn;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NetworkUtil.setNetworkPolicy();


        dbAdapter = new myDBAdapter(this);

        //myHelper = new myDBHelper(this);

        loginBtn = findViewById(R.id.LoginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        autoLogButton = findViewById(R.id.autoLogButton);
        autoLogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //sqlite에 로그인정보 저장되어있는 경우 -> 바로 자동 로그인

                /*
                sqlDB = myHelper.getReadableDatabase();
                Cursor cusor;
                cusor = sqlDB.rawQuery("SELECT gName FROM groupTBL;", null);
                strId = "";
                while (cusor.moveToNext()) {
                    strId += cusor.getString(0) + "\r";
                    System.out.println("sqlite id : " + strId);
                }
                 */
                dbAdapter.open();
                strId = dbAdapter.idOpen();
                dbAdapter.close();

                if (!strId.equals("")){
                    Toast.makeText(getApplicationContext(), "login됨 : "+strId, 0).show();
                    Intent intent = new Intent(getApplicationContext(), navaigation.class);
                    intent.putExtra("userId", strId);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "간편로그인 불가능. 카카오로그인 필요.", 0).show();
                }
                //cusor.close();
            }
        });

        setActionBar();

    }

    //카카오계정 로그인
    private void login(){
        NetworkUtil.setNetworkPolicy();

        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(throwable != null){
                    Log.e("LoginActivity","throwable="+throwable.getMessage());
                }

                if(!TextUtils.isEmpty(oAuthToken.getAccessToken())){
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            long userId = user.getId();
                            //System.out.println(userId);
                            user_id = String.valueOf(userId);
                            Account account = user.getKakaoAccount();
                            if(account != null){
                                Profile userProfile = account.getProfile();
                                if(userProfile != null) {
                                    String nickname = userProfile.getNickname();
                                    String userImage = userProfile.getProfileImageUrl();
                                    //String images[] = userImage.split("/");
                                    // 데이터 송신
                                    try {
                                        com.wdwy.ftp_connect.PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/update_log.php");
                                        String result = request.PhPtest_log(user_id, nickname, userImage);
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }

                                    //sqlite에 id 저장
                                    //sqlDB = myHelper.getWritableDatabase();
                                    //초기화
                                    //.onUpgrade(sqlDB, 1, 2);
                                    dbAdapter.open();
                                    dbAdapter.clear();
                                    //sqlDB.execSQL("INSERT INTO groupTBL VALUES ('" + user_id + "');");
                                    dbAdapter.insert(user_id);
                                    dbAdapter.close();
                                    //sqlDB.close();
                                    Toast.makeText(getApplicationContext(), "db에 log정보 추가 : "+user_id, 0).show();

                                    Intent intent = new Intent(getApplicationContext(), navaigation.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("nickname", nickname);
                                    intent.putExtra("userImage", userImage);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                            return null;
                        }
                    });
                }
                Log.e("LoginActivity","login token = "+oAuthToken);

                return null;
            }
        });
    }

/*
    //sqlite
    public class myDBHelper extends SQLiteOpenHelper {
        Context context;

        public myDBHelper(LoginActivity context){
            super((Context) context, "groupDB", null, 1);
        }

        //테이블 생성(테이블에 없을 때, 호출될 때)
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY);");
        }

        //table 삭제, 생성
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }

    public String idOpen(myDBHelper myHelper, SQLiteDatabase sqlDB){
        String sqlId;
        sqlDB = myHelper.getReadableDatabase();
        Cursor cusor;
        cusor = sqlDB.rawQuery("SELECT gName FROM groupTBL;", null);
        sqlId = "";
        while (cusor.moveToNext()) {
            sqlId += cusor.getString(0) + "\r";
            //System.out.println("sqlite id : " + strId);
        }
        cusor.close();
        sqlDB.close();
        return sqlId;
    }
*/

    private void setActionBar(){
        CustomActionBar_login lg=new CustomActionBar_login(this, getSupportActionBar());
        lg.setActionBar();
    }


/*
    //로그아웃
    //로그아웃 시에 sqlite에서 삭제해야함?
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        }
        else {
            Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
*/
}

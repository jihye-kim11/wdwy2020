
package com.wdwy.ftp_connect.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.wdwy.ftp_connect.NetworkUtil;
import com.wdwy.ftp_connect.PHPRequest;
import com.wdwy.ftp_connect.R;
import com.wdwy.ftp_connect.ui.home.myDBAdapter;

import java.net.MalformedURLException;

public class pay extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    public ImageButton back_btn;
    String search,class_name,user_name,class_price,no;
    public TextView class_name_t,user_name_t,class_price_t;
    public Button pay_btn;
    private BillingProcessor bp;
    private AppStorage storage;
    myDBAdapter dbAdapter;
    String userId;
    String myJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent = getIntent() ;
        NetworkUtil.setNetworkPolicy();
        search=intent.getStringExtra("classno2");
        no=intent.getStringExtra("classno");
        class_name=intent.getStringExtra("class_name");
        user_name=intent.getStringExtra("user_name");
        class_price=intent.getStringExtra("class_price");
        class_name_t=(TextView)findViewById(R.id.class_name);
        user_name_t=(TextView)findViewById(R.id.user_name);
        class_price_t=(TextView)findViewById(R.id.class_price);
        class_name_t.setText("    클래스명: "+class_name);
        user_name_t.setText("    강의자: "+user_name);
//        class_price_t.setText("   "+class_price+"원");
        dbAdapter = new myDBAdapter( getApplicationContext());
        dbAdapter.open();
        userId = dbAdapter.idOpen();
        dbAdapter.close();
        AppStorage storage = new AppStorage(this);
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskaMN5bdlA9mWMRMPboUBJu4bEd4nC0NTATfcu7MG+YtJHBOVTNE1QWpjz0SQQWMs4pqM4f9ywEzTtubFpFqs/Sip9mbMDxO9R4Ckl0+jepSl4aM6KixP6LliA1Kzl7OqWQllwQ2Rhqm9Bhk88klM8CFNIzaI/0IYyHrX9eQE8ndqFk6hf3s9UrrjFAyqX6xn3eqMF+EIbvHsoKAkoo7ViEUgAJHnMabZjuwES0aX2IIcAfTWCVTEHUoV1nq1OcvIOBpQvSkma7MQuK4HBFV+QfEMurUAllWSAmBGXoHGYaPXnCgeZca4cuhuK1TawItgyUcrfFM6O3U1Zp68Af+IwIDAQAB", this);
        bp.initialize();
        Activity t=this;
        setActionBar();
        back_btn=(ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 종료 후 이전페이지가 나타남
            }
        });
        pay_btn=(Button)findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {//결제하기 버튼
                bp.purchase(t, "no2");
                // finish();


            }
        });


    }
    private void setActionBar(){
        CustomActionBar_details ca=new CustomActionBar_details(this, getSupportActionBar());
        ca.setActionBar();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        // 아이템 구매 성공 시 호출.
        // 따라서 보상을 지급하든(광고 제거) 혹은 해당 아이템을 소비하든 해당 기능을 작성
        try {
            PHPRequest request = new PHPRequest("http://hyper0616.dothome.co.kr/pay.php");
            String result = request.PhPtest2(no, search,"", userId);//유저 아이디 우선 yumi로 임의값 넣어놓음.
            myJSON =result.trim();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        finish();
        Intent intent = new Intent(getApplicationContext(), payfinish.class);
        startActivity(intent);

    }

    @Override
    public void onPurchaseHistoryRestored() {
        // 구매 내역 복원 및 구매한 모든 PRODUCT ID 목록이 Google Play에서 로드 될 때 호출.
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        // 구매 시 에러가 발생했을 때 처리

    }

    @Override
    public void onBillingInitialized() {

    }
    public class AppStorage {
        private Context context;
        private SharedPreferences pref;
        private String PURCHASED_REMOVE_ADS = "remove_ads";

        public AppStorage(Context context) {
            pref = context.getSharedPreferences("app_storage", Context.MODE_PRIVATE);
            this.context = context;
        }

        public boolean purchasedRemoveAds() {
            return pref.getBoolean(PURCHASED_REMOVE_ADS, false);
        }

        public void setPurchasedRemoveAds(boolean flag) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(PURCHASED_REMOVE_ADS, flag);
            editor.apply();
        }
    }

}
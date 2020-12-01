package com.wdwy.ftp_connect;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Kakao SDK 초기화
        KakaoSdk.init(this, "65d67f843a14a372a0a9a4b48f68d872");
    }
}


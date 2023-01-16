package com.example.petsapce_week1.loginrelated

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplictation : Application(){
    override fun onCreate() {
        super.onCreate()
        //다른 초기화 코드들

        //Kakao SDK 초기화
        KakaoSdk.init(this, "2cf6ec08f9cb13a8974a4eedb4c7029c")
    }
}
package com.moneyminions.mvvmtemplate.di

import android.app.Application
import com.moneyminions.mvvmtemplate.util.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {

    // 코틀린의 전역변수
    companion object {
        //만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sharedPreferences: SharedPreferenceUtil

    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어준다.
    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferenceUtil(applicationContext)
    }
}
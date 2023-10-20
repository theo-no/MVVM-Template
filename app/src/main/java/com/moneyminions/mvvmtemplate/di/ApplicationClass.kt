package com.moneyminions.mvvmtemplate.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {

    // 코틀린의 전역변수
    companion object {

    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어준다.
    override fun onCreate() {
        super.onCreate()
    }
}
package com.moneyminions.mvvmtemplate.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrefenceViewModel @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
): ViewModel() {

    private var _accessToken: String = ""
    fun setAccessToken(token: String){
        _accessToken = token
    }

    fun putAccessToken(){
        preferenceDataSource.setAccessToken(_accessToken)
    }

    fun getAccessToken(): String?{
        return  preferenceDataSource.getAccessToken()
    }
}
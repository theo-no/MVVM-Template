package com.moneyminions.mvvmtemplate.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
): ViewModel() {

    fun getPermissionRejected(key: String): Boolean{
        return preferenceDataSource.getPermissionRejected(key)
    }

    fun setPermissionRejected(key: String){
        preferenceDataSource.setPermissionRejected(key, true)
    }
}
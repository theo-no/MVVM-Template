package com.moneyminions.mvvmtemplate.viewmodel

import androidx.lifecycle.ViewModel
import com.moneyminions.mvvmtemplate.MainActivity
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
): ViewModel() {

}
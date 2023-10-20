package com.moneyminions.mvvmtemplate.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.moneyminions.mvvmtemplate.MainActivity
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import com.moneyminions.mvvmtemplate.util.hasCameraPermissions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
): ViewModel() {

}
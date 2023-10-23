package com.moneyminions.mvvmtemplate.viewmodel

import android.graphics.Bitmap
import android.os.Environment
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneyminions.mvvmtemplate.MainActivity
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
): ViewModel() {

    private val _isSelectedCamera = MutableSharedFlow<Boolean>()
    val isSelectedCamera: SharedFlow<Boolean>
        get() = _isSelectedCamera
    fun setIsSelectedCamera(value: Boolean){
        viewModelScope.launch {
            _isSelectedCamera.emit(value)
        }
    }



    private val _selectedCameraImage = MutableSharedFlow<Bitmap>()
    val selectedCameraImage: SharedFlow<Bitmap>
        get() = _selectedCameraImage.asSharedFlow()
    fun setSelectedCameraImage(bitmap: Bitmap){
        viewModelScope.launch {
            _selectedCameraImage.emit(bitmap)
        }
    }
}
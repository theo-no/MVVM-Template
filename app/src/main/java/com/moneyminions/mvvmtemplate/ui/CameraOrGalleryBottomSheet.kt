package com.moneyminions.mvvmtemplate.ui

import android.Manifest.permission_group.STORAGE
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseBottomSheet
import com.moneyminions.mvvmtemplate.databinding.BottomsheetCameraOrDialogBinding
import com.moneyminions.mvvmtemplate.databinding.FragmentCameraBinding
import com.moneyminions.mvvmtemplate.viewmodel.CameraViewModel
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "차선호"
class CameraOrGalleryBottomSheet(
    private val cameraViewModel: CameraViewModel
): BaseBottomSheet<BottomsheetCameraOrDialogBinding>(
    BottomsheetCameraOrDialogBinding::bind,
    R.layout.bottomsheet_camera_or_dialog
) {
    private lateinit var file: File
    private lateinit var currentPhotoPath: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.apply {
            buttonCamera.setOnClickListener {
                // 1. TakePicture
                cameraViewModel.setIsSelectedCamera(true)
                this@CameraOrGalleryBottomSheet.dismiss()
            }
            buttonGallery.setOnClickListener {

            }
        }
    }





}
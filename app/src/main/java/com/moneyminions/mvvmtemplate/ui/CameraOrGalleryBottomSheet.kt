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
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseBottomSheet
import com.moneyminions.mvvmtemplate.databinding.BottomsheetCameraOrDialogBinding
import com.moneyminions.mvvmtemplate.databinding.FragmentCameraBinding
import com.moneyminions.mvvmtemplate.di.ApplicationClass
import com.moneyminions.mvvmtemplate.util.checkOnePermission
import com.moneyminions.mvvmtemplate.util.hasPermissions
import com.moneyminions.mvvmtemplate.viewmodel.CameraViewModel
import com.moneyminions.mvvmtemplate.viewmodel.MainViewModel
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
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
//        checkCameraPermission()
        checkGalleryPermission()
    }

    private fun initListener(){
        binding.apply {
            buttonCamera.setOnClickListener {
                if(!mActivity.hasPermissions(ApplicationClass.CAMERA_PERMISSION_REJECTED)){
                    mActivity.showToast("설정에서 카메라 권한을 허용해주세요")
                    return@setOnClickListener
                }else{
                    // 1. TakePicture
                    cameraViewModel.setIsSelectedCamera(true)
                    this@CameraOrGalleryBottomSheet.dismiss()
                }
            }
            buttonGallery.setOnClickListener {
                cameraViewModel.setIsSelectedGallery(true)
                this@CameraOrGalleryBottomSheet.dismiss()
            }
        }
    }

    private fun checkCameraPermission(){
        checkOnePermission(
            fragment = this,
            activity = mActivity,
            permission = ApplicationClass.CAMERA_PERMISSION_REJECTED,
            getPermissionRejected = mainViewModel.getPermissionRejected(ApplicationClass.CAMERA_PERMISSION_REJECTED),
            setPermissionRejected = {mainViewModel.setPermissionRejected(ApplicationClass.CAMERA_PERMISSION_REJECTED)},
            getIsShowedPermissionDialog = mainViewModel.getIsShowedPermissionDialog(
                ApplicationClass.CAMERA_PERMISSION_REJECTED +"show"),
            setIsShowedPermissionDialog = {mainViewModel.setIsShowedPermissionDialog(
                ApplicationClass.CAMERA_PERMISSION_REJECTED +"show")}
        )
    }

    private fun checkGalleryPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            checkOnePermission(
                fragment = this,
                activity = mActivity,
                permission = ApplicationClass.IMAGE_PERMISSION_REJECTED,
                getPermissionRejected = mainViewModel.getPermissionRejected(ApplicationClass.IMAGE_PERMISSION_REJECTED),
                setPermissionRejected = {mainViewModel.setPermissionRejected(ApplicationClass.IMAGE_PERMISSION_REJECTED)},
                getIsShowedPermissionDialog = mainViewModel.getIsShowedPermissionDialog(
                    ApplicationClass.IMAGE_PERMISSION_REJECTED +"show"),
                setIsShowedPermissionDialog = {mainViewModel.setIsShowedPermissionDialog(
                    ApplicationClass.IMAGE_PERMISSION_REJECTED +"show")}
            )
        }
        else{
            checkOnePermission(
                fragment = this,
                activity = mActivity,
                permission = ApplicationClass.GALLERY_PERMISSION_REJECTED,
                getPermissionRejected = mainViewModel.getPermissionRejected(ApplicationClass.GALLERY_PERMISSION_REJECTED),
                setPermissionRejected = {mainViewModel.setPermissionRejected(ApplicationClass.GALLERY_PERMISSION_REJECTED)},
                getIsShowedPermissionDialog = mainViewModel.getIsShowedPermissionDialog(
                    ApplicationClass.GALLERY_PERMISSION_REJECTED +"show"),
                setIsShowedPermissionDialog = {mainViewModel.setIsShowedPermissionDialog(
                    ApplicationClass.GALLERY_PERMISSION_REJECTED +"show")}
            )
        }
    }


}
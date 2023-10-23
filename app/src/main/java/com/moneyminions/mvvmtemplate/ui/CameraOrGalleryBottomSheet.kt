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
import com.moneyminions.mvvmtemplate.util.checkAllPermission
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
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        checkPermission()
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    if(!mActivity.hasPermissions(ApplicationClass.IMAGE_PERMISSION_REJECTED)){
                        mActivity.showToast("설정에서 사진/미디어 접근 권한을 허용해주세요")
                        return@setOnClickListener
                    }else{
                        cameraViewModel.setIsSelectedGallery(true)
                        this@CameraOrGalleryBottomSheet.dismiss()
                    }
                }else{
                    if(!mActivity.hasPermissions(ApplicationClass.GALLERY_PERMISSION_REJECTED)){
                        mActivity.showToast("설정에서 사진/미디어 접근 권한을 허용해주세요")
                        return@setOnClickListener
                    }else {
                        cameraViewModel.setIsSelectedGallery(true)
                        this@CameraOrGalleryBottomSheet.dismiss()
                    }
                }
            }
        }
    }

    private fun checkPermission(){
        val cameraAndGalleryPermissionList: Array<String>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            cameraAndGalleryPermissionList = arrayOf(
                ApplicationClass.CAMERA_PERMISSION_REJECTED,
                ApplicationClass.IMAGE_PERMISSION_REJECTED
            )
        }else{
            cameraAndGalleryPermissionList = arrayOf(
                ApplicationClass.CAMERA_PERMISSION_REJECTED,
                ApplicationClass.GALLERY_PERMISSION_REJECTED
            )
        }
        checkAllPermission(
            fragment = this,
            activity = mActivity,
            permissionList = cameraAndGalleryPermissionList,
            getPermissionRejected = {it -> mainViewModel.getPermissionRejected(it)},
            setPermissionRejected = {it -> mainViewModel.setPermissionRejected(it)},
            getIsShowedPermissionDialog = {it -> mainViewModel.getIsShowedPermissionDialog(it+"show")},
            setIsShowedPermissionDialog = {it -> mainViewModel.setIsShowedPermissionDialog(it+"show")},
            isShowDialog = {if(!mainViewModel.isShowPermissionDialog.value) mainViewModel.setIsShowPermissionDialog(true)}
        )
    }

}
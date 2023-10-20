package com.moneyminions.mvvmtemplate.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentCameraBinding
import com.moneyminions.mvvmtemplate.di.ApplicationClass.Companion.CAMERA_PERMISSION_REJECTED
import com.moneyminions.mvvmtemplate.util.checkOnePermission
import com.moneyminions.mvvmtemplate.util.hasPermissions
import com.moneyminions.mvvmtemplate.viewmodel.CameraViewModel
import com.moneyminions.mvvmtemplate.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "차선호"
@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>(
    FragmentCameraBinding::bind,
    R.layout.fragment_camera
) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val cameraViewModel: CameraViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCameraPermission()
        initListener()
    }

    private fun checkCameraPermission(){
        checkOnePermission(
            fragment = this,
            activity = mActivity,
            permission = CAMERA_PERMISSION_REJECTED,
            getPermissionRejected = mainViewModel.getPermissionRejected(CAMERA_PERMISSION_REJECTED),
            setPermissionRejected = {mainViewModel.setPermissionRejected(CAMERA_PERMISSION_REJECTED)},
            getIsShowedPermissionDialog = mainViewModel.getIsShowedPermissionDialog(
                CAMERA_PERMISSION_REJECTED+"show"),
            setIsShowedPermissionDialog = {mainViewModel.setIsShowedPermissionDialog(
                CAMERA_PERMISSION_REJECTED+"show")}
        )
    }

    private fun openCamerOrGalleryDialog(){
        val cameraOrGalleryDialog = CameraOrGalleryBottomSheet()
        cameraOrGalleryDialog.show(childFragmentManager,null)
    }

    override fun initListener() {
        binding.apply {
            buttonCamera.setOnClickListener {
                if(!mActivity.hasPermissions(CAMERA_PERMISSION_REJECTED)){
                    mActivity.showToast("설정에서 카메라 권한을 허용해주세요")
                    return@setOnClickListener
                }else{
                    //TODO 카메라 키는 로직 추가
                    openCamerOrGalleryDialog()
                }
            }
        }
    }

}
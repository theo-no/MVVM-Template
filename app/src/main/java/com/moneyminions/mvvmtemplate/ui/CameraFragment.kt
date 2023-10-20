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
import androidx.fragment.app.viewModels
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentCameraBinding
import com.moneyminions.mvvmtemplate.util.checkCameraPermission
import com.moneyminions.mvvmtemplate.util.hasCameraPermissions
import com.moneyminions.mvvmtemplate.viewmodel.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "차선호"
@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>(
    FragmentCameraBinding::bind,
    R.layout.fragment_camera
) {

    private val cameraViewModel: CameraViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "pref의 camera permission rejected : ${cameraViewModel.getCameraPermissionRejected()} ")
        checkCameraPermission(
            fragment = this,
            activity = mActivity,
            getCameraPermissionRejected = cameraViewModel.getCameraPermissionRejected(),
            setCameraPermissionRejected = {cameraViewModel.setCameraPermissionRejected()}
        )
        initListener()
    }



    override fun initListener() {
        binding.apply {
            buttonCamera.setOnClickListener {

            }
        }
    }

}
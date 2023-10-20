package com.moneyminions.mvvmtemplate.ui

import android.os.Bundle
import android.view.View
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseBottomSheet
import com.moneyminions.mvvmtemplate.databinding.BottomsheetCameraOrDialogBinding

class CameraOrGalleryBottomSheet: BaseBottomSheet<BottomsheetCameraOrDialogBinding>(
    BottomsheetCameraOrDialogBinding::bind,
    R.layout.bottomsheet_camera_or_dialog
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.apply {

        }
    }
}
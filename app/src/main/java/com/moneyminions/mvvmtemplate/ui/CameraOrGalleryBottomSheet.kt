package com.moneyminions.mvvmtemplate.ui

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseBottomSheet
import com.moneyminions.mvvmtemplate.databinding.BottomsheetCameraOrDialogBinding
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "차선호"
class CameraOrGalleryBottomSheet: BaseBottomSheet<BottomsheetCameraOrDialogBinding>(
    BottomsheetCameraOrDialogBinding::bind,
    R.layout.bottomsheet_camera_or_dialog
) {
    
    var pictureUri: Uri? = null
    private val getTakePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if(it) {
            Log.d(TAG, "camera 결과 : $it")
        }
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
    }

    // 카메라를 실행하며 결과로 비트맵 이미지를 얻음
//    private val getTakePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
//        bitmap.let { binding.mainImg.setImageBitmap(bitmap) }
//    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.apply {
            buttonCamera.setOnClickListener {
                // 1. TakePicture
                getTakePicture.launch(pictureUri)

                // 2. TakePicturePreview
//            getTakePicturePreview.launch(null)    // Bitmap get

                this@CameraOrGalleryBottomSheet.dismiss()
            }
            buttonGallery.setOnClickListener {

            }
        }
    }
}
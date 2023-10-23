package com.moneyminions.mvvmtemplate.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.moneyminions.mvvmtemplate.R
import com.moneyminions.mvvmtemplate.base.BaseFragment
import com.moneyminions.mvvmtemplate.databinding.FragmentCameraBinding
import com.moneyminions.mvvmtemplate.di.ApplicationClass.Companion.CAMERA_PERMISSION_REJECTED
import com.moneyminions.mvvmtemplate.util.hasPermissions
import com.moneyminions.mvvmtemplate.viewmodel.CameraViewModel
import com.moneyminions.mvvmtemplate.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "차선호"
@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>(
    FragmentCameraBinding::bind,
    R.layout.fragment_camera
) {
    private lateinit var currentPhotoPath: String
    private lateinit var file: File

    private val mainViewModel: MainViewModel by activityViewModels()
    private val cameraViewModel: CameraViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initCollect()
    }


    private fun openCamerOrGalleryDialog(){
        val cameraOrGalleryDialog = CameraOrGalleryBottomSheet(cameraViewModel)
        cameraOrGalleryDialog.show(childFragmentManager,null)
    }

    override fun initListener() {
        binding.apply {
            buttonCamera.setOnClickListener {
                openCamerOrGalleryDialog()
            }
        }
    }

    private fun initCollect(){
        cameraViewModel.apply {
            //카메라 열기
            viewLifecycleOwner.lifecycleScope.launch {
                isSelectedCamera.collectLatest {
                    /**
                     * 카메라 앱을 띄워 사진을 받아옵니다.
                     */
                    file = createImageFile()
                    //AndroidMenifest에 설정된 URI와 동일한 값으로 설정한다.
                    val photoUri =
                        FileProvider.getUriForFile(requireContext(), "com.moneyminions.mvvmtemplate.fileprovider", file)
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    cameraActivityResult.launch(intent) //카메라 앱을 실행 한 후 결과를 받기 위해서 launch
                }
            }

            //갤러리 열기
            viewLifecycleOwner.lifecycleScope.launch {
                isSelectedGallery.collectLatest {
                    galleryActivityResult.launch("image/*")
                }
            }

        }
    }

    /**
     * 카메라로 찍은 사진을 사진파일로 만듭니다.
     * openCamera()로 결과를 반환합니다.
     */
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File =
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path
            currentPhotoPath = absolutePath
        }
    }
    private val cameraActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                var bitmap: Bitmap

                if (Build.VERSION.SDK_INT >= 29) {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(
                        requireContext().contentResolver,
                        Uri.fromFile(file)
                    )
                    Log.d(TAG, "source : $source")
                    bitmap = ImageDecoder.decodeBitmap(source)

                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        Uri.fromFile(file)
                    )
                }
                binding.imagePhoto.setImageBitmap(bitmap)
            }
        }

    //갤러리 결과 받아오기
    private val galleryActivityResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // 결과를 처리하는 코드를 작성합니다.
            if (uri != null) {
                Glide.with(binding.imagePhoto)
                    .load(uri)
                    .into(binding.imagePhoto)
            }

        }

}


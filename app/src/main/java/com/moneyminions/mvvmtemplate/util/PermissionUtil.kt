package com.moneyminions.mvvmtemplate.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.moneyminions.mvvmtemplate.MainActivity
import java.security.Permission

private const val TAG = "차선호"
fun Context.hasCameraPermissions(): Boolean{
    return ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

data class PermissionWithName(
    val permission: String,
    val name: String
)


private const val CAMERA_PERMISSION_REJECTED = "camera_permission_rejected"

val permissionList = arrayOf(
    PermissionWithName(permission = android.Manifest.permission.CAMERA, name = CAMERA_PERMISSION_REJECTED)

)


fun checkCameraPermission(
    fragment: Fragment,
    activity: MainActivity,
    getCameraPermissionRejected: Boolean,
    setCameraPermissionRejected: () -> Unit
){
    if(activity.hasCameraPermissions()) return
    val requestMultiplePermission = fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
        results.forEach {
            if(!it.value) {
                Log.d(TAG, "checkCameraPermission 내에서 : $getCameraPermissionRejected ")
                if(!getCameraPermissionRejected){
                    setCameraPermissionRejected()
                }else{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivity(intent)
                }
            }
        }
    }
    requestMultiplePermission.launch(arrayOf(android.Manifest.permission.CAMERA))

}
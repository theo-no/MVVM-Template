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
import com.moneyminions.mvvmtemplate.di.ApplicationClass.Companion.CAMERA_PERMISSION_REJECTED
import java.security.Permission

private const val TAG = "차선호"
fun Context.hasCameraPermissions(permission: String): Boolean{
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

data class PermissionWithName(
    val permission: String,
    val name: String
)



val permissionList = arrayOf(
    CAMERA_PERMISSION_REJECTED
)

fun checkAllPermission(
    activity: MainActivity,
    getPermissionRejected: Boolean,
    setPermissionRejected: () -> Unit
){
    for(permission in permissionList) {
        if (activity.hasCameraPermissions(permission)) return
        val requestMultiplePermission =
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                results.forEach {
                    if (!it.value) {
                        if (!getPermissionRejected) {
                            setPermissionRejected()
                        } else {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package", activity.packageName, null)
                            intent.data = uri
                            activity.startActivity(intent)
                        }
                    }
                }
            }
        requestMultiplePermission.launch(arrayOf(permission))
    }
}


fun checkOnePermission(
    fragment: Fragment?,
    activity: MainActivity,
    permission: String,
    getPermissionRejected: Boolean,
    setPermissionRejected: () -> Unit
){
    if(activity.hasCameraPermissions(permission)) return
    val requestMultiplePermission = (fragment?:activity).registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
        results.forEach {
            if(!it.value) {
                if(!getPermissionRejected){
                    setPermissionRejected()
                }else{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivity(intent)
                }
            }
        }
    }
    requestMultiplePermission.launch(arrayOf(permission))

}
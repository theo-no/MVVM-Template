package com.moneyminions.mvvmtemplate.util

import android.app.AlertDialog
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
fun Context.hasPermissions(permission: String): Boolean{
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


fun checkOnePermission(
    fragment: Fragment?,
    activity: MainActivity,
    permission: String,
    getPermissionRejected: Boolean,
    setPermissionRejected: () -> Unit,
    getIsShowedPermissionDialog: Boolean,
    setIsShowedPermissionDialog: () -> Unit
){
    if(activity.hasPermissions(permission)) return
    val requestMultiplePermission = (fragment?:activity).registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
        results.forEach {
            if(!it.value) {
                if(!getPermissionRejected){
                    setPermissionRejected()
                }else{
                    if(!getIsShowedPermissionDialog) {
                        //TODO 다이얼로그 띄우고 거기서 확인 누르면 설정으로 이동하도록
//                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                        val uri = Uri.fromParts("package", activity.packageName, null)
//                        intent.data = uri
//                        activity.startActivity(intent)
                        setIsShowedPermissionDialog()
                        showPermissionDialog(activity)
                    }
                }
            }
        }
    }
    requestMultiplePermission.launch(arrayOf(permission))

}

// 다이얼로그를 띄우기 위한 함수
fun showPermissionDialog(activity: MainActivity) {
    val builder = AlertDialog.Builder(activity)

    builder.setTitle("권한 요청")
    builder.setMessage("앱을 사용하려면 권한을 허용해야 합니다. 설정으로 이동하여 권한을 허용하시겠습니까?")

    builder.setPositiveButton("확인") { dialog, which ->
        // 사용자가 확인 버튼을 누를 때 설정으로 이동하는 코드
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivity(intent)
    }

    builder.setNegativeButton("취소") { dialog, which ->
        // 사용자가 취소 버튼을 누를 때 수행할 작업 (옵션)
    }

    val dialog = builder.create()
    dialog.show()
}
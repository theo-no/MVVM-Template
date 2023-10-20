package com.moneyminions.mvvmtemplate.datasource

interface PreferenceDataSource {
    fun getAccessToken(): String?
    fun setAccessToken(newToken: String)
    fun getRefreshToken(): String?
    fun setRefreshToken(newToken: String)
    fun resetToken()
    fun setPermissionRejected(key: String, value: Boolean)
    fun getPermissionRejected(key: String): Boolean
    fun setIsShowedPermissionDialog(key: String, value: Boolean)
    fun getIsShowedPermissionDialog(key: String): Boolean
}
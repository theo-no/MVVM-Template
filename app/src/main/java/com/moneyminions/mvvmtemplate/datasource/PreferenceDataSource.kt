package com.moneyminions.mvvmtemplate.datasource

interface PreferenceDataSource {
    fun getAccessToken(): String?
    fun setAccessToken(newToken: String)
    fun getRefreshToken(): String?
    fun setRefreshToken(newToken: String)
    fun resetToken()
    fun setCameraPermissionRejected(value: Boolean)
    fun getCameraPermissionRejected(): Boolean
}
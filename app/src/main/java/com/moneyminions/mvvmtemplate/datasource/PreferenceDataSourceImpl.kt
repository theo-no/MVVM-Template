package com.moneyminions.mvvmtemplate.datasource

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

private const val TAG = "차선호"
class PreferenceDataSourceImpl(context: Context): PreferenceDataSource {
    companion object{
        private const val AUTH_ENCRYPTED_PREFERENCE = "mvvm_template"
        private const val X_ACCESS_TOKEN = "access_token"
        private const val X_REFRESH_TOKEN = "refresh_token"
        private const val X_ROLE = "role"
        private const val FCM_TOKEN = "fcm_token"
        private const val ROOM_ID = "room_id"
    }
    private fun getPreference(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            AUTH_ENCRYPTED_PREFERENCE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }
    private val prefs by lazy { getPreference(context) }
    private val editor by lazy { prefs.edit() }

    private fun putString(key: String, data: String?){
        editor.putString(key, data)
        editor.apply()
    }
    private fun getString(key: String, defValue: String? = null): String? {
        return prefs.getString(key, defValue)
    }

    private fun putBoolean(key: String, value: Boolean){
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    override fun getAccessToken(): String? {
        return getString(X_ACCESS_TOKEN)
    }

    override fun setAccessToken(newToken: String) {
        putString(X_ACCESS_TOKEN, newToken)
    }

    override fun getRefreshToken(): String? {
        TODO("Not yet implemented")
    }

    override fun setRefreshToken(newToken: String) {
        TODO("Not yet implemented")
    }

    override fun resetToken() {
        TODO("Not yet implemented")
    }

    override fun setPermissionRejected(key: String, value: Boolean) {
        putBoolean(key, value)
    }

    override fun getPermissionRejected(key: String): Boolean {
        return getBoolean(key)
    }
}
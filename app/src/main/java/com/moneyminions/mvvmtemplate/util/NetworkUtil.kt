package com.moneyminions.mvvmtemplate.util

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

private const val TAG = "차선호"
private fun <T> Response<T>.isDelete(): Boolean {
    return this.raw().request.method == "DELETE"
}

@Suppress("UNCHECKED_CAST")
fun <T> Response<T>.getValueOrThrow(): T {

    if (this.isSuccessful) {
        if (this.isDelete()) { return Unit as T }
        return this.body() ?: throw NetworkThrowable.IllegalStateThrowable()
    }

    // TODO 서버에 따라 다를수도?
    val errorResponse = errorBody()?.string()
    val jsonObject = errorResponse?.let { JSONObject(it) }
    val code = jsonObject?.getInt("code") ?: 0
    val message = jsonObject?.getString("message") ?: ""

    Log.e(TAG, "getValueOrThrow: Error code : ${code}, message : ${message}")

    when (code) {
        in 100..199 -> { throw NetworkThrowable.Base100Throwable(code, message) }
        in 300..399 -> { throw NetworkThrowable.Base300Throwable(code, message) }
        in 400..499 -> { throw NetworkThrowable.Base400Throwable(code, message) }
        in 500..599 -> { throw NetworkThrowable.Base500Throwable(code, message) }
    }

    throw NetworkThrowable.IllegalStateThrowable()
}
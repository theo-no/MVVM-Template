package com.moneyminions.mvvmtemplate.util

import retrofit2.Response


internal inline fun <T> handleApi(block: () -> Response<T>): T {
    return block.invoke().getValueOrThrow()
}
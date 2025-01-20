package com.rure.angkorlife.domain.repository

sealed class RetrofitResult<out T> {
    data class Success<out T>(val value: T): RetrofitResult<T>()
    data class Failure(val error: Throwable) : RetrofitResult<Nothing>()
}
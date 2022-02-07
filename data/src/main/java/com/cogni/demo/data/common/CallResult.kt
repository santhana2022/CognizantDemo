package com.cogni.demo.data.common

sealed class CallResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : CallResult<T>()
    data class Error(val exception: CallError) : CallResult<Nothing>()
    object Loading : CallResult<Nothing>()
}
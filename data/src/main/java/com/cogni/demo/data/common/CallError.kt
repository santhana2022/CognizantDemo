package com.cogni.demo.data.common

sealed class CallError {
    object ErrorEmptyData : CallError()
    object ErrorServer : CallError()
    data class ErrorException(val throwable: Throwable) : CallError()
}
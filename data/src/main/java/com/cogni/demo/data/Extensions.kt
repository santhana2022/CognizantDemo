package com.cogni.demo.data

import com.cogni.demo.data.common.CallResult
import com.cogni.demo.data.repository.remote.MAX_RETRIES
import com.cogni.demo.data.repository.remote.getBackoffDelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

fun <T : Any> Flow<CallResult<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < MAX_RETRIES) -> {
                delay(getBackoffDelay(attempt))
                true
            }
            else -> {
                false
            }
        }
    }.onStart {
        emit(CallResult.Loading)
    }
package com.cogni.demo.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


fun <T : ViewModel> AppCompatActivity.viewModelProvider(
    factory: ViewModelProvider.Factory,
    model: KClass<T>
): T {
    return ViewModelProvider(this, factory)[model.java]
}

fun ViewModel.launchOnUI(task: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
        task()
    }
}
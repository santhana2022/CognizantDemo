package com.cogni.demo.domain.dto

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


data class Character(
    val appearance: List<Any>? = null,
    val better_call_saul_appearance: List<Int>? = null,
    val birthday: String? = null,
    val category: String? = null,
    val char_id: Int? = null,
    val img: String? = null,
    @Bindable
    @Transient var imageData: Bitmap? = null,
    val name: String? = null,
    val nickname: String? = null,
    val occupation: List<String>? = null,
    val portrayed: String? = null,
    val status: String? = null
) : BaseObservable()

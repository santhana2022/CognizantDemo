package com.cogni.demo.domain.dto

data class Quote(
    val quote_id: Long? = 0,
    val quote: String? = "",
    val author: String? = "",
)
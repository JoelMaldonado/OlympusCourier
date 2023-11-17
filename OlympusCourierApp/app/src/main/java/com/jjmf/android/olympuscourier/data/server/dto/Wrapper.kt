package com.jjmf.android.olympuscourier.data.server.dto

data class Wrapper<T>(
    val isSuccess: Boolean,
    val mensaje: String,
    val data: T?,
)

package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.data.server.dto.ClienteDto

interface ClienteRepository {

    suspend fun getById(id:String): ClienteDto?
}
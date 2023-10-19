package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.data.firebase.ClienteDto
import com.jjmf.android.olympuscourier.domain.model.Cliente

interface ClienteRepository {

    suspend fun getById(id:String):ClienteDto?
}
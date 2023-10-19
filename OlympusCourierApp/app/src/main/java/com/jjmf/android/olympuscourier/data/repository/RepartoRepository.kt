package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.firebase.RepartoDto
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.flow.Flow

interface RepartoRepository {

    suspend fun getAll() : Flow<List<RepartoDto>>

}
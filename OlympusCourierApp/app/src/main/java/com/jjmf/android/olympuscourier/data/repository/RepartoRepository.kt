package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.server.dto.RepartoDto
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.flow.Flow

interface RepartoRepository {

    suspend fun listarRepartos(): EstadosResult<List<Reparto>>
    suspend fun getAll() : Flow<List<RepartoDto>>
    suspend fun get(idReparto:Int) : Reparto?
    suspend fun update(idReparto:String)

}
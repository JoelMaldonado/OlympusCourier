package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.domain.model.Usuario

interface UsuarioRepository {
    suspend fun login(doc:String, clave:String) : EstadosResult<Int>
    suspend fun getList(): List<Usuario>
    suspend fun get(id: Int) : EstadosResult<Usuario>
}
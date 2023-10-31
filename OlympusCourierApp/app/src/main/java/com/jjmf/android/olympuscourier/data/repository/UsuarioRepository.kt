package com.jjmf.android.olympuscourier.data.repository

import com.jjmf.android.olympuscourier.domain.model.Usuario

interface UsuarioRepository {
    suspend fun getList(): List<Usuario>
}
package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.data.server.dto.UsuarioDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Usuario
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb:CollectionReference
) : UsuarioRepository {
    override suspend fun getList(): List<Usuario> {
        val call = fb.get().await()
        val list = mutableListOf<Usuario>()
        call.documents.forEach {
            val obj = it.toObject(UsuarioDto::class.java)
            //obj?.id = it.id
            obj?.let { dto->
                list.add(dto.toDomain())
            }
        }
        return list
    }

}
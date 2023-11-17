package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.server.dto.UsuarioDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.data.server.ApiInterface
import com.jjmf.android.olympuscourier.data.server.dto.LoginRequest
import com.jjmf.android.olympuscourier.domain.model.Usuario
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb:CollectionReference,
    private val api: ApiInterface
) : UsuarioRepository {
    override suspend fun login(doc:String, clave:String): EstadosResult<Int> {
        return try {
            val request = LoginRequest(
                documento = doc,
                clave = clave
            )
            val call = api.login(request)
            if (call.isSuccessful){
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data)
                else EstadosResult.Error(call.body()?.mensaje.toString())
            }else EstadosResult.Error(call.message())
        }catch (e:Exception){
            EstadosResult.Error(e.message.toString())
        }
    }

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

    override suspend fun get(id: Int): EstadosResult<Usuario> {
        return try {
            val call = api.getUsuario(id)
            if (call.isSuccessful){
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.data?.toDomain())
                else EstadosResult.Error(call.body()?.mensaje.toString())
            }else EstadosResult.Error(call.message())
        }catch (e:Exception){
            EstadosResult.Error(e.message.toString())
        }
    }

}
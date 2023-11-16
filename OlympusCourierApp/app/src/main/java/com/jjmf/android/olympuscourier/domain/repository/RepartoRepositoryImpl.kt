package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.server.dto.RepartoDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.ClienteRepository
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.data.server.ApiInterface
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepartoRepositoryImpl @Inject constructor(
    @FirebaseModule.RepartoCollection private val fb: CollectionReference,
    private val api: ApiInterface
) : RepartoRepository {
    override suspend fun listarRepartos(): EstadosResult<List<Reparto>> {
        return try {
            val call = api.listarRepartos()
            return if (call.isSuccessful){
                val body = call.body()
                if (body!=null) EstadosResult.Correcto(body.map { it.toDomain() })
                else EstadosResult.Error("Sin Data")
            }else EstadosResult.Error(call.message())
        }catch (e:Exception){
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun getAll(): Flow<List<RepartoDto>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<RepartoDto>()
            sna?.forEach {
                val product = it.toObject(RepartoDto::class.java)
                lista.add(product)
            }
            trySend(lista).isSuccess
        }
        awaitClose { listado.remove() }
    }

    override suspend fun get(idReparto: Int): Reparto? {
        return api.listarRepartos().body()?.find { it.id == idReparto }?.toDomain()
    }

    override suspend fun update(idReparto: String) {
        fb.document(idReparto)
            .update("estado", "E", "fechaEntrega", Timestamp.now(), "idRepartidor", prefs.getUserId())
            .await()
    }

}
package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.data.firebase.RepartoDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.ClienteRepository
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepartoRepositoryImpl @Inject constructor(
    @FirebaseModule.RepartoCollection private val fb: CollectionReference,
    private val repoCliente: ClienteRepository,
) : RepartoRepository {
    override suspend fun getAll(): Flow<List<RepartoDto>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<RepartoDto>()
            sna?.forEach {
                val product = it.toObject(RepartoDto::class.java)
                product.id = it.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }
        awaitClose { listado.remove() }
    }

    override suspend fun get(idReparto: String): Reparto? {
        val doc = fb.document(idReparto).get().await()
        val reparto = doc.toObject(RepartoDto::class.java)
        reparto?.id = doc.id
        val cliente = repoCliente.getById(reparto?.idCliente ?: "")
        return reparto?.toDomain(cliente?.toDomain())
    }

    override suspend fun update(idReparto: String) {
        fb.document(idReparto)
            .update("estado", "E", "fechaEntrega", Timestamp.now(), "idRepartidor", prefs.getUserId())
            .await()
    }

}
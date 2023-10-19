package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.data.firebase.RepartoDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepartoRepositoryImpl @Inject constructor(
    @FirebaseModule.RepartoCollection private val fb: CollectionReference,
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

}
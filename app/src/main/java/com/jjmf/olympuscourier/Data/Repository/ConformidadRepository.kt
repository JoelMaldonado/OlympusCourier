package com.jjmf.olympuscourier.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Module.FirebaseModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConformidadRepository {

    suspend fun getListFlow() : Flow<List<Conformidad>>

    suspend fun insert(conformidad: Conformidad)

}

class ConformidadRepositoryImpl @Inject constructor(
    @FirebaseModule.ConformidadCollection private val fb: CollectionReference
) : ConformidadRepository {
    override suspend fun getListFlow(): Flow<List<Conformidad>> = callbackFlow{
        val listado = fb.addSnapshotListener{sna, _->
            val lista = mutableListOf<Conformidad>()
            for (i in sna!!.documents) {
                val product = i.toObject(Conformidad::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }
        awaitClose{listado.remove()}
    }

    override suspend fun insert(conformidad: Conformidad){
        fb.add(conformidad)
    }

}
package com.jjmf.olympuscourier.Data.Repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Module.FirebaseModule
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.olympuscourier.util.toMinusculas
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConformidadRepository {

    suspend fun getListFlow(soloVigente: Boolean = true): Flow<List<Conformidad>>
    suspend fun getList(): List<Conformidad>
    suspend fun getCorrelativo(): Int

    suspend fun insert(conformidad: Conformidad)
    suspend fun update(conformidad: Conformidad): EstadosResult<String>

}

class ConformidadRepositoryImpl @Inject constructor(
    @FirebaseModule.ConformidadCollection private val fb: CollectionReference
) : ConformidadRepository {
    override suspend fun getListFlow(soloVigente: Boolean): Flow<List<Conformidad>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<Conformidad>()
            for (i in sna!!.documents) {
                val product = i.toObject(Conformidad::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            if (soloVigente) {
                trySend(lista.filter { it.vigente == true }).isSuccess
            } else {
                trySend(lista).isSuccess
            }
        }
        awaitClose { listado.remove() }
    }

    override suspend fun getList(): List<Conformidad> {
        val list = fb.get().await().documents.map {
            val conf = it.toObject(Conformidad::class.java)
            conf?.id = it.id
            conf
        }
        return list.filterNotNull()
    }

    override suspend fun getCorrelativo(): Int {
        return try {
            (getList().maxBy { it.codigo ?: 0 }.codigo ?: 0) + 1
        } catch (e: Exception) { 1 }
    }

    override suspend fun insert(conformidad: Conformidad) {
        val user = prefs.getUser()!!
        fb.add(
            conformidad.copy(
                vigente = true,
                idUsuarioInsert = user.id,
                usuarioInsert = user.nombres.toMinusculas().split(" ").first() + " " + user.apePaterno.toMinusculas()
            )
        )
    }

    override suspend fun update(conformidad: Conformidad): EstadosResult<String> {
        return if (conformidad.id != null) {
            val user = prefs.getUser()!!
            fb.document(conformidad.id!!).set(
                conformidad.copy(
                    idUsuarioDelete = user.id,
                    usuarioDelete = user.nombres.toMinusculas().split(" ").first() + " " + user.apePaterno.toMinusculas(),
                    fechaDelete = Timestamp.now()
                )
            ).await()
            EstadosResult.Correcto("Actualizado Correctamente")
        } else EstadosResult.Error("ID no encontrado")
    }

}
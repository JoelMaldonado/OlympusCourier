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
import com.jjmf.android.olympuscourier.data.server.DarConformidadRequest
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepartoRepositoryImpl @Inject constructor(
    @FirebaseModule.RepartoCollection private val fb: CollectionReference,
    private val api: ApiInterface,
) : RepartoRepository {
    override suspend fun listarRepartos(): EstadosResult<List<Reparto>> {
        return try {
            val call = api.listarRepartos()
            return if (call.isSuccessful) {
                val body = call.body()
                if (body != null) EstadosResult.Correcto(body.map { it.toDomain() })
                else EstadosResult.Error("Sin Data")
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun get(idReparto: Int): EstadosResult<Reparto> {
        return try {
            val call = api.getReparto(idReparto)
            if (call.isSuccessful) {
                if (call.body() != null) EstadosResult.Correcto(call.body()?.toDomain())
                else EstadosResult.Error("No se encontro")
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

    override suspend fun update(idReparto: String) {
        fb.document(idReparto)
            .update(
                "estado",
                "E",
                "fechaEntrega",
                Timestamp.now(),
                "idRepartidor",
                prefs.getUserId()
            )
            .await()
    }

    override suspend fun darConformidad(idReparto: Int, urlFoto: String): EstadosResult<String> {
        return try {
            val request = DarConformidadRequest(
                id_reparto = idReparto,
                url_foto = urlFoto,
                id_usuario = prefs.getUserId()
                )
            val call = api.darConformidad(request)
            if (call.isSuccessful) {
                if (call.body()?.isSuccess == true) EstadosResult.Correcto(call.body()?.mensaje)
                else EstadosResult.Error(call.body()?.mensaje.toString())
            } else EstadosResult.Error(call.message())
        } catch (e: Exception) {
            EstadosResult.Error(e.message.toString())
        }
    }

}
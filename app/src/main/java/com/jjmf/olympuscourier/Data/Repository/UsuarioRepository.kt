package com.jjmf.olympuscourier.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.Data.Module.FirebaseModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UsuarioRepository {

    suspend fun getListFlow(): Flow<List<Usuario>>
    suspend fun getList(): List<Usuario>

    suspend fun insert(usuario: Usuario) : EstadosResult<String>
    suspend fun login(documento: String, clave: String): EstadosResult<Usuario>
    suspend fun delete(id: String)
}

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb: CollectionReference
) : UsuarioRepository {
    override suspend fun getListFlow(): Flow<List<Usuario>> = callbackFlow {
        val listado = fb.addSnapshotListener { sna, _ ->
            val lista = mutableListOf<Usuario>()
            for (i in sna!!.documents) {
                val product = i.toObject(Usuario::class.java)
                product!!.id = i.id
                lista.add(product)
            }
            trySend(lista).isSuccess
        }
        awaitClose { listado.remove() }
    }

    override suspend fun getList(): List<Usuario> {
        val list = fb.get().await().documents.map {
            val user = it.toObject(Usuario::class.java)
            user?.id = it.id
            user
        }
        return list.filterNotNull()
    }

    override suspend fun insert(usuario: Usuario) : EstadosResult<String>{
        val list = getList()
        return if (usuario.documento !in list.map { it.documento }){
            fb.add(usuario).await()
            EstadosResult.Correcto("Usuario Registrado")
        }else{
            EstadosResult.Error("Documento Duplicado")
        }
    }

    override suspend fun login(documento: String, clave: String): EstadosResult<Usuario> {
        val user = getList().find { it.documento == documento && it.clave == clave }
        return if (user != null) {
            EstadosResult.Correcto(datos = user)
        } else EstadosResult.Error(mensajeError = "Usuario y/o contraseñas son invalidas", codigoError = 1)
    }

    override suspend fun delete(id: String) {
        fb.document(id).delete().await()
    }

}
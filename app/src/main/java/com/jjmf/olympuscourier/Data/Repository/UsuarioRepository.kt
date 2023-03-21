package com.jjmf.olympuscourier.Data.Repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.Data.Module.FirebaseModule
import javax.inject.Inject

interface UsuarioRepository {
    fun insert(usuario: Usuario)
}

class UsuarioRepositoryImpl @Inject constructor(
    @FirebaseModule.UsuarioCollection private val fb: CollectionReference
) : UsuarioRepository{
    override fun insert(usuario: Usuario) {
        fb.add(usuario)
    }

}
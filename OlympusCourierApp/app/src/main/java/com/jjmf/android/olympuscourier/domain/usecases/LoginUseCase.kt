package com.jjmf.android.olympuscourier.domain.usecases

import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UsuarioRepository,
) {
    suspend operator fun invoke(doc: String, clave: String): Boolean {
        return repository.getList().find { it.doc == doc && it.clave == clave }.also {
            if (it != null) {
                prefs.saveUserId(it.id)
            }
        } != null
    }
}
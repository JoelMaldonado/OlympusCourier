package com.jjmf.olympuscourier.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var loaderAlert by mutableStateOf(false)
    var documento by mutableStateOf(prefs.getDocumentoLogin())
    var clave by mutableStateOf("")
    var check by mutableStateOf(prefs.getRecuerdame())

    var loader by mutableStateOf(false)
    var toMenu by mutableStateOf(false)
    var cambiarClave by mutableStateOf<Usuario?>(null)
    var mensajeError by mutableStateOf<String?>(null)

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            loader = true
            val wrap = repository.login(
                documento = documento,
                clave = clave
            )
            when (wrap) {
                is EstadosResult.Correcto -> {
                    loader = false
                    prefs.saveUser(wrap.datos!!)

                    prefs.saveDocumentoLogin(if (check) documento else "")
                    prefs.saveRecuerdame(check)

                    if (clave == "1234") {
                        cambiarClave = wrap.datos
                    } else {
                        toMenu = true
                    }

                }
                is EstadosResult.Error -> {
                    loader = false
                    mensajeError = wrap.mensajeError
                }
            }
        }
    }

    fun setNewClave(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            loaderAlert = true
            try {
                when (val wrap = repository.update(usuario = usuario)) {
                    is EstadosResult.Correcto -> {
                        toMenu = true
                        cambiarClave = null
                        loaderAlert = false
                    }
                    is EstadosResult.Error -> {
                        mensajeError = wrap.mensajeError
                        loaderAlert = false
                    }
                }
            } catch (e: Exception) {
                mensajeError = e.message
                loaderAlert = false
            }
        }
    }
}
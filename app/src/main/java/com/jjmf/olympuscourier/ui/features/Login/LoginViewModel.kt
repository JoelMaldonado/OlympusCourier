package com.jjmf.olympuscourier.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel(){

    var documento by mutableStateOf(prefs.getDocumentoLogin())
    var clave by mutableStateOf("11101999")
    var check by mutableStateOf(prefs.getRecuerdame())

    var loader by mutableStateOf(false)
    var toMenu by mutableStateOf(false)
    var mensajeError by mutableStateOf<String?>(null)

    fun login() {
        viewModelScope.launch(Dispatchers.IO){
            loader = true
            val wrap = repository.login(
                documento = documento,
                clave = clave
            )
            when(wrap){
                is EstadosResult.Correcto -> {
                    loader = false
                    prefs.saveUser(wrap.datos!!)
                    prefs.saveDocumentoLogin(if (check) documento else "")
                    prefs.saveRecuerdame(check)
                    toMenu = true

                }
                is EstadosResult.Error -> {
                    loader = false
                    mensajeError = wrap.mensajeError
                }
            }
        }
    }
}
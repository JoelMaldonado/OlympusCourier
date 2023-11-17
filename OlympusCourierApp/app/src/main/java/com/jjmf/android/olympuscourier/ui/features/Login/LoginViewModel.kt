package com.jjmf.android.olympuscourier.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var documento by mutableStateOf(prefs.getDoc() ?: "")
    var clave by mutableStateOf("")
    var check by mutableStateOf(prefs.getRecordar())

    var toMenu by mutableStateOf(false)
    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)


    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                when(val res = repository.login(documento, clave)){
                    is EstadosResult.Correcto -> {
                        if (res.datos!=null){
                            if (check) prefs.saveDoc(documento)
                            else prefs.removeDoc()
                            prefs.saveRecordar(check)
                            prefs.saveUserId(res.datos)
                            toMenu = true
                        }
                    }
                    is EstadosResult.Error -> {
                        error = res.mensajeError
                    }
                }
            } catch (e: Exception) {
                error = e.message
            }finally {
                loader = false
            }
        }
    }

}
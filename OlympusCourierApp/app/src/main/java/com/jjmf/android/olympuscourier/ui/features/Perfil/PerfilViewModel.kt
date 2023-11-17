package com.jjmf.android.olympuscourier.ui.features.Perfil

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var usuario by mutableStateOf<Usuario?>(null)
    var error by mutableStateOf<String?>(null)
    var loader by mutableStateOf(false)

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                loader = true
                when(val res = repository.get(prefs.getUserId())){
                    is EstadosResult.Correcto -> usuario = res.datos
                    is EstadosResult.Error -> error = res.mensajeError
                }
            }catch (e:Exception){
                error = e.message
            }finally {
                loader = false
            }
        }
    }

}
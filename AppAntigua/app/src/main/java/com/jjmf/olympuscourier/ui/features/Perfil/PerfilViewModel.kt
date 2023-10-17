package com.jjmf.olympuscourier.ui.features.Perfil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PerfilViewModel  @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel(){

    var loader by mutableStateOf(false)
    var logout by mutableStateOf(false)

    fun eliminarCuenta(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            loader = true
            try {
                repository.delete(id)
                loader = false
                logout = true
            }catch (e:Exception){
                loader = false
            }
        }
    }

}
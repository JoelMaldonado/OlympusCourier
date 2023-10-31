package com.jjmf.android.olympuscourier.ui.features.DatosPersonales

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatosPersonalesViewModel @Inject constructor(
    private val repository: UsuarioRepository,
) : ViewModel() {
    fun init() {
        viewModelScope.launch {
            try {
                user = repository.getList().find { it.id == prefs.getUserId() }
            }catch (e:Exception){

            }
        }
    }

    var user by mutableStateOf<Usuario?>(null)
    var loader by mutableStateOf(false)
}
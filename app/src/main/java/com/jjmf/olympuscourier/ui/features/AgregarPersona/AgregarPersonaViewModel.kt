package com.jjmf.olympuscourier.ui.features.AgregarPersona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Core.Reniec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarPersonaViewModel @Inject constructor(

) : ViewModel() {

    var loader by mutableStateOf(false)

    var documento by mutableStateOf("")
    var nombres by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var direccion by mutableStateOf("")
    var celular by mutableStateOf("")
    var rol by mutableStateOf("")

    fun reniec() {
        viewModelScope.launch(Dispatchers.IO) {
            loader = true
            val call = Reniec().getDocumento().get(documento)
            if (call.isSuccessful) {
                loader = false
                val body = call.body()!!
                nombres = body.nombres
                apellidos = body.apellidoPaterno + " " + body.apellidoMaterno
            }else{
                loader = false
            }
        }
    }

}
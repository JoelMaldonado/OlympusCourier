package com.jjmf.olympuscourier.ui.features.ConformidadEntrega

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.olympuscourier.Core.Reniec
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConformidadEntregaViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    var loader by mutableStateOf(false)
    var back by mutableStateOf(false)

    var documento by mutableStateOf("")
    var nombres by mutableStateOf("")
    var direccion by mutableStateOf("")
    var celular by mutableStateOf("")

    fun reniec() {
        viewModelScope.launch(Dispatchers.IO) {
            loader = true
            val call = Reniec().getDocumento().get(documento)
            if (call.isSuccessful) {
                loader = false
                val body = call.body()!!
                nombres = body.nombre
            }else{
                loader = false
            }
        }
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.IO){
            val conformidad = Conformidad(
                documento = documento,
                fullName = nombres,
                direccion = direccion,
                latitud = 0.0,
                longitud = 0.0,
                celular = celular,
                time = Timestamp.now()
            )
            repository.insert(conformidad)
            back = true
        }
    }
}
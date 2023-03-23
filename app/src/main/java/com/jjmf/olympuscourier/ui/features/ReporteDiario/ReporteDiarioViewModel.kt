package com.jjmf.olympuscourier.ui.features.ReporteDiario

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.getFecha
import com.jjmf.olympuscourier.util.toFecha
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteDiarioViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    var cont by mutableStateOf(0)
    var listado by mutableStateOf<List<Conformidad>>(emptyList())
    var listadoDias by mutableStateOf<List<Conformidad>>(emptyList())
    var listadoMeses by mutableStateOf<List<Conformidad>>(emptyList())

    var fechaSelected by mutableStateOf(getFecha("dd/MM/yyyy"))
    var mesSelected by mutableStateOf(getFecha("MM/yyyy"))

    var progressDias by mutableStateOf(false)
    var progress by mutableStateOf(false)

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            val listaDias = mutableListOf<Conformidad>()
            repository.getList().forEach {conformidad->
                if (conformidad.time?.toFecha().format() !in listaDias.map {
                        it.time?.toFecha().format()
                    }
                ){
                    listaDias.add(conformidad)
                }
            }
            listadoDias = listaDias.filter { it.time?.toFecha()?.format("MM/yyyy") == mesSelected }
            val listaMeses = mutableListOf<Conformidad>()
            listaDias.forEach {conformidad->
                if (conformidad.time?.toFecha().format("MM/yyyy") !in listaMeses.map {
                        it.time?.toFecha().format("MM/yyyy")
                    }
                ){
                    listaMeses.add(conformidad)
                }
            }
            listadoMeses = listaMeses.sortedByDescending { it.time }
        }
    }

    fun getListConformidad() {
        viewModelScope.launch(Dispatchers.IO){
            progress = true
            listado = repository.getList().filter { it.time.toFecha().format() == fechaSelected }
            progress = false
        }
    }

    fun getListConformidadDias() {
        viewModelScope.launch(Dispatchers.IO){
            progressDias = true
            val listaDias = mutableListOf<Conformidad>()
            repository.getList().forEach {conformidad->
                if (conformidad.time?.toFecha().format() !in listaDias.map {
                        it.time?.toFecha().format()
                    }
                ){
                    listaDias.add(conformidad)
                }
            }
            listadoDias = listaDias.filter { it.time?.toFecha()?.format("MM/yyyy") == mesSelected }
            progressDias = false
        }
    }
}
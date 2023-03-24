package com.jjmf.olympuscourier.ui.features.ReporteDiario

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.Data.Usecase.GetListDiario
import com.jjmf.olympuscourier.Data.Usecase.GetListMensual
import com.jjmf.olympuscourier.util.Fecha
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.toFecha
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReporteDiarioViewModel @Inject constructor(
    private val repository: ConformidadRepository,
    private val getListDiario: GetListDiario,
    private val getListMensual: GetListMensual
) : ViewModel() {

    var fechaSelected by mutableStateOf("")

    var listDias by mutableStateOf<List<Fecha>>(emptyList())

    var listMeses by mutableStateOf<List<Fecha>>(emptyList())

    var cont by mutableStateOf(0)

    var listConformidad by mutableStateOf<List<Conformidad>>(emptyList())


    fun getListDias() {
        viewModelScope.launch(Dispatchers.IO) {
            listDias = getListDiario().filter { it.mes == listMeses[cont].mes && it.anio == listMeses[cont].anio }
        }
    }

    fun getListConformidad() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListFlow(false).collect() { list ->
                listConformidad = list.filter {
                    val fecha = it.time?.toFecha()
                    fecha.format() == fechaSelected
                }
            }
        }
    }

    var mensajeError by mutableStateOf<String?>(null)
    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listMeses = getListMensual()
                listDias = getListDiario().filter { it.mes == listMeses[cont].mes }
                cont = listMeses.size - 1
                delay(1000)
                fechaSelected = listDias.last().format()
                getListConformidad()
            }catch (e:Exception){
                mensajeError = e.message
            }
        }
    }
}
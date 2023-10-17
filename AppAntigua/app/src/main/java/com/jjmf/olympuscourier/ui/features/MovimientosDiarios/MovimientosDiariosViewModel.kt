package com.jjmf.olympuscourier.ui.features.MovimientosDiarios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.util.getFecha
import com.jjmf.olympuscourier.util.toFecha
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovimientosDiariosViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    var listado by mutableStateOf<List<Conformidad>>(emptyList())

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListFlow().collect() {
                listado = it.filter { c ->
                    val hoy = c.time?.toFecha()
                    val fb = "${hoy?.diaNum}/${hoy?.mes}/${hoy?.anio}"
                    val fec = getFecha("dd/MM/yyyy")
                    fb == fec
                }
            }
        }
    }

}
package com.jjmf.olympuscourier.ui.features.MovimientosDiarios

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovimientosDiariosViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    var listado by mutableStateOf<List<Conformidad>>(emptyList())

    fun getList() {
        viewModelScope.launch(Dispatchers.IO){
            repository.getListFlow().collect(){
                listado = it
            }
        }
    }

}
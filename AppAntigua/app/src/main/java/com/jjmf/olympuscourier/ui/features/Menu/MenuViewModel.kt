package com.jjmf.olympuscourier.ui.features.Menu

import androidx.lifecycle.ViewModel
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    fun verificar() = flow{
        emit(repository.getList().isEmpty())
    }

}
package com.jjmf.android.olympuscourier.core

sealed class EstadosResult<out T>{
    object Cargando : EstadosResult<Nothing>()
    data class Correcto<T> (val datos:T? ) : EstadosResult<T>()
    data class Error(val mensajeError :String) : EstadosResult<Nothing>()
}
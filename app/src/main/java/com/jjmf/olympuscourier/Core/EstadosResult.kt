package com.jjmf.olympuscourier.Core



sealed class EstadosResult<out T>{
    //COMPLETE
    data class Correcto<T> (val datos:T? ) : EstadosResult<T>()

    //FAILURE
    data class Error(val mensajeError :String, val codigoError : Int? = null) : EstadosResult<Nothing>()
}
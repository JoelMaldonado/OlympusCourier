package com.jjmf.olympuscourier.Data.Usecase

import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.util.Fecha
import com.jjmf.olympuscourier.util.toFecha
import javax.inject.Inject

class GetListMensual @Inject constructor(
    private val repository: ConformidadRepository
){
  suspend operator fun invoke() : List<Fecha>{
      val listado = repository.getList()
      val list2 = listado.map {
          it.time?.toFecha()
      }
      val list3 =  list2.requireNoNulls()
      return list3.distinctBy {
          it.mes+it.anio
      }.sortedBy { it.date }
  }
}
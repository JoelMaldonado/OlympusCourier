package com.jjmf.android.olympuscourier.data.server

import com.jjmf.android.olympuscourier.data.server.dto.RepartoDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/repartos")
    suspend fun listarRepartos() : Response<List<RepartoDto>>

}
package com.jjmf.android.olympuscourier.data.server

import com.jjmf.android.olympuscourier.data.server.dto.LoginRequest
import com.jjmf.android.olympuscourier.data.server.dto.RepartoDto
import com.jjmf.android.olympuscourier.data.server.dto.UsuarioDto
import com.jjmf.android.olympuscourier.data.server.dto.Wrapper
import com.jjmf.android.olympuscourier.domain.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/repartos")
    suspend fun listarRepartos() : Response<List<RepartoDto>>

    @GET("api/usuarios/get/{id}")
    suspend fun getUsuario(@Path("id") id:Int) : Response<Wrapper<UsuarioDto>>

    @POST("api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<Wrapper<Int>>

}
package com.jjmf.olympuscourier.Core

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Reniec {
    fun getDocumento() : ReniecInterface {
        return Retrofit.Builder()
            .baseUrl("https://api.apis.net.pe/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReniecInterface::class.java)
    }

    interface ReniecInterface{

        @GET("dni")
        suspend fun get(@Query("numero") documento:String) : Response<DocumentoReniec>
    }

    data class DocumentoReniec(
        @SerializedName("nombres") val nombres:String,
        @SerializedName("numeroDocumento") val numeroDocumento:String,
        @SerializedName("apellidoPaterno") val apellidoPaterno:String,
        @SerializedName("apellidoMaterno") val apellidoMaterno:String
    )
}
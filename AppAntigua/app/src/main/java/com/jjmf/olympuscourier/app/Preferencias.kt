package com.jjmf.olympuscourier.app

import android.content.Context
import com.google.gson.Gson
import com.jjmf.olympuscourier.Data.Model.Usuario

class Preferencias(context: Context) {

    private val SHARED_NAME = "MYDTB"
    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    private val KEY_DOCUMENTO_LOGIN = "KEY_DOCUMENTO_LOGIN"
    private val KEY_RECUERDAME = "KEY_RECUERDAME"
    private val KEY_USUARIO = "KEY_USUARIO"


    fun saveDocumentoLogin(valor: String) = storage.edit().putString(KEY_DOCUMENTO_LOGIN, valor).apply()
    fun getDocumentoLogin() = storage.getString(KEY_DOCUMENTO_LOGIN, "") ?: ""

    fun saveRecuerdame(valor: Boolean) = storage.edit().putBoolean(KEY_RECUERDAME, valor).apply()
    fun getRecuerdame() = storage.getBoolean(KEY_RECUERDAME, false)

    fun saveUser(valor: Usuario) {
        val json = Gson().toJson(valor)
        storage.edit().putString(KEY_USUARIO, json).apply()
    }
    fun getUser() : Usuario?{
        val user = storage.getString(KEY_USUARIO, null)
        return if ((user!=null)){
            Gson().fromJson(user, Usuario::class.java)
        }else null
    }
    fun clearUser(){
        storage.edit().remove(KEY_USUARIO).apply()
    }
}
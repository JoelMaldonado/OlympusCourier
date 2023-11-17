package com.jjmf.android.olympuscourier.app

import android.content.Context

class Preferencias(context: Context) {
    private val SHARED_NAME = "MYDTB"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    private val KEY_DOC = "KEY_DOC"
    private val KEY_RECORDAR = "KEY_RECORDAR"
    private val KEY_USER_ID = "KEY_USER_ID"


    fun saveDoc(valor: String) = storage.edit().putString(KEY_DOC, valor).apply()
    fun getDoc() = storage.getString(KEY_DOC, null)
    fun removeDoc() = storage.edit().remove(KEY_DOC).apply()

    fun saveRecordar(valor: Boolean) = storage.edit().putBoolean(KEY_RECORDAR, valor).apply()
    fun getRecordar() = storage.getBoolean(KEY_RECORDAR, false)

    fun saveUserId(valor: Int) = storage.edit().putInt(KEY_USER_ID, valor).apply()
    fun getUserId() = storage.getInt(KEY_USER_ID, 0)
}
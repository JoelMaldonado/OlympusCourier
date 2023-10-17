package com.jjmf.olympuscourier.Data.Module

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase() = FirebaseFirestore.getInstance()

    @ConformidadCollection
    @Provides
    @Singleton
    fun provideConformidad() = provideFirebase().collection(FB_CONFORMIDAD)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ConformidadCollection

    @UsuarioCollection
    @Provides
    @Singleton
    fun provideUsuario() = provideFirebase().collection(FB_USUARIO)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UsuarioCollection

}

const val FB_CONFORMIDAD = "Conformidad"
const val FB_USUARIO = "Usuario"
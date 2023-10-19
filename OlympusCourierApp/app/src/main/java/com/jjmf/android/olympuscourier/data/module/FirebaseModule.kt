package com.jjmf.android.olympuscourier.data.module

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

    @ClienteCollection
    @Provides
    @Singleton
    fun provideCliente() = provideFirebase().collection("Cliente")

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ClienteCollection

    @RepartoCollection
    @Provides
    @Singleton
    fun provideReparto() = provideFirebase().collection("Reparto")

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RepartoCollection

}
package com.jjmf.olympuscourier.Data.Module

import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepositoryImpl
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun conformidadRepo(repo: ConformidadRepositoryImpl): ConformidadRepository
    @Binds
    abstract fun usuarioRepo(repo: UsuarioRepositoryImpl): UsuarioRepository

}
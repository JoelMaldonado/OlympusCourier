package com.jjmf.android.olympuscourier.data.module

import com.jjmf.android.olympuscourier.data.repository.ClienteRepository
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.repository.ClienteRepositoryImpl
import com.jjmf.android.olympuscourier.domain.repository.RepartoRepositoryImpl
import com.jjmf.android.olympuscourier.domain.repository.UsuarioRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun clienteRepo(impl: ClienteRepositoryImpl): ClienteRepository

    @Binds
    abstract fun repartoRepo(impl: RepartoRepositoryImpl): RepartoRepository

    @Binds
    abstract fun usuarioRepo(impl: UsuarioRepositoryImpl): UsuarioRepository

}
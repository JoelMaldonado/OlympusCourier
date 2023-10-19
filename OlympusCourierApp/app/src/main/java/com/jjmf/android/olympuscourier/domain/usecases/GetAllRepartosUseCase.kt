package com.jjmf.android.olympuscourier.domain.usecases

import com.jjmf.android.olympuscourier.data.repository.ClienteRepository
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllRepartosUseCase @Inject constructor(
    private val repository: RepartoRepository,
    private val repoCliente: ClienteRepository,
) {
    suspend operator fun invoke(): Flow<List<Reparto>> {
        return repository.getAll().map { listDto ->
            listDto.map { dto ->
                val cliente = repoCliente.getById(dto.idCliente ?: "")
                dto.toDomain(cliente?.toDomain())
            }
        }
    }
}
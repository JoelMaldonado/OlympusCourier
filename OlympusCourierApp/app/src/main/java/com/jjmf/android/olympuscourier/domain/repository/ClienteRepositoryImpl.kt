package com.jjmf.android.olympuscourier.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.jjmf.android.olympuscourier.data.server.dto.ClienteDto
import com.jjmf.android.olympuscourier.data.module.FirebaseModule
import com.jjmf.android.olympuscourier.data.repository.ClienteRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClienteRepositoryImpl @Inject constructor(
    @FirebaseModule.ClienteCollection private val fb: CollectionReference,
) : ClienteRepository {
    override suspend fun getById(id: String): ClienteDto? {
        return fb.document(id).get().await().toObject(ClienteDto::class.java)
    }


}
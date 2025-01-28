package com.example.pamtugasakhir_158.repository

import com.example.pamtugasakhir_158.model.AllProyekResponse
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.service.ProyekService

interface ProyekRepository {

    suspend fun getAllProyek(): AllProyekResponse

    suspend fun getProyekByID(idProyek: String): Proyek

    suspend fun insertProyek(proyek: Proyek)

    suspend fun updateProyek(idProyek: String, proyek: Proyek)

    suspend fun deleteProyek(idProyek: String)
}

class NetworkProyekRepository(
    private val proyekApiService: ProyekService
) : ProyekRepository {

    override suspend fun getAllProyek(): AllProyekResponse =
        proyekApiService.getProyek()

    override suspend fun getProyekByID(idProyek: String): Proyek {
        return proyekApiService.getProyekByID(idProyek).data
    }

    override suspend fun insertProyek(proyek: Proyek) {
        proyekApiService.insertProyek(proyek)
    }

    override suspend fun updateProyek(idProyek: String, proyek: Proyek) {
        proyekApiService.updateProyek(idProyek, proyek)
    }

    override suspend fun deleteProyek(idProyek: String) {
        try {
            val response = proyekApiService.deleteProyek(idProyek)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete proyek. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

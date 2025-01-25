package com.example.pamtugasakhir_158.repository

import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.service.TimService


interface TimRepository {

    suspend fun getAllTim(): List<Tim>

    suspend fun getTimByID(id_tim: String): Tim

    suspend fun insertTim(tim: Tim)

    suspend fun updateTim(id_tim: String, tim: Tim)

    suspend fun deleteTim(id_tim: String)
}

class NetworkTimRepository(
    private val timApiService: TimService
) : TimRepository {

    override suspend fun getAllTim(): List<Tim> =
        timApiService.getAllTim()

    override suspend fun getTimByID(id_tim: String): Tim {
        return timApiService.getTimByID(id_tim)
    }

    override suspend fun insertTim(tim: Tim) {
        timApiService.insertTim(tim)
    }

    override suspend fun updateTim(id_tim: String, tim: Tim) {
        timApiService.updateTim(id_tim, tim)
    }

    override suspend fun deleteTim(id_tim: String) {
        try {
            val response = timApiService.deleteTim(id_tim)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete tim. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

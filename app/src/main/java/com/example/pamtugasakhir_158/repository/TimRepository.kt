package com.example.pamtugasakhir_158.repository
import com.example.pamtugasakhir_158.model.AllTimResponse
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.service.TimService


interface TimRepository {

    suspend fun getAllTim(): AllTimResponse

    suspend fun getTimByID(idTim: String): Tim

    suspend fun insertTim(tim: Tim)

    suspend fun updateTim(idTim: String, tim: Tim)

    suspend fun deleteTim(idTim: String)
}

class NetworkTimRepository(
    private val timApiService: TimService
) : TimRepository {

    override suspend fun getAllTim(): AllTimResponse =
        timApiService.getTim()

    override suspend fun getTimByID(idTim: String): Tim {
        return timApiService.getTimByID(idTim).data
    }

    override suspend fun insertTim(tim: Tim) {
        timApiService.insertTim(tim)
    }

    override suspend fun updateTim(idTim: String, tim: Tim) {
        timApiService.updateTim(idTim, tim)
    }

    override suspend fun deleteTim(idTim: String) {
        try {
            val response = timApiService.deleteTim(idTim)
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

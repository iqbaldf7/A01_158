package com.example.pamtugasakhir_158.repository

import com.example.pamtugasakhir_158.model.AnggotaTim
import com.example.pamtugasakhir_158.service.AnggotaService

interface AnggotaRepository {

    suspend fun getAllAnggota(): List<AnggotaTim>

    suspend fun getAnggotaByID(id_anggota: String): AnggotaTim

    suspend fun insertAnggota(anggota: AnggotaTim)

    suspend fun updateAnggota(id_anggota: String, anggota: AnggotaTim)

    suspend fun deleteAnggota(id_anggota: String)
}

class NetworkAnggotaRepository(
    private val anggotaApiService: AnggotaService
) : AnggotaRepository {

    override suspend fun getAllAnggota(): List<AnggotaTim> =
        anggotaApiService.getAllAnggota()

    override suspend fun getAnggotaByID(id_anggota: String): AnggotaTim {
        return anggotaApiService.getAnggotaByID(id_anggota)
    }

    override suspend fun insertAnggota(anggota: AnggotaTim) {
        anggotaApiService.insertAnggota(anggota)
    }

    override suspend fun updateAnggota(id_anggota: String, anggota: AnggotaTim) {
        anggotaApiService.updateAnggota(id_anggota, anggota)
    }

    override suspend fun deleteAnggota(id_anggota: String) {
        try {
            val response = anggotaApiService.deleteAnggota(id_anggota)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete anggota. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

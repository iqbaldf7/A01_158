package com.example.pamtugasakhir_158.repository

import com.example.pamtugasakhir_158.model.AnggotaTim
import com.example.pamtugasakhir_158.service.AnggotaService
import com.example.pamtugasakhir_158.ui.theme.viewmodel.anggotaTim.Anggota

interface AnggotaRepository {

    suspend fun getAllAnggota(): List<AnggotaTim>

    suspend fun getAnggotaByID(id_anggota: String): AnggotaTim

    suspend fun insertAnggota(anggota: Anggota)

    suspend fun updateAnggota(id_anggota: String, anggota: Anggota)

    suspend fun deleteAnggota(id_anggota: String)
}

class NetworkAnggotaRepository(
    private val anggotaApiService: AnggotaService
) : AnggotaRepository {

    override suspend fun getAllAnggota(): List<AnggotaTim> =
        anggotaApiService.getAllAnggota()

    override suspend fun getAnggotaByID(id_anggota: String): AnggotaTim =
        anggotaApiService.getAnggotaByID(id_anggota)

    override suspend fun insertAnggota(anggota: Anggota) {
        anggotaApiService.insertAnggota(anggota)
    }

    override suspend fun updateAnggota(id_anggota: String, anggota: Anggota) {
        anggotaApiService.updateAnggota(id_anggota, anggota)
    }

    override suspend fun deleteAnggota(id_anggota: String) {
        try {
            val response = anggotaApiService.deleteAnggota(id_anggota)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete anggota. HTTP Status code: ${response.code()}")
            } else {
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}

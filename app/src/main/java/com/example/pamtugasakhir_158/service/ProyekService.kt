package com.example.pamtugasakhir_158.service

import com.example.pamtugasakhir_158.model.AllProyekResponse
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.model.ProyekDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProyekService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("proyek/.") // Rute untuk mendapatkan semua proyek
    suspend fun getProyek(): AllProyekResponse

    @GET("proyek/{idproyek}") // Rute untuk mendapatkan proyek berdasarkan ID
    suspend fun getProyekByID(@Path("id_proyek") idProyek: String): ProyekDetailResponse

    @POST("proyek/proyek") // Rute untuk menambahkan proyek baru
    suspend fun insertProyek(@Body proyek: Proyek)

    @PUT("proyek/{id_proyek}") // Rute untuk mengedit proyek
    suspend fun updateProyek(@Path("id_proyek") idProyek: String, @Body proyek: Proyek)

    @DELETE("proyek/{id_proyek}") // Rute untuk menghapus proyek
    suspend fun deleteProyek(@Path("id_proyek") idProyek: String): retrofit2.Response<Void>
}

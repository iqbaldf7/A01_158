package com.example.pamtugasakhir_158.service

import com.example.pamtugasakhir_158.model.AnggotaTim
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AnggotaService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacaanggota.php")
    suspend fun getAllAnggota(): List<AnggotaTim>

    @GET("baca1anggota.php/{id_anggota}")
    suspend fun getAnggotaByID(@Query("id_anggota") id_anggota: String): AnggotaTim

    @POST("insertanggota.php")
    suspend fun insertAnggota(@Body anggota: AnggotaTim)

    @PUT("editanggota.php/{id_anggota}")
    suspend fun updateAnggota(@Query("id_anggota") id_anggota: String, @Body anggota: AnggotaTim)

    @DELETE("deleteanggota.php/{id_anggota}")
    suspend fun deleteAnggota(@Query("id_anggota") id_anggota: String): retrofit2.Response<Void>
}

package com.example.pamtugasakhir_158.service


import com.example.pamtugasakhir_158.model.AllTimResponse
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.model.TimDetailResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
interface TimService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("tim/.")
    suspend fun getTim(): AllTimResponse

    @GET("tim/{idtim}") // Pastikan rute untuk ID juga sesuai
    suspend fun getTimByID(@Path("idtim") idTim: String): TimDetailResponse

    @POST("tim/tim") // Rute untuk menambahkan tim
    suspend fun insertTim(@Body tim: Tim)

    @PUT("tim/{id_tim}") // Rute untuk mengedit tim
    suspend fun updateTim(@Path("id_tim") idTim: String, @Body tim: Tim)

    @DELETE("tim/{id_tim}") // Rute untuk menghapus tim
    suspend fun deleteTim(@Path("id_tim") idTim: String): retrofit2.Response<Void>
}
package com.example.pamtugasakhir_158.service


import com.example.pamtugasakhir_158.model.Tim
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TimService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacatim.php")
    suspend fun getAllTim(): List<Tim>

    @GET("baca1tim.php/{id_tim}")
    suspend fun getTimByID(@Query("id_tim") id_tim: String): Tim

    @POST("inserttim.php")
    suspend fun insertTim(@Body tim: Tim)
    @PUT("edittim.php/{id_tim}")
    suspend fun updateTim(@Query("id_tim") id_tim: String, @Body tim: Tim)

    @DELETE("deletetim.php/{id_tim}")
    suspend fun deleteTim(@Query("id_tim") id_tim: String): retrofit2.Response<Void>
}



package com.example.pamtugasakhir_158.depedenciesinjection

import com.example.pamtugasakhir_158.repository.NetworkTimRepository
import com.example.pamtugasakhir_158.repository.TimRepository
import com.example.pamtugasakhir_158.service.TimService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val timRepository: TimRepository
}
class ProyekContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:4000/api/mahasiswa/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    // Implementasi timRepository

    private val timService: TimService by lazy {
        retrofit.create(TimService::class.java)
    }
    override val timRepository: TimRepository by lazy {
        NetworkTimRepository(timService)
    }
}

package com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.ProyekRepository
import com.example.pamtugasakhir_158.repository.TimRepository
import kotlinx.coroutines.launch
import okio.IOException

class DetailProyekViewModelFactory(private val repository: ProyekRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailProyekViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailProyekViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailProyekViewModel(private val proyekRepository: ProyekRepository) : ViewModel() {

    // Mengambil data proyek berdasarkan idProyek
    fun getProyekById(idProyek: String, onResult: (Proyek?) -> Unit) {
        viewModelScope.launch {
            val proyek = try {
                proyekRepository.getProyekByID(idProyek)
            } catch (e: IOException) {
                null // Tangani error jika ada masalah koneksi
            } catch (e: HttpException) {
                null // Tangani error jika respons dari server salah
            }
            onResult(proyek)
        }
    }
}

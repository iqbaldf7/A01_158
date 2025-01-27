package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import kotlinx.coroutines.launch
import okio.IOException

class DetailTimViewModelFactory(private val repository: TimRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailTimViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailTimViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailTimViewModel(private val tim: TimRepository) : ViewModel() {

    // Mengambil data anggota berdasarkan id anggota
    fun getMahasiswabyNim(idTim: String, onResult: (Tim?) -> Unit) {
        viewModelScope.launch {
            val tim = try {
                tim.getTimByID(idTim)
            } catch (e: IOException) {
                null // Tangani error jika ada masalah koneksi
            } catch (e: HttpException) {
                null // Tangani error jika respons dari server salah
            }
            onResult(tim)
        }
    }


}
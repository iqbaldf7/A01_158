package com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.ProyekRepository
import com.example.pamtugasakhir_158.repository.TimRepository
import kotlinx.coroutines.launch
import java.io.IOException


sealed class HomeProyekUiState {
    data class Success(val proyek: List<Proyek>) : HomeProyekUiState()
    object Loading : HomeProyekUiState()
    object Error : HomeProyekUiState()
}

class HomeProyekViewModel(private val proyekRepository: ProyekRepository) : ViewModel() {
    var proyekUiState: HomeProyekUiState by mutableStateOf(HomeProyekUiState.Loading)
        private set

    init {
        getProyek()
    }

    fun getProyek() {
        viewModelScope.launch {
            proyekUiState = HomeProyekUiState.Loading
            proyekUiState = try {
                HomeProyekUiState.Success(proyekRepository.getAllProyek().data)
            } catch (e: IOException) {
                HomeProyekUiState.Error
            } catch (e: HttpException) {
                HomeProyekUiState.Error
            }
        }
    }

    fun deleteProyek(idProyek: String) {
        viewModelScope.launch {
            try {
                proyekRepository.deleteProyek(idProyek)
            } catch (e: IOException) {
                proyekUiState = HomeProyekUiState.Error
            } catch (e: HttpException) {
                proyekUiState = HomeProyekUiState.Error
            }
        }
    }
}

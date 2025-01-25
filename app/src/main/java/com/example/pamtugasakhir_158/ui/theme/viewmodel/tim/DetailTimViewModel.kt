package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim

import androidx.compose.runtime.getValue
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiDetailTim
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val tim: Tim) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailTimViewModel(
    savedStateHandle: SavedStateHandle,
    private val timRepository: TimRepository
) : ViewModel() {
    var timDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _idTim: String = checkNotNull(savedStateHandle[DestinasiDetailTim.TIM])

    init {
        getTimById()
    }

    fun getTimById() {
        viewModelScope.launch {
            timDetailState = DetailUiState.Loading
            timDetailState = try {
                val fetchedTim = timRepository.getTimByID(_idTim)
                DetailUiState.Success(fetchedTim)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    fun deleteTim() {
        viewModelScope.launch {
            try {
                timRepository.deleteTim(_idTim)
            } catch (e: IOException) {

            } catch (e: HttpException) {

            }
        }
    }
}

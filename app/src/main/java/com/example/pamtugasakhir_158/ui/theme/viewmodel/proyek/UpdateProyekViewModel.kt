package com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.repository.ProyekRepository
import com.example.pamtugasakhir_158.ui.theme.view.proyek.DestinasiUpdateProyek
import kotlinx.coroutines.launch

class UpdateProyekViewModel(
    savedStateHandle: SavedStateHandle,
    private val proyekRepository: ProyekRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertProyekUiState())
        private set

    private val _idProyek: String = checkNotNull(savedStateHandle[DestinasiUpdateProyek.ID_PROYEK]) { "ID Proyek tidak ditemukan" }


    init {
        viewModelScope.launch {
            val proyek = proyekRepository.getProyekByID(_idProyek)
            uiState = proyek.toUiStateProyek()
        }
    }

    fun updateProyek() {
        viewModelScope.launch {
            try {
                val proyek = uiState.insertUiEvent.toProyek()
                proyekRepository.updateProyek(_idProyek, proyek)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateState(insertUiEvent: InsertProyekUiEvent) {
        uiState = InsertProyekUiState(
            insertUiEvent = insertUiEvent
        )
    }

    // Konversi dari model Proyek ke UiState
    fun Proyek.toUiStateProyek(): InsertProyekUiState = InsertProyekUiState(
        insertUiEvent = toInsertProyekUiEvent()
    )

    // Konversi dari model Proyek ke InsertProyekUiEvent
    fun Proyek.toInsertProyekUiEvent(): InsertProyekUiEvent = InsertProyekUiEvent(
        idProyek = idProyek,
        namaProyek = namaProyek,
        deskripsiProyek = deskripsiProyek,
        tanggalMulai = tanggalMulai,
        tanggalBerakhir = tanggalBerakhir,
        statusProyek = statusProyek
    )
}

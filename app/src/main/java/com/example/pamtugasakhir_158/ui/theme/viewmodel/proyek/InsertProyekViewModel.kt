package com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.ProyekRepository
import com.example.pamtugasakhir_158.repository.TimRepository
import kotlinx.coroutines.launch

class InsertProyekViewModel(private val proyekRepository: ProyekRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertProyekUiState())

    fun updateProyekState(insertProyekUiEvent: InsertProyekUiEvent) {
        uiState = InsertProyekUiState(insertUiEvent = insertProyekUiEvent)
    }

    fun insertProyek() {
        viewModelScope.launch {
            try {
                proyekRepository.insertProyek(uiState.insertUiEvent.toProyek())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertProyekUiState(
    val insertUiEvent: InsertProyekUiEvent = InsertProyekUiEvent()
)

data class InsertProyekUiEvent(
    val idProyek: String = "",
    val namaProyek: String = "",
    val deksripsiProyek: String = "",
    val tanggalMulai: String = "",
    val tanggalBerakhir: String = "",
    val statusProyek: String = ""
)

fun InsertProyekUiEvent.toProyek(): Proyek = Proyek(
    idProyek = idProyek,
    namaProyek = namaProyek,
    deksripsiProyek = deksripsiProyek,
    tanggalMulai = tanggalMulai,
    tanggalBerakhir = tanggalBerakhir,
    statusProyek = statusProyek
)

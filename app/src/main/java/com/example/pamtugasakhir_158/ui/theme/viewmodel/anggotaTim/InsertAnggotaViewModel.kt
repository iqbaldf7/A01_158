package com.example.pamtugasakhir_158.ui.theme.viewmodel.anggotaTim

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamtugasakhir_158.repository.AnggotaRepository
import kotlinx.coroutines.launch

class InsertAnggotaViewModel(private val anggotaRepository: AnggotaRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertAnggotaUiState())

    fun updateAnggotaState(insertAnggotaUiEvent: InsertAnggotaUiEvent) {
        uiState = InsertAnggotaUiState(insertUiEvent = insertAnggotaUiEvent)
    }

    fun insertAnggota() {
        viewModelScope.launch {
            try {
                anggotaRepository.insertAnggota(uiState.insertUiEvent.toAnggota())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertAnggotaUiState(
    val insertUiEvent: InsertAnggotaUiEvent = InsertAnggotaUiEvent()
)

data class InsertAnggotaUiEvent(
    val id_anggota: String = "",
    val id_tim: String = "",
    val nama_anggota: String = "",
    val peran: String = ""
)

fun InsertAnggotaUiEvent.toAnggota(): Anggota = Anggota(
    id_anggota = id_anggota,
    id_tim = id_tim,
    nama_anggota = nama_anggota,
    peran = peran
)

data class Anggota(
    val id_anggota: String,
    val id_tim: String,
    val nama_anggota: String,
    val peran: String
)

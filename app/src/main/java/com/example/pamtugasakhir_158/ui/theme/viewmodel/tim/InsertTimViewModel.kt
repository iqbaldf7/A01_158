package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import kotlinx.coroutines.launch


class InsertTimViewModel(private val timRepository: TimRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertTimUiState())

    fun updateTimState(insertTimUiEvent: InsertTimUiEvent) {
        uiState = InsertTimUiState(insertUiEvent = insertTimUiEvent)
    }

    fun insertTim() {
        viewModelScope.launch {
            try {
                timRepository.insertTim(uiState.insertUiEvent.toTim())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertTimUiState(
    val insertUiEvent: InsertTimUiEvent = InsertTimUiEvent()
)

data class InsertTimUiEvent(
    val id_tim: String = "",
    val nama_tim: String = "",
    val deskripsi_tim: String = "",
)

fun InsertTimUiEvent.toTim(): Tim = Tim(
    id_tim = id_tim,
    nama_tim = nama_tim,
    deskripsi_tim = deskripsi_tim
)
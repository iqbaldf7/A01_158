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
    val idTim: String = "",
    val namatim: String = "",
    val deskripsitim: String = "",
)

fun InsertTimUiEvent.toTim(): Tim = Tim(
    idTim = idTim,
    namatim = namatim,
    deskripsitim = deskripsitim
)
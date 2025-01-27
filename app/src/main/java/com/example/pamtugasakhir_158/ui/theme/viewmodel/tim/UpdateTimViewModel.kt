package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateTimViewModel(
    savedStateHandle: SavedStateHandle,
    private val timRepository: TimRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertTimUiState())
        private set

    private val _idTim: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_TIM])

    init {
        viewModelScope.launch{
            val tim = timRepository.getTimByID(_idTim)
            uiState = tim.toUiStateTim()
        }
    }

    fun updateTim() {
        viewModelScope.launch {
            try {
                val tim = uiState.insertUiEvent.toTim()
                timRepository.updateTim(_idTim, tim)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateState(insertUiEvent: InsertTimUiEvent) {
        uiState = InsertTimUiState(
            insertUiEvent = insertUiEvent
        )
    }
    fun Tim.toUiStateTim(): InsertTimUiState = InsertTimUiState(
        insertUiEvent = toInsertTimUiEvent()
    )

    fun Tim.toInsertTimUiEvent(): InsertTimUiEvent = InsertTimUiEvent(
        idTim = idTim,
        namatim = namatim,
        deskripsitim = deskripsitim
    )

}


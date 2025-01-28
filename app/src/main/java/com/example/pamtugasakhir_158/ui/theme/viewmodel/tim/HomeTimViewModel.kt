
package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeTimUiState {
    data class Success(val Tim: List<Tim>) : HomeTimUiState()
    object Loading : HomeTimUiState()
    object Error : HomeTimUiState()
}

class HomeTimViewModel(private val timRepository: TimRepository): ViewModel(){
    var timUiState: HomeTimUiState by mutableStateOf(HomeTimUiState.Loading)
        private set

    init {
        getTim()
    }

    fun getTim(){
        viewModelScope.launch {
            timUiState = HomeTimUiState.Loading
            timUiState = try {
                HomeTimUiState.Success(timRepository.getAllTim().data)
            } catch (e: IOException){
                HomeTimUiState.Error
            } catch (e: HttpException){
                HomeTimUiState.Error
            }
        }
    }

    fun deleteTim(idtim: String){
        viewModelScope.launch {
            try {
                timRepository.deleteTim(idtim)
            } catch (e: IOException) {
                HomeTimUiState.Error
            }catch (e : HttpException){
                HomeTimUiState.Error
            }
        }
    }
}



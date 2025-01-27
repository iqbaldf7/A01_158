
package com.example.pamtugasakhir_158.ui.theme.viewmodel.tim
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.repository.TimRepository
import com.example.pamtugasakhir_158.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.view.tim.OnError
import com.example.pamtugasakhir_158.ui.theme.view.tim.OnLoading
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.network.HttpException
import com.example.pamtugasakhir_158.PenyediaViewModel
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



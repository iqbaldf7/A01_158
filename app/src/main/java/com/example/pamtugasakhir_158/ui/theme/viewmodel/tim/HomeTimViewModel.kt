
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
                HomeTimUiState.Success(timRepository.getAllTim())
            } catch (e: IOException){
                HomeTimUiState.Error
            } catch (e: HttpException){
                HomeTimUiState.Error
            }
        }
    }

    fun deleteTim(id_tim: String){
        viewModelScope.launch {
            try {
                timRepository.deleteTim(id_tim)
            } catch (e: IOException) {
                HomeTimUiState.Error
            }catch (e : HttpException){
                HomeTimUiState.Error
            }
        }
    }
}

object DestinasiHomeTim : DestinasiNavigasi {
    override val route ="home"
    override val titleRes = "Home Tim"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewTim(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeTimViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeTim.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getTim()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Tim")
            }
        },
    ) { innerPadding ->
        HomeTimStatus(
            homeTimUiState = viewModel.timUiState,
            retryAction = { viewModel.getTim() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { tim ->
                viewModel.deleteTim(tim.id_tim)
                viewModel.getTim()
            }
        )
    }
}


@Composable
fun HomeTimStatus(
    homeTimUiState: HomeTimUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tim) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeTimUiState) {
        is HomeTimUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeTimUiState.Success ->
            if (homeTimUiState.Tim.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Tim")
                }
            } else {
                TimLayout(
                    tim = homeTimUiState.Tim,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_tim)
                    },
                    onDeleteClick = { tim ->
                        onDeleteClick(tim)
                    }
                )
            }
        is HomeTimUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun TimLayout(
    tim: List<Tim>,
    modifier: Modifier = Modifier,
    onDetailClick:(Tim)->Unit,
    onDeleteClick: (Tim) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tim){ data ->
            TimCard(
                tim = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(){onDetailClick(data)},
                onDeleteClick ={
                    onDeleteClick(data)
                }
            )

        }
    }
}

@Composable
fun TimCard(
    tim: Tim,
    modifier: Modifier = Modifier,
    onDeleteClick:(Tim)->Unit={}
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tim.nama_tim,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(tim)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }

            Text(
                text = "ID: ${tim.id_tim}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Deskripsi: ${tim.deskripsi_tim}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

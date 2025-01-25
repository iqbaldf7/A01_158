package com.example.pamtugasakhir_158.ui.theme.view.tim


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamtugasakhir_158.PenyediaViewModel
import com.example.pamtugasakhir_158.R
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeTimUiState
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeTimViewModel

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
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loadingg),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction:()->Unit, modifier: Modifier = Modifier){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.loadingg), contentDescription = ""
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
                    .clickable{onDetailClick(data)},
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

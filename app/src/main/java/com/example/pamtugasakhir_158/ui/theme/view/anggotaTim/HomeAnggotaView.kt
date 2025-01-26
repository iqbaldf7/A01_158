package com.example.pamtugasakhir_158.ui.theme.view.anggotaTim

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
import com.example.pamtugasakhir_158.model.AnggotaTim
import com.example.pamtugasakhir_158.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.viewmodel.anggotaTim.HomeAnggotaUiState
import com.example.pamtugasakhir_158.ui.theme.viewmodel.anggotaTim.HomeAnggotaViewModel

object DestinasiHomeAnggota : DestinasiNavigasi {
    override val route ="home"
    override val titleRes = "Home Anggota"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewAnggota(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeAnggotaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeAnggota.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getAnggota()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Anggota")
            }
        },
    ) { innerPadding ->
        HomeAnggotaStatus(
            homeAnggotaUiState = viewModel.anggotaUiState,
            retryAction = { viewModel.getAnggota() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { anggota ->
                viewModel.deleteAnggota(anggota.id_anggota)
                viewModel.getAnggota()
            }
        )
    }
}

@Composable
fun HomeAnggotaStatus(
    homeAnggotaUiState: HomeAnggotaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (AnggotaTim) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeAnggotaUiState) {
        is HomeAnggotaUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeAnggotaUiState.Success ->
            if (homeAnggotaUiState.anggota.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Anggota")
                }
            } else {
                AnggotaLayout(
                    anggota = homeAnggotaUiState.anggota,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_anggota)
                    },
                    onDeleteClick = { anggota ->
                        onDeleteClick(anggota)
                    }
                )
            }
        is HomeAnggotaUiState.Error -> OnError(
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
fun AnggotaLayout(
    anggota: List<AnggotaTim>,
    modifier: Modifier = Modifier,
    onDetailClick:(AnggotaTim)->Unit,
    onDeleteClick: (AnggotaTim) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(anggota){ data ->
            AnggotaCard(
                anggota = data,
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
fun AnggotaCard(
    anggota: AnggotaTim,
    modifier: Modifier = Modifier,
    onDeleteClick:(AnggotaTim)->Unit={}
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
                    text = anggota.nama_anggota,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(anggota)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }

            Text(
                text = "ID: ${anggota.id_anggota}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "ID Tim: ${anggota.id_tim}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Peran: ${anggota.peran}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

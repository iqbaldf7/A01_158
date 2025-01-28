package com.example.pamtugasakhir_158.ui.theme.view.proyek

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
import com.example.pamtugasakhir_158.model.Proyek
import com.example.pamtugasakhir_158.model.Tim
import com.example.pamtugasakhir_158.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.view.tim.HomeTimStatus
import com.example.pamtugasakhir_158.ui.theme.view.tim.OnError
import com.example.pamtugasakhir_158.ui.theme.view.tim.OnLoading
import com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek.HomeProyekUiState
import com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek.HomeProyekViewModel
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeTimUiState
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeTimViewModel

object DestinasiHomeProyek : DestinasiNavigasi {
    override val route = "home_proyek"
    override val titleRes = "Home Proyek"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewProyek(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeProyekViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeProyek.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getProyek()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Proyek")
            }
        },
    ) { innerPadding ->
        HomeProyekStatus(
            homeProyekUiState = viewModel.proyekUiState,
            retryAction = { viewModel.getProyek() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { proyek ->
                viewModel.deleteProyek(proyek.idProyek)
                viewModel.getProyek()
            }
        )
    }
}

@Composable
fun HomeProyekStatus(
    homeProyekUiState: HomeProyekUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Proyek) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeProyekUiState) {
        is HomeProyekUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeProyekUiState.Success ->
            if (homeProyekUiState.proyek.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Proyek")
                }
            } else {
                ProyekLayout(
                    proyek = homeProyekUiState.proyek,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idProyek)
                    },
                    onDeleteClick = { proyek ->
                        onDeleteClick(proyek)
                    }
                )
            }
        is HomeProyekUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ProyekLayout(
    proyek: List<Proyek>,
    modifier: Modifier = Modifier,
    onDetailClick: (Proyek) -> Unit,
    onDeleteClick: (Proyek) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(proyek) { data ->
            ProyekCard(
                proyek = data,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(data) },
                onDeleteClick = {
                    onDeleteClick(data)
                }
            )
        }
    }
}

@Composable
fun ProyekCard(
    proyek: Proyek,
    modifier: Modifier = Modifier,
    onDeleteClick: (Proyek) -> Unit = {}
) {
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
                    text = proyek.namaProyek,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(proyek) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }

            Text(
                text = "ID: ${proyek.idProyek}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "NamaProyek: ${proyek.namaProyek}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Deskripsi: ${proyek.deksripsiProyek}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "TanggalMulai: ${proyek.tanggalMulai}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "TanggalBerakhir: ${proyek.tanggalBerakhir}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Status: ${proyek.statusProyek}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

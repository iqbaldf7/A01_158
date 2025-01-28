package com.example.pamtugasakhir_158.ui.theme.view.proyek

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamtugasakhir_158.PenyediaViewModel
import com.example.pamtugasakhir_158.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek.InsertProyekUiEvent
import com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek.InsertProyekUiState
import com.example.pamtugasakhir_158.ui.theme.viewmodel.proyek.InsertProyekViewModel
import kotlinx.coroutines.launch

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item"
    override val titleRes = " entry proyek"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertProyekScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertProyekViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyProyek(
            insertUiState = viewModel.uiState,
            onProyekValueChange = viewModel::updateProyekState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertProyek()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBodyProyek(
    insertUiState: InsertProyekUiState,
    onProyekValueChange: (InsertProyekUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputProyek(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onProyekValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputProyek(
    insertUiEvent: InsertProyekUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertProyekUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.idProyek,
            onValueChange = { onValueChange(insertUiEvent.copy(idProyek = it)) },
            label = { Text("ID Proyek") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.namaProyek,
            onValueChange = { onValueChange(insertUiEvent.copy(namaProyek = it)) },
            label = { Text("Nama Proyek") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertUiEvent.deskripsiProyek,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsiProyek = it)) },
            label = { Text("Deskripsi Proyek") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data Proyek!",
                modifier = Modifier.padding(12.dp)
            )
        }

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

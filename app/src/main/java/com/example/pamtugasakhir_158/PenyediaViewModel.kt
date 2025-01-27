package com.example.pamtugasakhir_158

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.DetailTimViewModel
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeTimViewModel
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.InsertTimViewModel
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.UpdateTimViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeTimViewModel(ProyekApplications().container.timRepository) }
        initializer { InsertTimViewModel(ProyekApplications().container.timRepository) }
        initializer { DetailTimViewModel(ProyekApplications().container.timRepository) }
        initializer { UpdateTimViewModel(createSavedStateHandle(),ProyekApplications().container.timRepository) }

    }
}

fun CreationExtras.ProyekApplications(): ProyekApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ProyekApplications)
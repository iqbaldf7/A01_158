package com.example.pamtugasakhir_158.ui.theme.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiDetailTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiEntry
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiUpdate
import com.example.pamtugasakhir_158.ui.theme.view.tim.DetailViewTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.InsertTimScreen
import com.example.pamtugasakhir_158.ui.theme.view.tim.UpdateScreen
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.DestinasiHomeTim
import com.example.pamtugasakhir_158.ui.theme.viewmodel.tim.HomeViewTim


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeTim.route,
        modifier = modifier
    ) {
        // Halaman Home
        composable(DestinasiHomeTim.route) {
            HomeViewTim(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { idPekerja ->
                    if (idPekerja.isNotBlank()) {
                        navController.navigate("${DestinasiDetailTim.route}/$idPekerja")
                    } else {
                        // Tampilkan pesan kesalahan atau feedback
                    }
                }
            )
        }

        // Halaman Insert Pekerja
        composable(route = DestinasiEntry.route) {
            InsertTimScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Halaman Detail Pekerja
        composable(
            route = DestinasiDetailTim.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailTim.TIM) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idPekerja = backStackEntry.arguments?.getString(DestinasiDetailTim.TIM)
            if (!idPekerja.isNullOrBlank()) {
                DetailViewTim(
                    navigateBack = { navController.popBackStack() },
                    navigateToEdit = { navController.navigate("${DestinasiUpdate.route}/$idPekerja") }
                )
            } else {
                navController.popBackStack() // Argumen null atau kosong, kembali ke layar sebelumnya
            }
        }

        // Halaman Update Pekerja
        composable(
            route = DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID_TIM) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idPekerja = backStackEntry.arguments?.getString(DestinasiUpdate.ID_TIM)
            if (!idPekerja.isNullOrBlank()) {
                UpdateScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            } else {
                navController.popBackStack() // Argumen null atau kosong, kembali ke layar sebelumnya
            }
        }
    }
}
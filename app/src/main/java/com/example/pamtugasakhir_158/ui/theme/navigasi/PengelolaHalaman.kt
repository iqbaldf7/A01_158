package com.example.pamtugasakhir_158.ui.theme.navigasi

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamtugasakhir_158.depedenciesinjection.AppContainer
import com.example.pamtugasakhir_158.ui.theme.view.home.DestinasiHome
import com.example.pamtugasakhir_158.ui.theme.view.home.HomeScreen
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiDetailTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiEntry
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiHomeTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.DestinasiUpdate
import com.example.pamtugasakhir_158.ui.theme.view.tim.DetailTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.HomeViewTim
import com.example.pamtugasakhir_158.ui.theme.view.tim.InsertTimScreen
import com.example.pamtugasakhir_158.ui.theme.view.tim.UpdateScreen



@Composable
fun PengelolaHalaman(
    appContainer: AppContainer,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToTim = {navController.navigate(DestinasiHomeTim.route)},
                navigateToAnggota = {},
                navigateToTugas = {},
                navigateToProyek = {}

            )
        }
        // Halaman Home
        composable(DestinasiHomeTim.route) {
            HomeViewTim(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { idTim -> navController.navigate("${DestinasiDetailTim.route}/$idTim")
                }
            )
        }

        // Halaman Insert Pekerjaid
        composable(route = DestinasiEntry.route) {
            InsertTimScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Halaman Detail Pekerja
        composable(DestinasiDetailTim.route + "/{id_tim}") { navBackStackEntry ->
            val idTim = navBackStackEntry.arguments?.getString("id_tim")
            if (idTim != null) {
                DetailTim(
                    idTim = idTim,
                    repository = appContainer.timRepository, // Ganti dengan instance repository yang sesuai
                    navigateBack = { navController.popBackStack() } // Navigasi kembali
                )
            } else {
                // Handle kasus di mana idTim null, jika diperlukan
                Text("ID Tim tidak ditemukan")
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
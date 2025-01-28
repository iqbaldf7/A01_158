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
import com.example.pamtugasakhir_158.ui.theme.view.proyek.DestinasiDetailProyek
import com.example.pamtugasakhir_158.ui.theme.view.proyek.DestinasiHomeProyek
import com.example.pamtugasakhir_158.ui.theme.view.proyek.DetailProyek
import com.example.pamtugasakhir_158.ui.theme.view.proyek.HomeViewProyek
import com.example.pamtugasakhir_158.ui.theme.view.proyek.InsertProyekScreen
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
        // Halaman Home
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToTim = { navController.navigate(DestinasiHomeTim.route) },
                navigateToProyek = { navController.navigate(DestinasiHomeProyek.route) },
                navigateToTugas = {},
                navigateToAnggota = {}
            )
        }

        // Halaman Home Tim
        composable(DestinasiHomeTim.route) {
            HomeViewTim(
                navigateToItemEntry = { navController.navigate("insert_tim") },
                onDetailClick = { idTim ->
                    navController.navigate("${DestinasiDetailTim.route}/$idTim")
                }
            )
        }

        // Halaman Insert Tim
        composable(route = "insert_tim") {
            InsertTimScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Halaman Detail Tim
        composable(DestinasiDetailTim.route + "/{id_tim}") { navBackStackEntry ->
            val idTim = navBackStackEntry.arguments?.getString("id_tim")
            if (idTim != null) {
                DetailTim(
                    idTim = idTim,
                    repository = appContainer.timRepository,
                    navigateBack = { navController.popBackStack() }
                )
            } else {
                Text("ID Tim tidak ditemukan")
            }
        }

        // Halaman Home Proyek
        composable(DestinasiHomeProyek.route) {
            HomeViewProyek(
                navigateToItemEntry = { navController.navigate("insert_proyek") },
                onDetailClick = { idProyek ->
                    navController.navigate("${DestinasiDetailProyek.route}/$idProyek")
                }
            )
        }

        // Halaman Insert Proyek
        composable(route = "insert_proyek") {
            InsertProyekScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Halaman Detail Proyek
        composable(DestinasiDetailProyek.route + "/{id_proyek}") { navBackStackEntry ->
            val idProyek = navBackStackEntry.arguments?.getString("id_proyek")
            if (idProyek != null) {
                DetailProyek(
                    idProyek = idProyek,
                    repository = appContainer.proyekRepository,
                    navigateBack = { navController.popBackStack() }
                )
            } else {
                Text("ID Proyek tidak ditemukan")
            }
        }
    }
}

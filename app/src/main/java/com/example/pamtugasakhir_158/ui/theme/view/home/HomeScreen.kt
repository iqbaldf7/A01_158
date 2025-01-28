package com.example.pamtugasakhir_158.ui.theme.view.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pamtugasakhir_158.R
import com.example.pamtugasakhir_158.ui.theme.navigasi.DestinasiNavigasi
import com.example.pamtugasakhir_158.ui.theme.nosifar

object DestinasiHome : DestinasiNavigasi {
    override val route = "homescreen"
    override val titleRes = "Home screen"
}

@Composable
fun HomeScreen(
    navigateToTim: () -> Unit,
    navigateToAnggota: () -> Unit,
    navigateToTugas: () -> Unit,
    navigateToProyek: () -> Unit

) {
    Scaffold(
        topBar = {
            TopHeader()

        },
        bottomBar = {

        }

    ) { paddingvalues ->
        Column(modifier = Modifier.padding(paddingvalues)) {
            Row(
            ) {

                Image(
                    painter = painterResource(id = R.drawable.banner), // Ganti dengan ID drawable Anda
                    contentDescription = "Banner Image",
                    modifier = Modifier
                        .size(490.dp)
                        .fillMaxWidth()
                        .padding(paddingvalues)
                         // Sesuaikan tinggi banner sesuai kebutuhan
                        , // Sesuaikan bentuk sudut jika diperlukan
                    contentScale = ContentScale.Crop // Sesuaikan skala konten sesuai kebutuhan
                )

            }
            MenuSection(
                navigateToTim = navigateToTim,
                navigateToAnggota = navigateToAnggota,
                navigateToTugas = navigateToTugas,
                navigateToProyek = navigateToProyek,
                )
        }
    }
}


@Composable
fun TopHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 0.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFFF72798)
                    )
                )
            )
            .padding(16.dp),
    ) {
        // Kartu utama
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Konten di dalam kartu
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center, // Menempatkan teks di tengah secara horizontal
                verticalAlignment = Alignment.CenterVertically // Menempatkan teks di tengah secara vertikal
            ) {
                Text(
                    text = "VORTEX TEAM",
                    fontSize = 35.sp ,
                    fontFamily = nosifar,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MenuSection(
    navigateToTim: () -> Unit,
    navigateToAnggota: () -> Unit,
    navigateToProyek: () -> Unit,
    navigateToTugas: () -> Unit,

    ) {
    Column (modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Menu Utama",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier

            .fillMaxWidth()
        ,
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            GradientCard(
                title = "Proyek",
                iconResId = R.drawable.project, // Ganti dengan ID drawable Anda
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB771E5),
                        Color(0xFF8B5DFF)
                    )
                ),
                onClick = navigateToProyek
            )
        }
        item {
            GradientCard(
                title = "Tim",
                iconResId = R.drawable.teamwork, // Ganti dengan ID drawable Anda
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB771E5),
                        Color(0xFF8B5DFF)
                    )
                ),
                onClick = navigateToTim
            )
        }
        item {
            GradientCard(
                title = "AnggotaTim",
                iconResId = R.drawable.anggota, // Ganti dengan ID drawable Anda
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB771E5),
                        Color(0xFF8B5DFF)
                    )
                ),
                onClick = navigateToTim
            )
        }
        item {
            GradientCard(
                title = "Tugas",
                iconResId = R.drawable.jobseeking, // Ganti dengan ID drawable Anda
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFB771E5),
                        Color(0xFF8B5DFF)
                    )
                ),
                onClick = navigateToTugas
            )
        }


    }
}


@Composable
fun GradientCard(
    title: String,
    iconResId: Int, // Parameter untuk ID drawable
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (clicked) 0.98f else 1f,
        animationSpec = tween(durationMillis = 150)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                clicked = !clicked
                onClick()
            }
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .height(80.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = iconResId), // Memuat ikon dari drawable
                    contentDescription = title,
                    modifier = Modifier.size(50.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToTim = {},
        navigateToAnggota = {},
        navigateToTugas = {},
        navigateToProyek = {},

    )
}
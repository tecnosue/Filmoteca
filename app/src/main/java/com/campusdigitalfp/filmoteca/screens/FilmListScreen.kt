package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.sampledata.Film
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@Composable
fun FilmListScreen( navController: NavHostController,     modifier: Modifier = Modifier
) {
    val isActionMode =
        remember { mutableStateOf(false) } //Para saber si el usuario ha activado el modo de selección múltiple.
    val selectedFilms =
        remember { mutableStateListOf<Film>() } //seguimiento de las películas seleccionadas
    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",
                mostrarAtras = false,
                mostrarMenu = true,
                isActionMode,
                selectedFilms
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(FilmDataSource.films) { film ->

                    VistaPelicula(
                        film = film,
                        isSelected = selectedFilms.contains(film),
                        onClick = {
                            if (isActionMode.value) {
                                // Lógica de selección/deselección (si está en modo acción)
                                if (selectedFilms.contains(film)) {
                                    selectedFilms.remove(film)
                                    // Si se deselecciona la última, desactiva el modo acción
                                    if (selectedFilms.isEmpty()) {
                                        isActionMode.value = false
                                    }
                                } else {
                                    selectedFilms.add(film) // Añade a la selección
                                }
                            } else {
                                // Navegación normal a detalles
                                navController.navigate("filmData/${film.id}")
                            }
                        },
                        onLongClick = {
                            // Activa el modo edición y selecciona la película
                            isActionMode.value = true
                            selectedFilms.add(film)
                        }
                    )
                }
            }



            Button(
                onClick = {
                    navController.navigate("about")
                }
            ) {
                Text(stringResource(R.string.acerca_de))
            }
        }
    }
}





@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun VistaPelicula(
    film: Film,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isSelected: Boolean
) {
    // Definimos el color de fondo para indicar la selección
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            // Usa combinedClickable para ambos eventos
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .background(backgroundColor) // Aplicamos el color de fondo
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isSelected) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Película seleccionada",
                tint = Color(0xFF4CAF50), // Un color verde similar al de la imagen (verde Material Design 500)
                modifier = Modifier
                    .height(100.dp)
                    .padding(end = 16.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )

        } else {
            Image( painter = painterResource(film.imageResId),
                contentDescription = "cartel de pelicula",
                modifier = Modifier
                    .height(100.dp)
                    .padding(end = 16.dp)
                    .weight(1f),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = film.title ?: "<Sin título>",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = film.director ?: "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

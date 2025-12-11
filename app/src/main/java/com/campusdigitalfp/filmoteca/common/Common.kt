package com.campusdigitalfp.filmoteca.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.R.drawable.default_movie
import com.campusdigitalfp.filmoteca.sampledata.Film
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource
import com.campusdigitalfp.filmoteca.screens.FilmListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperiorComun(
    navController: NavController,
    title: String = "Filmoteca",
    mostrarAtras : Boolean = true,
    mostrarMenu: Boolean = false,
    onAtrasClick: () -> Unit = { navController.popBackStack() }
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        ) },
        navigationIcon = {
            if (mostrarAtras) {
                Box(
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack(
                                "filmList",
                                inclusive = false
                            )
                        }
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = stringResource(R.string.volver_a_inicio),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        },
//        navigationIcon = {
//            if (mostrarAtras) {
//                IconButton(onClick = onAtrasClick) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "Atrás"
//                    )
//                }
//            }
//        }
        //menú en FilmListScreen
        actions = {
        if (mostrarMenu ){

            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "menu de opciones",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        // onClick = { navController.navigate("new")},
                        text = { Text("Añadir Película") },
                        onClick = {
                            val nuevaPelicula = Film(
                                id = FilmDataSource.films.size,
                                title = "Película por defecto",
                                director = "Director Desconocido",
                                imageResId =R.drawable.default_movie,
                                comments = "",
                                format = Film.FORMAT_DVD,
                                genre = Film.GENRE_ACTION,
                                imdbUrl = "",
                                year = 2024
                            )
                            FilmDataSource.films.add(nuevaPelicula)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Acerca de") },
                        onClick = {
                            expanded = false
                            navController.navigate("about")
                        }
                    )
                }
            }
        }


    )
}

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}
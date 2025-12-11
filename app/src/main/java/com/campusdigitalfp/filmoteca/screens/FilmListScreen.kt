package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource

@Composable
fun FilmListScreen( navController: NavHostController,     modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",
                mostrarAtras = false,
                mostrarMenu = true
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
                    Row(  modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(film.imageResId),
                            contentDescription = "cartel de pelicula",
                            modifier = Modifier
                                .height(100.dp)
                                .padding(end = 16.dp)
                                .clickable {
                                    navController.navigate("filmData/${film.id}")
                                }
                                .weight(1f),
                            contentScale = androidx.compose.ui.layout.ContentScale.Fit
                        )
                        Column(
                            modifier= Modifier.weight(2f)
                        ) {
                            Text(
                                text = film.title ?: "<Sin título>",
                                style = MaterialTheme.typography.titleLarge,
                                color= MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("filmData/${film.id}")
                                    }

                            )
                            Text(
                                text = film.director ?: "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("filmData/${film.id}")
                                    }
                            )
                        }

                    }

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


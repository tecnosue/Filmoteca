package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R

@Composable
fun FilmDataScreen(navController: NavHostController, filmName: String,     modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val mensajeToast = stringResource(R.string.funcionalidad_sin_implementar)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            stringResource(R.string.datos_pelicula) ,
            style = MaterialTheme.typography.titleMedium
        )
        //muestro el nombre de la película
        Text(
            text = filmName,
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = {
                // voy  a la misma pantalla pero con otra película
                if (filmName == "Película A") {
                    navController.navigate("filmData/Película B")
                } else {
                    navController.navigate("filmData/Película A")
                }
            }
        ) {
            Text(stringResource(R.string.ver_pelicula_relacionada))
        }

        Button(
            onClick = {
                navController.navigate("filmEdit")
            }
        ) {
            Text(stringResource(R.string.editar_pelicula))
        }
        Button(
            onClick = {
                navController.navigate("filmList"){
                    popUpTo("filmList") { inclusive = true }
                }
            }
        ) {
            Text(stringResource(R.string.volver_principal))
        }
    }
}
package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Composable
fun FilmDataScreen(navController: NavHostController, filmName: String,     modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",

            )
        }
    ) { innerPadding ->
        val context = LocalContext.current
        //recibo el resultado
        val result = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<String>("result")

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Text(
                stringResource(R.string.datos_pelicula),
                style = MaterialTheme.typography.titleMedium
            )
            //muestro el nombre de la película
            Text(
                text = filmName,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (result != null) {
                Text(
                    text = if (result == "RESULT_OK") stringResource(R.string.edicion_ok)
                    else stringResource(R.string.edicion_cancelada),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .background(
                            color = if (result == "RESULT_OK") Color.Green else Color.Red,
                        )
                        .padding(8.dp)

                )
            }
            Spacer(modifier = Modifier.height(10.dp))

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
                    navController.navigate("filmEdit/$filmName")
                }

            ) {
                Text(stringResource(R.string.editar_pelicula))
            }
            Button(
                onClick = {
                    navController.navigate("filmList") {
                        popUpTo("filmList") { inclusive = true }
                    }
                }
            ) {
                Text(stringResource(R.string.volver_principal))
            }
        }
    }
}
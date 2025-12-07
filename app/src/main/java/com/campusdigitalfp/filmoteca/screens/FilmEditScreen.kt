package com.campusdigitalfp.filmoteca.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Composable
fun FilmEditScreen(navController: NavHostController, filmName: String,   modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",
                onAtrasClick = {
                    //devolver el result
                    navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
                    navController.popBackStack()

                }
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current
        val mensajeToast = stringResource(R.string.funcionalidad_sin_implementar)
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                stringResource(R.string.editando_pelicula),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = filmName,
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "result",
                        "RESULT_OK"
                    )
                    navController.popBackStack()
                }
            ) {
                Text(stringResource(R.string.guardar))
            }

            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "result",
                        "RESULT_CANCELED"
                    )
                    navController.popBackStack()
                }
            ) {
                Text(stringResource(R.string.cancelar))
            }

        }
    }
}

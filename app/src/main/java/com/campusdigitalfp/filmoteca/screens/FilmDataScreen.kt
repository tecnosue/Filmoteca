package com.campusdigitalfp.filmoteca.screens

import android.R.color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun
import com.campusdigitalfp.filmoteca.common.abrirPaginaWeb
import com.campusdigitalfp.filmoteca.sampledata.FilmDataSource


@Composable
fun FilmDataScreen(navController: NavHostController, filmId: Int,     modifier: Modifier = Modifier
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
        val film = FilmDataSource.films.getOrNull(filmId)

        if (film == null) {
            Text(
                text = "Película no encontrada",
                modifier = Modifier.padding(innerPadding)
            )
            return@Scaffold
        }
        //recibo el resultado
        val result = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.get<String>("result")

        Column(
            modifier = modifier
                .fillMaxSize()
                // Añado un padding de 16.dp a la izquierda y derecha
                .padding(horizontal = 24.dp)
                // Y aplico el padding del Scaffold al contenido
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center,

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(film.imageResId),
                    contentDescription = "cartel de pelicula",
                    modifier = Modifier
                        .height(150.dp) //altura fija a la imagen
                        .padding(end = 16.dp), // Espacio después de la imagen
                    // para que la imagen se recorte y ajuste correctamente
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )

                Column(
                    Modifier.weight(1f),
                    horizontalAlignment =Alignment.Start
                ) {
//                    Text(
//                        stringResource(R.string.datos_pelicula),
//                        style = MaterialTheme.typography.titleMedium
//                    )
                    //muestro el nombre de la película
                    Text(
                        //text = filmName,
                        text = film.title ?: "<Sin título>",
                        style = MaterialTheme.typography.titleLarge,
                        color= MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Director",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = film.director?: " ",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Año:",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = film.year.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "BluRay, Sci-Fi",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
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
                    abrirPaginaWeb("https://www.imdb.com/es-es/title/tt0241527/?ref_=mv_close", context)

                }
            ) {

                Text(
                    stringResource(R.string.ver_imdb),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.version_extendida),
                //para alinearlo a la izquierda
                modifier = Modifier.align(Alignment.Start)

            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Button(
                    onClick = {
                        navController.navigate("filmEdit/${film.id}")
                    },
                    //el primer boton coge la mitad del espacio
                    modifier = Modifier.weight(1f)

                ) {
                    Text(stringResource(R.string.editar_pelicula))
                }
                Button(
                    onClick = {
                        navController.navigate("filmList") {
                            popUpTo("filmList") { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.volver))
                }

            }


        }
    }
}


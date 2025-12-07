package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@Composable
fun AboutScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",

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
                text = stringResource(R.string.creada_por),
                //modifier = modifier,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Icono de perfil"
            )
            Row {
                Button(
                    onClick = {
                        //showToast(context= context, mensajeToast)
                        abrirPaginaWeb("https://campusdigitalfp.com/", context)

                    }
                )
                {
                    Text(stringResource(R.string.ir_sitio_web))
                }
                Button(
                    onClick = {
                        //showToast(context = context, mensajeToast)
                        mandarEmail(
                            context,
                            "sparacuellosrc@fpvirtualaragon.es",
                            context.getString(R.string.incidencia_con_filmoteca)
                        )


                    }
                ) {
                    Text(stringResource(R.string.obtener_soporte))
                }
            }

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(stringResource(R.string.volver))
            }

        }
    }

}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}

fun mandarEmail(context: Context, email: String, asunto: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email?subject=${Uri.encode(asunto)}")
        //putExtra(Intent.EXTRA_SUBJECT, asunto)
    }

    context.startActivity(intent)
}
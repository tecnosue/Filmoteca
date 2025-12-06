package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.campusdigitalfp.filmoteca.R

@Composable
fun AboutScreen( modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (
        modifier= modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(
            text = "Creado por Susana Paracuellos Ralfas",
            //modifier = modifier,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image (
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Icono de perfil"
        )
        Row {
            Button(
                onClick = {
                    showToast(context= context, "Funcionalidad sin implementar")
                }
            )
            {
                Text("Ir al sitio web")
            }
            Button(
                onClick = {
                    showToast(context = context, "Funcionalidad sin implementar")
                }
            ) {
                Text("Obtener soporte")
            }
        }

        Button(
            onClick = {
                showToast(context = context, "Funcionalidad sin implementar")
            }
        ) {
            Text("Volver")
        }

    }

}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
package com.campusdigitalfp.filmoteca.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R


import com.campusdigitalfp.filmoteca.common.BarraSuperiorComun

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmEditScreen(navController: NavHostController, filmName: String, modifier: Modifier = Modifier) {
    var titulo by remember { mutableStateOf(filmName) }
    var director by remember { mutableStateOf("") }
    var anyo by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }

    var expandedGenero by remember { mutableStateOf(false) }
    var expandedFormato by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val generoList = context.resources.getStringArray(R.array.genero_list).toList()
    val formatoList = context.resources.getStringArray(R.array.formato_list).toList()

    var genero by remember { mutableIntStateOf(0) }
    var formato by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            BarraSuperiorComun(
                navController = navController,
                title = "Filmoteca",
                onAtrasClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.harry_potter_piedra),
                    contentDescription = "cartel de pelicula",
                    modifier = Modifier
                        .height(100.dp)
                        .padding(end = 16.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )
                Button(onClick = { }) {
                    Text(stringResource(R.string.capturar_foto))
                }
                Button(onClick = { }) {
                    Text(stringResource(R.string.seleccionar_imagen))
                }
            }

            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text(stringResource(R.string.titulo_pelicula)) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            TextField(
                value = director,
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.director)) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            TextField(
                value = anyo,
                onValueChange = { anyo = it },
                label = { Text(stringResource(R.string.anio)) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            TextField(
                value = url,
                onValueChange = { url = it },
                label = { Text(stringResource(R.string.enlace_imdb)) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.genero),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedGenero,
                    onExpandedChange = { expandedGenero = !expandedGenero }
                ) {
                    TextField(
                        value = generoList[genero],
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGenero)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedGenero,
                        onDismissRequest = { expandedGenero = false }
                    ) {
                        generoList.forEachIndexed { index, seleccion ->
                            DropdownMenuItem(
                                text = { Text(seleccion) },
                                onClick = {
                                    genero = index
                                    expandedGenero = false
                                }
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.formato),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedFormato,
                    onExpandedChange = { expandedFormato = !expandedFormato }
                ) {
                    TextField(
                        value = formatoList[formato],
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFormato)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedFormato,
                        onDismissRequest = { expandedFormato = false }
                    ) {
                        formatoList.forEachIndexed { index, seleccion ->
                            DropdownMenuItem(
                                text = { Text(seleccion) },
                                onClick = {
                                    formato = index
                                    expandedFormato = false
                                }
                            )
                        }
                    }
                }
            }

            TextField(
                value = comentarios,
                onValueChange = { comentarios = it },
                label = { Text(stringResource(R.string.comentarios)) },
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(vertical = 4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_OK")
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.guardar))
                }

                Button(
                    onClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.cancelar))
                }
            }
        }
    }
}
package co.edu.appmovilsmartcoach.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.appmovilsmartcoach.R
import co.edu.appmovilsmartcoach.screens.home.Articulo
import co.edu.appmovilsmartcoach.screens.home.ListItemRow

@Composable
fun Eventos(navController: NavController) {
    val listadoAyuda: List<Articulo> = listOf(
        Articulo(R.drawable.configuraciones, "Ajustes de privacidad", "Sigue estos pasos para editar tus ajustes de privacidad: En la pestaña perfil de la app, haz clic en el icono de herramientas"),
        Articulo(R.drawable.cuaderno, "Planes de entrenamiento", "Details sobre Entrenamiento"),
        Articulo(R.drawable.compartir, "Compartir imágenes", "Crea tu propia imagen personalizada y compártela en cualquiera de las plataformas instalas en tu dispositivo"),
        Articulo(
            R.drawable.entrenamiento,
            "¿Puedo volver a empezar un plan de entrenamiento?",
            "Si quieres volver a empezar tu plan de entrenamiento, sigue estos pasos: Abre la app Start Coach, selecciona: Abandonar y asi podrás volver a empezar tu plan"
        ),
        Articulo(R.drawable.notificaciones, "Sección de notificaciones", "Las notificaciones que recibas aparecerán en la sección, Notificaciones,"),
    )
    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        //agregar varios elementos con item
        items(listadoAyuda) { item ->
            ListItemRow(item)
        }
    }
    @Composable
    fun ListItemRow(item: Articulo) {
        //Guardar el estado de un boton, remember nos ayuda para almacenar datos en memoria
        var masInformacion = remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //Para cambiar la forma de la caja
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = MaterialTheme.colors.secondary)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.title,
                    modifier = Modifier
                        .height(25.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = tween(70, 0, LinearEasing)
                        )
                        //para darle un peso para que el boton no quede tan cerca
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier
                        .padding(50.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    if (masInformacion.value) {
                        Text(text = item.details)
                    }


                }

                IconButton(onClick = {
                    masInformacion.value = !masInformacion.value
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Icono expandir o colapsar",
                        modifier = Modifier.rotate(
                            if (masInformacion.value) 180f else 360f
                        )
                    )
                }
            }

        }

    }
}
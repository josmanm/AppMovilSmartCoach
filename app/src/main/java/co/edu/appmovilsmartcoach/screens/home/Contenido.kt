package co.edu.appmovilsmartcoach.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.appmovilsmartcoach.R

@Composable
fun Contenido(navController: NavController){
    val listado : List<Articulo> = listOf(
        Articulo(R.drawable.logo, "Razas", "Details sobre razas"),
        Articulo(R.drawable.logo, "Entrenamiento", "Details sobre Entrenamiento"),
        Articulo(R.drawable.logo, "Alimentacion", "Details sobre Alimentacion"),
        Articulo(R.drawable.logo, "Belleza", "Details sobre Belleza"),
        Articulo(R.drawable.logo, "Reproduccion", "Details sobre Reproduccion"),
        Articulo(R.drawable.logo, "Salud", "Details sobre Salud"),
        Articulo(R.drawable.logo, "Noticias", "Details sobre Noticias")
    )
    /*val datos: List<String> = listOf(
        "Razas",
        "Entrenamiento",
        "Alimentacion",
        "Belleza",
        "Reproduccion",
        "Salud",
        "Noticias"
    )*/
    //Elementos que pueden necesitar desplazamiento

    LazyColumn(
        contentPadding = PaddingValues(all= 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        //agregar varios elementos con item
        items(listado){
            item -> ListItemRow(item)
        }
    }
}

@Composable
fun ListItemRow (item: Articulo){
    //Guardar el estado de un boton, remember nos ayuda para almacenar datos en memoria
    var masInformacion = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //Para cambiar la forma de la caja
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ){
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
                    .fillMaxWidth()) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.body2
                )
                if(masInformacion.value){
                    Text(text = item.details)
                }

            }

            IconButton(onClick = {
                masInformacion.value = !masInformacion.value
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription ="Icono expandir o colapsar",
                    modifier = Modifier.rotate(
                        if(masInformacion.value) 180f else 360f
                    )
                )
            }
        }

    }
}

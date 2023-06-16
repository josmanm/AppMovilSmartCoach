package co.edu.appmovilsmartcoach.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.edu.appmovilsmartcoach.R
import co.edu.appmovilsmartcoach.ui.theme.AppMovilSmartCoachTheme

@Composable
fun Contenido(navController: NavController){

    val listadoFila : List<Articulo> = listOf(
        Articulo(R.drawable.plan1, "MÁS EN FORMA", "Ponte más fuerte en solo 6 semanas"),
        Articulo(R.drawable.plan2, "SIN LÍMITES", "Pasa al siguiente nivel"),
        Articulo(R.drawable.plan5, "RUNNING", "Entrenamiento cruzado para mejorar el rendimiento"),
        Articulo(R.drawable.plan4, "MÁS ENERGÍA", "Dale un impulso a tu nivel de energia con entrenamiento cortos.")
    )
    val listado : List<Articulo> = listOf(
        Articulo(R.drawable.usuario, "TRANSFORMACIÓN TOTAL", "Ponte en forma con un plan personalizado"),
        Articulo(R.drawable.abdominoplastia, "MARCA ABS EN 6 SEMANAS", "Define y marca los abdominales"),
        Articulo(R.drawable.musculo, "FUERTE Y FIT EN 3 SEMANAS", "Quema calorias y tonifica músculo"),
        Articulo(R.drawable.pesa, "DEFINE Y FORTALECE", "El entrenamiento de fuerza no solo moldea tu cuerpo, tambien te ayuda a perder peso")
    )

    LazyColumn(
        contentPadding = PaddingValues(all= 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ){
        //agregar varios elementos con item
        item {
            Text("PLANES DESTACADOS", fontSize = 25.sp, modifier = Modifier.padding(0.dp, 0.dp, 10.dp,0.dp), fontWeight = FontWeight.Bold)
        }
        item {
            LazyRow{
                items(listadoFila) {
                    item -> card(item)
                }

            }
        }
        item {
            Text("OTROS PLANES", fontSize = 25.sp, modifier = Modifier.padding(0.dp, 20.dp, 0.dp,0.dp), fontWeight = FontWeight.Bold)
        }

        item {
            Text("que te pueden interesar", fontSize = 15.sp, modifier = Modifier.padding(0.dp, 3.dp, 10.dp,0.dp))
        }
        items(listado){
            item -> ListItemRow(item)
        }
    }
}


@Composable
fun card (item: Articulo) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { }
            .width(200.dp)
            .height(250.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {

        Column() {

            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.title,

            )
            Column (
                modifier = Modifier
                    .padding(10.dp)
            ){
                Text(
                    text = item.title,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier
                    .padding(10.dp)
                )
                Text(
                    text =item.details,
                    fontSize = 15.sp
                )
            }
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

@Preview
@Composable
fun previewContenido (){
    val navController = rememberNavController()
    AppMovilSmartCoachTheme() {
        Contenido(navController = navController)
    }
}

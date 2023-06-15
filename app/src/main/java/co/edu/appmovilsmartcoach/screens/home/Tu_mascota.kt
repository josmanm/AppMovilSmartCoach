package co.edu.appmovilsmartcoach.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.appmovilsmartcoach.R

@Composable
fun Tu_mascota(navController: NavController){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        CardEjercicio()
        CardEstiramientos()
    }
}

@Composable
fun CardEjercicio () {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { }
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column() {

            Image(
                painter = painterResource(id = R.drawable.card1),
                contentDescription = "Descripcion 1"
            )
            Column (
                modifier = Modifier
                    .padding(10.dp)
            ){
                Text(
                    text = "10 COSAS QUE DEBES EVITAR DESPUES DE HACER EJERCICIO",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text ="por Santiago Lopez",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
fun CardEstiramientos () {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { }
            .fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column() {

            Image(
                painter = painterResource(id = R.drawable.card2),
                contentDescription = "Descripcion 1"
            )
            Column (
                modifier = Modifier
                    .padding(10.dp)
            ){
                Text(
                    text = "10 COSAS QUE DEBES EVITAR DESPUES DE HACER EJERCICIO",
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier
                    .padding(10.dp)
                )
                Text(
                    text ="por Santiago Lopez",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

package co.edu.appmovilsmartcoach.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import co.edu.appmovilsmartcoach.R

@Composable
fun Busca_mascota(navController: NavController){

        Column(
            modifier = Modifier
                .padding(40.dp, 80.dp, 40.dp, 40.dp )
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.sin_notificaciones),
                contentDescription = "Campana",
                modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
            )
            Column (
                modifier = Modifier
                    .padding(10.dp)
            ){
                Text(
                    text = "SIN NOTIFICACIONES",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier
                    .padding(3.dp)
                )
                Text(
                    text ="por el momento",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

}
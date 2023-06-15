package co.edu.appmovilsmartcoach.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.appmovilsmartcoach.screens.Eventos
import co.edu.appmovilsmartcoach.screens.home.*
import co.edu.appmovilsmartcoach.screens.home.Destinos.*

//Mapa de navegacion, relaciona las rutas con los contenidos a mostrar
@Composable
fun NavigationMenu(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Pantalla3.ruta
    ){
        composable(Pantalla1.ruta){
            Tu_mascota(navController)
        }
        composable(Pantalla2.ruta){
            Contenido(navController)
        }
        composable(Pantalla3.ruta){
            Busca_mascota(navController)
        }
        composable(Pantalla4.ruta){
            Eventos(navController)
        }

    }

}
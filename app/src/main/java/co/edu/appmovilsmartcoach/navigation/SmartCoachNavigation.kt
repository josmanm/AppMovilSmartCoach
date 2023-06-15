package co.edu.appmovilsmartcoach.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.appmovilsmartcoach.screens.home.PantallaPrincipal
import co.edu.appmovilsmartcoach.screens.home.Tu_mascota
import co.edu.appmovilsmartcoach.screens.login.MascotaLoginScreen
import co.edu.appmovilsmartcoach.screens.login.SmartCoachSplashScreen

@Composable
fun SmartCoachNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController =navController ,
        startDestination = SmartCoachScreens.SplashScreen.name
    ){
        composable(SmartCoachScreens.SplashScreen.name){
            SmartCoachSplashScreen(navController = navController)
        }
        composable(SmartCoachScreens.LoginScrenn.name){
            MascotaLoginScreen(navController = navController)
        }
        composable(SmartCoachScreens.MascotaHomeScreen.name){
            PantallaPrincipal()
        }
    }
}
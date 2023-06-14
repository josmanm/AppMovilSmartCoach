package co.edu.appmovilsmartcoach.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.appmovilsmartcoach.screens.home.Home
import co.edu.appmovilsmartcoach.screens.login.MascotaLoginScreen
import co.edu.appmovilsmartcoach.screens.login.MascotaSplashScreen

@Composable
fun MascotaNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController =navController ,
        startDestination = MascotaScreens.SplashScreen.name
    ){
        composable(MascotaScreens.SplashScreen.name){
            MascotaSplashScreen(navController = navController)
        }
        composable(MascotaScreens.LoginScrenn.name){
            MascotaLoginScreen(navController = navController)
        }
        composable(MascotaScreens.MascotaHomeScreen.name){
            Home(navController = navController)
        }
    }
}
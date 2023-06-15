package co.edu.appmovilsmartcoach.screens.home

import co.edu.appmovilsmartcoach.R

//Destinos de la barra baja
sealed class Items_menu(
    val icon: Int,
    val title: String,
    val ruta: String
){
    object Pantalla1: Items_menu(R.drawable.logo, "Inicio", "pantalla1")
    object Pantalla2: Items_menu(R.drawable.logo, "Contenidos", "pantalla2")
    object Pantalla3: Items_menu(R.drawable.logo, "Premium", "pantalla5")
}
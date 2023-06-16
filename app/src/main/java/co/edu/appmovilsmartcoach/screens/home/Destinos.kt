package co.edu.appmovilsmartcoach.screens.home

import co.edu.appmovilsmartcoach.R

sealed class Destinos(
    val icon: Int,
    val title : String,
    val ruta : String
){
    object Pantalla1 : Destinos(R.drawable.icongimnasio, "NOTICIAS", "pantalla1")
    object Pantalla2 : Destinos(R.drawable.logo, "RUTINAS", "pantalla2")
    object Pantalla3 : Destinos(R.drawable.iconcampana, "NOTIFICACIONES", "pantalla3")
    object Pantalla4 : Destinos(R.drawable.iconinformacion, "AYUDA Y FEEDBACK", "pantalla4")
    object Pantalla5 : Destinos(R.drawable.icon_usuario_de_perfil, "PERFIL", "pantalla5")
    object Pantalla6 : Destinos(R.drawable.icon_cerrar_sesion, "SALIR", "pantalla6")
}
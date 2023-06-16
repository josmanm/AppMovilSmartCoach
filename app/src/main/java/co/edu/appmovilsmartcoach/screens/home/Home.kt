package co.edu.appmovilsmartcoach.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.appmovilsmartcoach.R
import co.edu.appmovilsmartcoach.navigation.NavigationMenu
import co.edu.appmovilsmartcoach.navigation.SmartCoachNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaPrincipal() {
    val navController = rememberNavController()
    //Guardar el estado del scaffold para la configuracion del menu lateral
    val scaffoldState = rememberScaffoldState()
    //Para abrir o cerrar
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Destinos.Pantalla1,
        Destinos.Pantalla2,
        Destinos.Pantalla3,
        Destinos.Pantalla4,
        Destinos.Pantalla5,
    )
    val bottomItems = listOf(
        Items_menu.Pantalla1,
        Items_menu.Pantalla2,
        Items_menu.Pantalla3
    )
    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope, scaffoldState, navController as NavHostController,navigationItems)},
        //Menu de navegacion lateral
        drawerContent = {
            Drawer(
                scope,
                scaffoldState,
                navController,
                menu_items = navigationItems
            )
        },
    ){ it
        NavigationMenu(navController)
    }
}
@Composable
fun Drawer (
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    menu_items: List<Destinos>
){
    Column() {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Menu de opciones",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        //Resaltar la fila del item seleccionado
        //
        val currentRoute = currentRoute(navController)
        menu_items.forEach{item->
            DrawerItem(
                item = item,
                selected = currentRoute == item.ruta
            ){
                navController.navigate(item.ruta){
                    //Para que no cree nuevas instancias de la clase si ya esta en la
                    //parte superior de la fila de actividades
                    launchSingleTop = true
                }
                //despues de lanzar actividad, se cierra el menu
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }
    }
}
@Composable
fun currentRoute(navController: NavController) : String?{
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}
@Composable
fun DrawerItem(
    item: Destinos,
    selected : Boolean,
    //evento click a las filas del menu, funcion que va lanzar el navigation
    onItemClick : (Destinos) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(
                if (selected) MaterialTheme.colors.primaryVariant.copy(alpha = 0.25f)
                else Color.Transparent
            )
            .padding(8.dp)
            //Indicarle que funcion va a lanzar
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(painterResource(id = item.icon), contentDescription = item.title)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.body1,
            color = if (selected) MaterialTheme.colors.secondaryVariant
            else MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    //Para poner el titulo
    navController: NavHostController,
    menu_items: List<Destinos>
){
    //para abrir el menu de opciones
    var showMenu by remember{
        mutableStateOf(false)
    }
    val currentRoute = currentRoute(navController = navController)
    //Cambiar el nombre de la barra de navegacion
    var MyTitle = "Mascota Feliz"
    menu_items.forEach{ item ->
        if(currentRoute == item.ruta) MyTitle = item.title
    }

    TopAppBar (
        title= { Text(text = MyTitle, fontSize = 15.sp)},
        //Boton para abrir el boton lateral
        navigationIcon = {
            IconButton(onClick = {
                //se usa scope para dar la orden abrir el menu cuando se le de en el boton
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Icon de menu")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Boton refrescar")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Boton configurar")
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Mas opciones")
            }
            //Menu desplegable
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu=false } ,
                modifier = Modifier.width(150.dp)
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Idiomas"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "idiomas")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Compartir")
                }
            }
        }

    )
}

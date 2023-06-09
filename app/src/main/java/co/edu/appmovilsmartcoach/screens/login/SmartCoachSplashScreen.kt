package co.edu.appmovilsmartcoach.screens.login

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.appmovilsmartcoach.navigation.SmartCoachScreens
import kotlinx.coroutines.delay
import co.edu.appmovilsmartcoach.R

@Composable
fun SmartCoachSplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            ),
        )
        delay(3500L)
        navController.navigate(SmartCoachScreens.LoginScrenn.name)
//        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
//            navController.navigate(SmartCoachScreens.LoginScrenn.name)
//        }else{
//            navController.navigate(SmartCoachScreens.MascotaHomeScreen.name){
//                popUpTo(SmartCoachScreens.SplashScreen.name){
//                    inclusive = true
//                }
//            }
//        }
    }
    val color =MaterialTheme.colors.primary
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = color)
    ) {
        Column(
            modifier = Modifier
                .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Smart Coach"
            )
            Spacer(modifier = Modifier.height(15.dp))
        }

    }
}
package co.edu.appmovilsmartcoach.screens.login

import android.app.Instrumentation.ActivityResult
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.appmovilsmartcoach.navigation.SmartCoachScreens
import co.edu.appmovilsmartcoach.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiActivity
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.math.log

@Composable
fun MascotaLoginScreen(
        navController: NavController,
        viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
        // Para controlar si vamos a loger o  a crear una cuenta
        val showLoginForm = rememberSaveable {
                mutableStateOf(true)
        }
        val laucher = rememberLauncherForActivityResult(
                contract =ActivityResultContracts
                        .StartActivityForResult()
        ){
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try{
                        val account = task.getResult(ApiException::class.java)
                        val credencial = GoogleAuthProvider.getCredential(account.idToken ,null)
                        viewModel.signInWithGoogleCredential(credencial){
                                navController.navigate(SmartCoachScreens.MascotaHomeScreen.name)
                        }
                }catch (ex:Exception){
                        Log.d("Smart Coach","Google Sing in fallo")
                }
        }
        Surface(modifier = Modifier
                .fillMaxSize()
        ) {
                Image(
                        painter = painterResource(id = co.edu.appmovilsmartcoach.R.drawable.fondo1),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                )
                Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                ){
                        if(showLoginForm.value){
                                Text(text ="INICIA SESION" )
                                UserForm(
                                        isCreateAccount = false
                                ){
                                        email,password ->
                                        Log.d("SmartCoach","Logueado con $email y $password")
                                        viewModel.singInWithEmailAndPassword(email,password) {
                                                navController.navigate(SmartCoachScreens.MascotaHomeScreen.name)
                                                }
                                }
                        }else
                        {
                                Text(text = "Crea una cuenta")
                                UserForm(
                                        isCreateAccount = true
                                )
                                { email, password ->
                                        Log.d("SmartCoach", "Craando cuenta con $email y $password")
                                        viewModel.createUserWithEmailAndPassword(email,password){
                                                navController.navigate(SmartCoachScreens.MascotaHomeScreen.name)
                                        }
                                }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                        ) {
                                val text1 =
                                        if(showLoginForm.value) "¿No tienes cuenta ?"
                                        else "¿Ya tienes cuenta?"
                                val text2 =
                                        if(showLoginForm.value) "Registrarte"
                                        else "Inicia sesion"
                                Text(text = text1)
                                Text(text = text2,
                                modifier = Modifier
                                        .clickable { showLoginForm.value = !showLoginForm.value }
                                        .padding(start = 5.dp),
                                        color = MaterialTheme.colors.secondaryVariant
                                )
                        }
                        Row(
                                modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { }
                        ){
                                Image(
                                        painter = painterResource(id = R.drawable.icongoogle),
                                        contentDescription ="Login de Google",
                                        modifier = Modifier
                                                .padding(10.dp)
                                                .size(40.dp)
                                )
                                Text(text = "Login con google"
                                )
                        }
                }
        }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
        isCreateAccount: Boolean = false,
        onDone:(String,String ) ->Unit ={email,pwd ->}
) {
        val email = rememberSaveable {
                mutableStateOf("")
        }
        val password = rememberSaveable{
                mutableStateOf("")
        }
        val passwordVisible = rememberSaveable{
                mutableStateOf(false)
        }
        val valido = remember(email.value, password.value) {
                email.value.trim().isNotEmpty() &&
                        password.value.isNotEmpty()
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                EmailInput(
                        emailState = email
                )
                PasswordInput(
                        passwordState = password,
                        labelId = "Password",
                        passwordVisible = passwordVisible
                )
                Spacer(modifier = Modifier.height(15.dp))
                SubmitButton(
                        textId = if(isCreateAccount) "Crear cuenta " else "Login",
                        inputValido = valido
                ){
                        onDone(email.value.trim(), password.value.trim())
                        keyboardController?.hide()
                }
        }
}

@Composable
fun SubmitButton(
        textId: String,
        inputValido:Boolean,
        onClic:()->Unit
) {
        Button(onClick = onClic,
                modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                shape = CircleShape,
                enabled = inputValido
        ) {
         Text(text = textId,
         modifier = Modifier
                 .padding(5.dp)
         )
        }
}

@Composable
fun PasswordInput(
        passwordState: MutableState<String>,
        labelId: String,
        passwordVisible: MutableState<Boolean>
) {
        val visualTransformation = if (passwordVisible.value)
                VisualTransformation.None
        else PasswordVisualTransformation()

        OutlinedTextField(
                value =passwordState.value ,
                onValueChange = {passwordState.value = it},
                label = { Text(text = labelId)},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                ),
                modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                visualTransformation = visualTransformation,
                trailingIcon = {
                        if(passwordState.value.isNotBlank()){
                                passwordVisibleIcon(passwordVisible)
                        }
                }
        )
}
@Composable
fun  passwordVisibleIcon(passwordVisible: MutableState<Boolean>
) {
        val image =
                if(passwordVisible.value)
                        Icons.Default.VisibilityOff
                else
                        Icons.Default.Visibility
        IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
        }) {
                Icon(
                        imageVector = image,
                        contentDescription = "" )
        }
}
@Composable
fun EmailInput(
        emailState: MutableState<String>,
        labelId: String ="Email"
) {
        InputField(
                valueState = emailState,
                labelId = labelId,
                keyboardType= KeyboardType.Email,
        )
}
@Composable
fun InputField(
        valueState: MutableState<String>,
        labelId: String,
        isSingleLine: Boolean = true,
        keyboardType: KeyboardType,
) {
        OutlinedTextField(
                value =valueState.value ,
                onValueChange = {valueState.value = it},
                label = { Text(text = labelId)},
                singleLine = isSingleLine,
                modifier = Modifier
                        .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType
                )
        )
}

package co.edu.appmovilsmartcoach.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun singInWithEmailAndPassword(email:String , password: String , home:()->Unit)
    =viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult->
                    Log.d("FB", "" +
                            "singInWithEmailAndPassword Logueado!!!: ${authResult.toString()}")
                    home()
                }
                .addOnFailureListener{ex->
                    // código cuando falla
                    // Tienes acceso a la excepción
                    Log.d("FB", "" +
                            "singInWithEmailAndPassword Falló!!!: ${ex.localizedMessage}")
                    //errorLogueo()
                }
        }catch (ex: Exception){
            Log.d("Mascota feliz","singInWithEmailAndPassword ${ex.message} !!")
        }
    }

    fun createUserWithEmailAndPassword(
        email:String,
        password: String,
        home: () -> Unit
    ){
        if(_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task->
                    if (task.isSuccessful){
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }else{
                        Log.d("Mascota feliz", "createUserWithEmailAndPassword" )
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        user["user_id"] = userId.toString()
        user["display_name"] =displayName.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("Mascota feliz","Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("Mascota feliz", "Ocurrio un error")
            }
    }

}
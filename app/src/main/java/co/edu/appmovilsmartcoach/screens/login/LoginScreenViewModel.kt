package co.edu.appmovilsmartcoach.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun singInWithEmailAndPassword(email:String , password: String , home:()->Unit)
    =viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {authResult->
                    Log.d("Smart coach", "" +
                            "singInWithEmailAndPassword Logueado!!!: ${authResult.toString()}")
                    home()
                }
                .addOnFailureListener{ex->
                    // código cuando falla
                    // Tienes acceso a la excepción
                    Log.d("Smart coach", "" +
                            "singInWithEmailAndPassword Falló!!!: ${ex.localizedMessage}")
                    //errorLogueo()
                }
        }catch (ex: Exception){
            Log.d("Smart coach","singInWithEmailAndPassword ${ex.message} !!")
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
                        Log.d("Smart coach", "createUserWithEmailAndPassword" )
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
                Log.d("Smart coach","Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("Smart coach", "Ocurrio un error")
            }
    }

}
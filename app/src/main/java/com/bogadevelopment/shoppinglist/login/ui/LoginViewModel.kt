package com.bogadevelopment.shoppinglist.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.bogadevelopment.shoppinglist.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {



    private val auth: FirebaseAuth = Firebase.auth

    private val _email = MutableLiveData<String>()      // It's allow to modify only from this class
    val email: LiveData<String> = _email

    private val _password =
        MutableLiveData<String>()      // It's allow to modify only from this class
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8


    fun signInWithEmailAndPassword(email: String, password: String, navigationController: NavHostController) {

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("ShoppingList", "Loggeado")
                            navigationController.navigate(Routes.ShoppingCartScreen.route)
                        } else {
                            Log.d("ShoppingList", "No Loggeado")
                        }
                    }
            } catch (ex: Exception) {
                Log.d("ShoppingList", "Error SignIn")
            }
        }
    }


}
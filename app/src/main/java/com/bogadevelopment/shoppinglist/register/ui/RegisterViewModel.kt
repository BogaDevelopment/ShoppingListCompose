package com.bogadevelopment.shoppinglist.register.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel {

    private val auth: FirebaseAuth = Firebase.auth
    val _loading = MutableLiveData(false)

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _lastName = MutableLiveData<String>()
    val lastName : LiveData<String> = _lastName

    private val _email = MutableLiveData<String>()      // It's allow to modify only from this class
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()      // It's allow to modify only from this class
    val password : LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()      // It's allow to modify only from this class
    val repeatPassword : LiveData<String> = _repeatPassword

    private val _isRegisterEnable = MutableLiveData<Boolean>()
    val isRegisterEnable : LiveData<Boolean> = _isRegisterEnable


    fun onRegisterChanged(name : String, lastName : String, email : String, password : String, repeatPassword: String) {
        _name.value = name
        _lastName.value = lastName
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
        _isRegisterEnable.value = enableRegister(email, password, repeatPassword)
    }

    fun enableRegister(email: String, password: String, repeatPassword: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8 && password == repeatPassword

    fun createUsersWithEmailAndPassword(email: String, password: String, loginView: () -> Unit){
        if(_loading.value == false ){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        loginView()
                    }else{
                        Log.d("Register", "createUsersWithEmailAndPassword: ${task.result}")
                    }
                    _loading.value = false
                }
        }
    }

}
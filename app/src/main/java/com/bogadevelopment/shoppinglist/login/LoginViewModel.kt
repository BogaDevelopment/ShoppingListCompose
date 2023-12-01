package com.bogadevelopment.shoppinglist.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()      // It's allow to modify only from this class
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()      // It's allow to modify only from this class
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable : LiveData<Boolean> = _isLoginEnable

    fun onLoginChanged(email : String, password : String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email,password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8

}
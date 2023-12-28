package com.bogadevelopment.shoppinglist.register.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RegisterViewModel {

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



}
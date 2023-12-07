package com.bogadevelopment.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bogadevelopment.shoppinglist.login.LoginViewModel
import com.bogadevelopment.shoppinglist.register.RegisterScreen
import com.bogadevelopment.shoppinglist.register.RegisterViewModel
import com.bogadevelopment.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //RegisterScreen(RegisterViewModel())
                    LoginScreen(LoginViewModel())
                }
            }
        }
    }
}

/*
@Composable
fun DefaultPreview() {
    ShoppingListTheme {
        LoginScreen(LoginViewModel())
    }
}
 */
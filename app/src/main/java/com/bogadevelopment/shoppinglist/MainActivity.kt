package com.bogadevelopment.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.LoginScreen.route){
                        composable(Routes.LoginScreen.route){ LoginScreen(loginViewModel = LoginViewModel(), navigationController)}
                        composable(Routes.RegisterScreen.route){ RegisterScreen(registerViewModel = RegisterViewModel(),navigationController)}
                    }
                }
            }
        }
    }
}

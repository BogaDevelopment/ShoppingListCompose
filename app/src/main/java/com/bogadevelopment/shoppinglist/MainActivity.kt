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
import com.bogadevelopment.shoppinglist.login.ui.LoginScreen
import com.bogadevelopment.shoppinglist.login.ui.LoginViewModel
import com.bogadevelopment.shoppinglist.register.ui.RegisterScreen
import com.bogadevelopment.shoppinglist.register.ui.RegisterViewModel
import com.bogadevelopment.shoppinglist.shoppingCart.ui.ShoppingCartScreen
import com.bogadevelopment.shoppinglist.ui.theme.ShoppingListTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme() {
                // Change status bar color
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colors.primaryVariant,
                    darkIcons = false
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    val startDestination : String

                    if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
                        startDestination = Routes.LoginScreen.route
                    }else{
                        startDestination =Routes.ShoppingCartScreen.route
                    }


                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = startDestination){
                        composable(Routes.LoginScreen.route){ LoginScreen(loginViewModel = LoginViewModel(), navigationController) }
                        composable(Routes.RegisterScreen.route){ RegisterScreen(registerViewModel = RegisterViewModel(),navigationController) }
                        composable(Routes.ShoppingCartScreen.route){ ShoppingCartScreen() }
                    }
                }
            }
        }
    }
}

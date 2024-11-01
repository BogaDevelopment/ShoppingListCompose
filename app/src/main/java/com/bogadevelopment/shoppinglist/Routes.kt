package com.bogadevelopment.shoppinglist

sealed class Routes(var route : String) {
    object LoginScreen : Routes("LoginScreen")
    object RegisterScreen : Routes("RegisterScreen")
    object ShoppingCartScreen : Routes("ShoppingCartScreen")
    object ShoppingListScreen : Routes ("ShoppingListScreen")
}
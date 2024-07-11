package com.bogadevelopment.shoppinglist.shoppingCart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bogadevelopment.shoppinglist.Routes
import com.bogadevelopment.shoppinglist.dialogs.NewShoppingCartDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShoppingCartScreen(navigationController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { Content() },
        topBar = { ToolBar(scope, scaffoldState) },
        scaffoldState = scaffoldState,
        drawerContent = { Drawer(navigationController) },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End
    )

}

@Composable
fun Drawer(navigationController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = { LogOut(navigationController) }) {
            Text(text = "Cerrar Sesion")
        }
        Divider()
    }
}

fun LogOut(navigationController: NavHostController) {
    FirebaseAuth.getInstance().signOut()
    navigationController.navigate(Routes.LoginScreen.route)
}

@Composable
fun Content() {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary
                    )
                )
            )
    ) {
    }
}

@Composable
fun ToolBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Text(text = "ShoppingList", color = MaterialTheme.colors.onPrimary)
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun FAB() {
    var showDialog by remember { mutableStateOf(false) }

    NewShoppingCartDialog(show = showDialog, onDismiss = {showDialog = false})

    FloatingActionButton(
        modifier = Modifier.size(60.dp),
        onClick = { showDialog = true },
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "AddIcon"
        )
    }
}

